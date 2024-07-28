package org.simmer.knu_scraping.schedule.dto;

import org.jsoup.nodes.Element;
import org.simmer.knu_scraping.util.html.HtmlSelector;

public record Notice (String tag, String title, String date, String link) {
    public static Notice of(Element e, HtmlSelector selector) {
        String tag = "통합";
        String url = e.select(selector.link).attr("href");

        if(selector.tag != null) {
            tag = e.select(selector.tag).text();
        }

        if(selector.linkGenerator != null) {
            url = selector.linkGenerator.createLink(url);
        }

        String title = e.select(selector.title).first().ownText();

        return new Notice(
                tag,
                title,
                e.select(selector.date).text(),
                url
        );
    }
}
