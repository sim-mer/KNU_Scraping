package org.simmer.knu_scraping.util.webhook;

import org.simmer.knu_scraping.schedule.Major;

public interface WebhookGenerator {

    Major major();
    String generate(String tag);
}
