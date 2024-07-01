package org.simmer.knu_scraping.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class KnuAcademic implements SiteFactory{
    private final String baseUrl = "https://www.knu.ac.kr/wbbs/wbbs/bbs/btin/stdViewBtin.action?&note_div=row&menu_idx=42";
    private final List<String> paramNames = List.of("&bbs_cde=", "&bltn_no=");

    @Override
    public String createLink(String input) {
        StringBuilder sb = new StringBuilder(baseUrl);
        var params = findParam(input);

        sb.append(paramNames.get(0));
        sb.append(params.get(0));
        sb.append(paramNames.get(1));
        sb.append(params.get(2));

        return sb.toString();
    }

    private List<String> findParam(String input) {
        Pattern pattern = Pattern.compile("'([^']*)'");
        Matcher matcher = pattern.matcher(input);

        List<String> params = new ArrayList<>();

        while (matcher.find()) {
            params.add(matcher.group(1));
        }

        return params;
    }
}
