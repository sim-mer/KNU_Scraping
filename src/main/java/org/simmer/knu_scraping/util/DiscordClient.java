package org.simmer.knu_scraping.util;

import org.simmer.knu_scraping.config.MajorEnum;
import org.simmer.knu_scraping.dto.Notice;
import org.simmer.knu_scraping.dto.DiscordMessage;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Component
public class DiscordClient {
    public void sendDiscordMessage(List<Notice> notices, MajorEnum major) {
        for(Notice notice : notices) {
            DiscordMessage discordMessage = new DiscordMessage(notice);

            WebClient webClient = WebClient.create();
            webClient.post()
                    .uri(major.webhookUrl)
                    .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                    .body(BodyInserters.fromValue(discordMessage))
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
        }
    }
}
