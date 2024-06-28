package org.simmer.knu_scraping.dto;

import lombok.Getter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.simmer.knu_scraping.config.MajorEnum;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Getter
public class HtmlDocument {
    private final MajorEnum major;
    private final Document document;
    private final int currentNoticeNum;

    public HtmlDocument(MajorEnum major) {
        this.major = major;
        this.document = getWebDocument();
        this.currentNoticeNum = currentNoticeNum();
    }

    public List<Notice> getNotices(int newNoticeCount) {
        List<Notice> newNotices = new ArrayList<>();
        Elements contents = document.select(major.noticeList);

        for (int i = newNoticeCount - 1; i >= 0 ; i--) {
            newNotices.add(new Notice(
                    contents.get(i).select(major.tag).text(),
                    contents.get(i).select(major.title).text(),
                    contents.get(i).select(major.date).text(),
                    contents.get(i).select(major.link).attr("href")
            ));
        }

        return newNotices;
    }

    private Document getWebDocument() {
        try {
            return Jsoup.connect(major.url).get();
        } catch (IOException e) {
            throw new RuntimeException(major.ordinal() + " 페이지를 가져오는데 실패했습니다.");
        }
    }

    private int currentNoticeNum() {
        return Integer.parseInt(
                document
                        .select(major.currentNoticeNum)
                        .first()
                        .text()
        );
    }
}
