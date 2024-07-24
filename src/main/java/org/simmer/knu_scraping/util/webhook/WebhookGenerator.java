package org.simmer.knu_scraping.util.webhook;

import java.util.List;
import org.simmer.knu_scraping.schedule.Major;

public interface WebhookGenerator {

    List<Major> major();
    String generate(String tag);
}
