package org.simmer.knu_scraping.util.webhook;

import java.util.List;
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
    public List<Major> major() {
        return List.of(Major.COMPUTER_SCIENCE_AND_ENGINEERING, Major.KNU_ACADEMIC);
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
            case "통합" -> knuAcademic;
            default -> null;
        };
    }
}
