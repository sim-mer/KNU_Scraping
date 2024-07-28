package org.simmer.knu_scraping.util.webhook;

import java.util.List;
import org.simmer.knu_scraping.schedule.Site;

public interface WebhookGenerator {

    List<Site> major();
    String generate(String tag);
}
