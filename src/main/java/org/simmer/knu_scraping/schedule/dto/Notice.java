package org.simmer.knu_scraping.schedule.dto;

import org.jsoup.nodes.Element;
import org.simmer.knu_scraping.util.html.HtmlSelector;

public record Notice (String tag, String title, String date, String link) {
    public static Notice of(Element e, HtmlSelector major) {
        String tag = "통합";
        String url = e.select(major.link).attr("href");

        if(major.tag != null) {
            tag = e.select(major.tag).text();
        }

        if(major.linkGenerator != null) {
            url = major.linkGenerator.createLink(url);
        }

        String title = e.select(major.title).first().ownText();

        return new Notice(
                tag,
                title,
                e.select(major.date).text(),
                url
        );
    }
}
