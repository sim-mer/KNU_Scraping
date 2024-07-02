package org.simmer.knu_scraping.domain.link_utils;

public class ElectronicsEngineering implements LinkFactory {
    private final String baseUrl = "https://see.knu.ac.kr/content/board/notice.html";

    @Override
    public String createLink(String input) {
        return baseUrl + input;
    }
}
