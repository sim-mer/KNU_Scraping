package org.simmer.knu_scraping.domain;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Webhook {
    public static String computerScienceAndEngineering;
    public static String knuAcademic;

    @Value("${spring.webhook.computer_science_and_engineering}")
    public void setComputerScienceAndEngineering(String computerScienceAndEngineering) {
        Webhook.computerScienceAndEngineering = computerScienceAndEngineering;
    }

    @Value("${spring.webhook.knu_academic}")
    public void setKnuAcademic(String knuAcademic) {
        Webhook.knuAcademic = knuAcademic;
    }
}
