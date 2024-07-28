package org.simmer.knu_scraping.util.webhook;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.simmer.knu_scraping.schedule.Site;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("spring.webhook.electronics-engineering")
@RequiredArgsConstructor
public class EEWebhook implements WebhookGenerator{

    private final String knuAcademic;
    private final String international;
    private final String defaultWebhook;


    @Override
    public List<Site> major() {
        return List.of(Site.ELECTRONICS_ENGINEERING, Site.KNU_ACADEMIC, Site.INTERNATIONAL);
    }

    @Override
    public String generate(String tag) {
        return switch (tag) {
            case "국제교류처" -> international;
            case "통합" -> knuAcademic;
            default -> defaultWebhook;
        };
    }
}
