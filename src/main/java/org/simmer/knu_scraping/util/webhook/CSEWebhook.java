package org.simmer.knu_scraping.util.webhook;

import lombok.RequiredArgsConstructor;
import org.simmer.knu_scraping.schedule.Major;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("spring.webhook.computer-science-and-engineering")
@RequiredArgsConstructor
public class CSEWebhook implements WebhookGenerator {

    private final String knuAcademic;
    private final String general;
    private final String academic;
    private final String scholarship;
    private final String platform;
    private final String global;
    private final String ai;
    private final String graduate;
    private final String graduateContract;


    @Override
    public Major major() {
        return Major.COMPUTER_SCIENCE_AND_ENGINEERING;
    }

    @Override
    public String generate(String tag) {
        return switch (tag) {
            case "일반공지" -> general;
            case "학사" -> academic;
            case "장학" -> scholarship;
            case "심컴" -> platform;
            case "글솝" -> global;
            case "인컴" -> ai;
            case "대학원" -> graduate;
            case "대학원 계약학과" -> graduateContract;
            default -> knuAcademic;
        };
    }
}
