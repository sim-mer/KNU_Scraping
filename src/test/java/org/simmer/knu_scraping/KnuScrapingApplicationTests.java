package org.simmer.knu_scraping;

import org.junit.jupiter.api.Test;
import org.simmer.knu_scraping.domain.MajorEnum;
import org.simmer.knu_scraping.domain.HtmlDocument;
import org.simmer.knu_scraping.dto.Notice;
import org.simmer.knu_scraping.util.DiscordClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.List;

@SpringBootTest
class KnuScrapingApplicationTests {
    @Autowired
    private DiscordClient discordClient;

    @Test
    public void test() {
        for(MajorEnum major : MajorEnum.values()) {
            HtmlDocument htmlDocument = new HtmlDocument(major);
            System.out.println(major + " 최신 게시글 번호 : " + htmlDocument.getCurrentNoticeNum());

            List<Notice> notices = htmlDocument.getNotices(1);

            for(Notice notice : notices) {
                System.out.println(notice.toString());
            }

            discordClient.sendDiscordMessage(notices, major);
        }
    }
}
