package org.simmer.knu_scraping.schedule;

import lombok.RequiredArgsConstructor;
import org.simmer.knu_scraping.schedule.dto.HtmlDocument;
import org.simmer.knu_scraping.schedule.dto.Notice;
import org.simmer.knu_scraping.client.DiscordClient;
import org.simmer.knu_scraping.util.webhook.WebhookGenerator;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ScrapingTask {

    private final DiscordClient discordClient;
    private final List<WebhookGenerator> webhookGenerators;
    private final RedisTemplate<String, String> redisTemplate;


    @Scheduled(cron = "0 0/5 7-19 * * MON-FRI")
    public void scraping() {
        for (Site site : Site.values()) {
            HtmlDocument htmlDocument = new HtmlDocument(site.htmlSelector);

            if(site == Site.INTERNATIONAL) {
                String currentTitle = htmlDocument.getCurrentTitle();
                String recentTitle = getRecentTitle(currentTitle, site.name());
                if(!currentTitle.equals(recentTitle)) {
                    List<Notice> notices = htmlDocument.getNoticesByTitle(recentTitle);
                    sendDiscordMessage(notices, site);
                }
                continue;
            }

            int currentNoticeNum = htmlDocument.getCurrentNoticeNum();

            int recentNum = getRecentNum(currentNoticeNum, site.ordinal());

            if (currentNoticeNum > recentNum) {
                List<Notice> notices = htmlDocument.getNotices(currentNoticeNum - recentNum);
                sendDiscordMessage(notices, site);
            }
        }
    }

    private void sendDiscordMessage(List<Notice> notices, Site site) {
        var webhookGeneratorList = webhookGenerators.stream()
            .filter(webhookGenerator -> webhookGenerator.major().contains(site))
            .toList();

        for (WebhookGenerator webhookGenerator : webhookGeneratorList) {
            discordClient.sendDiscordMessage(notices, webhookGenerator);
        }
    }

    private int getRecentNum(int currentNoticeNum, int site) {
        ValueOperations<String, String> values = redisTemplate.opsForValue();
        String recent = values.get(String.valueOf(site));

        values.set(String.valueOf(site), String.valueOf(currentNoticeNum));

        if (recent == null) {
            return currentNoticeNum;
        }

        return Integer.parseInt(recent);
    }

    private String getRecentTitle(String currentTitle, String site) {
        ValueOperations<String, String> values = redisTemplate.opsForValue();
        String recent = values.get(site);

        values.set(site, currentTitle);

        if (recent == null) {
            return currentTitle;
        }

        return recent;
    }
}
