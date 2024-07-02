package org.simmer.knu_scraping.dto;

import org.jsoup.nodes.Element;
import org.simmer.knu_scraping.domain.MajorEnum;

public record Notice (String tag, String title, String date, String link) {
    public static Notice of(Element e, MajorEnum major) {
        String tag = "없음";
        String url = e.select(major.link).attr("href");

        if(major.tag != null) {
            tag = e.select(major.tag).text();
        }

        if(major.linkFactory != null) {
            url = major.linkFactory.createLink(url);
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
