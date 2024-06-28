package org.simmer.knu_scraping.schedule;

import lombok.RequiredArgsConstructor;
import org.simmer.knu_scraping.config.MajorEnum;
import org.simmer.knu_scraping.dto.HtmlDocument;
import org.simmer.knu_scraping.dto.Notice;
import org.simmer.knu_scraping.redis.RecentNotice;
import org.simmer.knu_scraping.redis.RedisCRUDRepository;
import org.simmer.knu_scraping.util.DiscordClient;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ScrapingTask {
    private final RedisCRUDRepository redisCRUDRepository;
    private final DiscordClient discordClient;


    @Scheduled(cron = "0 0/5 7-19 * * MON-FRI")
    public void scraping() {
        for (MajorEnum major : MajorEnum.values()) {
            HtmlDocument htmlDocument = new HtmlDocument(major);

            int currentNoticeNum = htmlDocument.getCurrentNoticeNum();

            int recentNum = getRecentNum(currentNoticeNum, major.ordinal());

            if(currentNoticeNum > recentNum) {
                List<Notice> notices = htmlDocument.getNotices(currentNoticeNum - recentNum);
                discordClient.sendDiscordMessage(notices, major);
            }
        }
    }

    private int getRecentNum(int currentNoticeNum, int major) {
        RecentNotice recentNotice = redisCRUDRepository.findById(major)
                .orElse(new RecentNotice(major, currentNoticeNum));

        int recentNum = recentNotice.getRecentNoticeNum();

        recentNotice.setRecentNoticeNum(currentNoticeNum);
        redisCRUDRepository.save(recentNotice);

        return recentNum;
    }
}
