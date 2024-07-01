package org.simmer.knu_scraping;

import org.junit.jupiter.api.Test;
import org.simmer.knu_scraping.domain.MajorEnum;
import org.simmer.knu_scraping.dto.HtmlDocument;
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
    public void test() throws IOException {
        HtmlDocument htmlDocument = new HtmlDocument(MajorEnum.KNU_ACADEMIC);
        System.out.println(htmlDocument.getCurrentNoticeNum());

        List<Notice> notices = htmlDocument.getNotices(5);

        for(Notice notice : notices) {
            System.out.println(notice.toString());
        }

        discordClient.sendDiscordMessage(notices, MajorEnum.KNU_ACADEMIC);

        htmlDocument = new HtmlDocument(MajorEnum.COMPUTER_SCIENCE_AND_ENGINEERING);
        System.out.println(htmlDocument.getCurrentNoticeNum());

        notices = htmlDocument.getNotices(5);

        for(Notice notice : notices) {
            System.out.println(notice.toString());
        }

        discordClient.sendDiscordMessage(notices, MajorEnum.COMPUTER_SCIENCE_AND_ENGINEERING);
    }
}
