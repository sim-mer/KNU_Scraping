package org.simmer.knu_scraping.dto;

import lombok.Getter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.simmer.knu_scraping.domain.MajorEnum;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Getter
public class HtmlDocument {
    private final MajorEnum major;
    private final Document document;
    private final int currentNoticeNum;
    private int startNoticeIndex = 0;

    public HtmlDocument(MajorEnum major) {
        this.major = major;
        this.document = getWebDocument();
        this.currentNoticeNum = currentNoticeNum();
    }

    public List<Notice> getNotices(int newNoticeCount) {
        List<Notice> newNotices = new ArrayList<>();
        Elements contents = document.select(major.noticeList);

        for (int i = newNoticeCount - 1 + startNoticeIndex; i >= startNoticeIndex ; i--) {
            newNotices.add(Notice.of(contents.get(i), major));
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
        Elements rows = document.select(major.noticeList);

        for (Element row : rows) {
            Element tdNum = row.selectFirst(major.currentNoticeNum);
            startNoticeIndex++;

            String text = tdNum.text();

            if(text.matches("\\d+")) {
                startNoticeIndex--;
                return Integer.parseInt(text);
            }
        }

        throw new RuntimeException("공지사항 번호를 가져오는데 실패했습니다.");
    }
}
