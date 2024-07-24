package org.simmer.knu_scraping.util.html.link;

public class ElectronicsEngineeringLink implements LinkGenerator {
    private final String baseUrl = "https://see.knu.ac.kr/content/board/notice.html";

    @Override
    public String createLink(String input) {
        return baseUrl + input;
    }
}
