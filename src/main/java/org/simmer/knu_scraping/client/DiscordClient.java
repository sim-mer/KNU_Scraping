package org.simmer.knu_scraping.client;

import java.util.List;
import org.simmer.knu_scraping.schedule.dto.Notice;
import org.simmer.knu_scraping.client.dto.DiscordMessage;
import org.simmer.knu_scraping.util.webhook.WebhookGenerator;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class DiscordClient {
    private final RestClient restClient;

    public DiscordClient() {
        this.restClient = RestClient.create();
    }

    public void sendDiscordMessage(List<Notice> notices, WebhookGenerator webhookGenerator) {
        for(Notice notice : notices) {
            DiscordMessage discordMessage = new DiscordMessage(notice);
            var url = webhookGenerator.generate(notice.tag());
            if (url == null) {
                continue;
            }

            restClient.post()
                    .uri(url)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(discordMessage)
                    .retrieve();
        }
    }
}
