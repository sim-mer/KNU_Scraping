package org.simmer.knu_scraping.schedule.dto;

import lombok.Getter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.simmer.knu_scraping.util.html.HtmlSelector;

@Getter
public class HtmlDocument {
    private final HtmlSelector selector;
    private final Document document;
    private final int currentNoticeNum;
    private int startNoticeIndex = 0;

    public HtmlDocument(HtmlSelector selector) {
        this.selector = selector;
        this.document = getWebDocument();
        this.currentNoticeNum = currentNoticeNum();
    }

    private Document getWebDocument() {
        try {
            return Jsoup.connect(selector.url).get();
        } catch (IOException e) {
            throw new RuntimeException(selector.ordinal() + " 페이지를 가져오는데 실패했습니다.");
        }
    }

    public List<Notice> getNotices(int newNoticeCount) {
        List<Notice> newNotices = new ArrayList<>();
        Elements contents = document.select(selector.noticeList);

        for (int i = newNoticeCount - 1 + startNoticeIndex; i >= startNoticeIndex ; i--) {
            newNotices.add(Notice.of(contents.get(i), selector));
        }

        return newNotices;
    }

    private int currentNoticeNum() {
        Elements rows = document.select(selector.noticeList);

        for (Element row : rows) {
            Element tdNum = row.selectFirst(selector.noticeNum);
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
