package org.simmer.knu_scraping.schedule;

import lombok.RequiredArgsConstructor;
import org.simmer.knu_scraping.domain.MajorEnum;
import org.simmer.knu_scraping.dto.HtmlDocument;
import org.simmer.knu_scraping.dto.Notice;
import org.simmer.knu_scraping.util.DiscordClient;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ScrapingTask {
    private final DiscordClient discordClient;
    private final RedisTemplate<String, String> redisTemplate;


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
        ValueOperations<String, String> values = redisTemplate.opsForValue();
        String recent = values.get(String.valueOf(major));

        values.set(String.valueOf(major), String.valueOf(currentNoticeNum));

        if(recent == null) {
            return currentNoticeNum;
        }

        return Integer.parseInt(recent);
    }
}
