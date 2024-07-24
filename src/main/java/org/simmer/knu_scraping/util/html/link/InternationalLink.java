package org.simmer.knu_scraping.util.html.link;

public class InternationalLink implements LinkGenerator{
    private final String baseUrl = "https://home.knu.ac.kr";

    @Override
    public String createLink(String input) {
        return baseUrl + input;
    }
}
