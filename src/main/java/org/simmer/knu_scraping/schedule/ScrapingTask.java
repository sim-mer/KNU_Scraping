package org.simmer.knu_scraping.schedule;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
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
public class ScrapingTask {

    private final DiscordClient discordClient;
    private final Map<Major, WebhookGenerator> webhookGenerators;
    private final RedisTemplate<String, String> redisTemplate;

    public ScrapingTask(DiscordClient discordClient, List<WebhookGenerator> webhookGenerators,
        RedisTemplate<String, String> redisTemplate) {
        this.discordClient = discordClient;
        this.webhookGenerators = webhookGenerators.stream()
            .collect(Collectors.toUnmodifiableMap(WebhookGenerator::major, Function.identity()));
        this.redisTemplate = redisTemplate;
    }


    @Scheduled(cron = "0 0/5 7-19 * * MON-FRI")
    public void scraping() {
        for (Major major : Major.values()) {
            HtmlDocument htmlDocument = new HtmlDocument(major.htmlSelector);

            int currentNoticeNum = htmlDocument.getCurrentNoticeNum();

            int recentNum = getRecentNum(currentNoticeNum, major.ordinal());

            if (currentNoticeNum > recentNum) {
                List<Notice> notices = htmlDocument.getNotices(currentNoticeNum - recentNum);
                discordClient.sendDiscordMessage(notices, webhookGenerators.get(major));
            }
        }
    }

    private int getRecentNum(int currentNoticeNum, int major) {
        ValueOperations<String, String> values = redisTemplate.opsForValue();
        String recent = values.get(String.valueOf(major));

        values.set(String.valueOf(major), String.valueOf(currentNoticeNum));

        if (recent == null) {
            return currentNoticeNum;
        }

        return Integer.parseInt(recent);
    }
}
