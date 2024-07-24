package org.simmer.knu_scraping.schedule;

import org.simmer.knu_scraping.util.html.HtmlSelector;

public enum Major {
    COMPUTER_SCIENCE_AND_ENGINEERING(
        HtmlSelector.COMPUTER_SCIENCE_AND_ENGINEERING
    ),
    KNU_ACADEMIC(
        HtmlSelector.KNU_ACADEMIC
    ),
    ELECTRONICS_ENGINEERING(
        HtmlSelector.ELECTRONICS_ENGINEERING
    ),
    INTERNATIONAL(
        HtmlSelector.INTERNATIONAL
    );

    public final HtmlSelector htmlSelector;

    Major(HtmlSelector htmlSelector) {
        this.htmlSelector = htmlSelector;
    }
}
