package org.simmer.knu_scraping.client.dto;

import lombok.Getter;

import java.util.List;
import org.simmer.knu_scraping.schedule.dto.Notice;

@Getter
public class DiscordMessage {
    List<Embed> embeds;

    public DiscordMessage(Notice notice) {
        Embed embed = new Embed();
        embed.title = notice.title();
        embed.url = notice.link();
        embed.fields = List.of(
                new Embed.Field("\uD83C\uDFF7\uFE0F" + "Tag", notice.tag(), true),
                new Embed.Field("\uD83D\uDCC5" + "Date", notice.date(), true)
        );
        embeds = List.of(embed);
    }

    @Getter
    static class Embed {
        String title;
        String url;
        List<Field> fields;

        @Getter
        static class Field {
            public String name;
            public String value;
            public Boolean inline;

            public Field(String tag, String value, Boolean inline) {
                this.name = tag;
                this.value = value;
                this.inline = inline;
            }
        }
    }
}