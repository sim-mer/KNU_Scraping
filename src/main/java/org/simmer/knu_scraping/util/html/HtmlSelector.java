package org.simmer.knu_scraping.util.html;

import org.simmer.knu_scraping.util.html.link.ElectronicsEngineeringLink;
import org.simmer.knu_scraping.util.html.link.InternationalLink;
import org.simmer.knu_scraping.util.html.link.KnuAcademicLink;
import org.simmer.knu_scraping.util.html.link.LinkGenerator;

public enum HtmlSelector {
    COMPUTER_SCIENCE_AND_ENGINEERING(
        "https://cse.knu.ac.kr/bbs/board.php?bo_table=sub5_1",
        "td.td_num2",
        "#fboardlist > div > table > tbody > tr",
        "td.td_subject > a",
        "td.td_subject > div > a",
        "td.td_datetime.hidden-xs",
        "td.td_subject > div > a",
        null
    ),
    KNU_ACADEMIC(
        "https://www.knu.ac.kr/wbbs//wbbs/bbs/btin/stdList.action?btin.page=57&menu_idx=42",
        "td.num",
        "#btinForm > div > table > tbody > tr",
        null,
        "td.subject > a",
        "td.date",
        "td.subject > a",
        new KnuAcademicLink()
    ),
    ELECTRONICS_ENGINEERING(
        "https://see.knu.ac.kr/content/board/notice.html",
        "td:nth-child(1)",
        "#content > div > div > div.board_list > div.board_body > table > tbody > tr",
        "td.left > a > span:nth-child(1)",
        "td.left > a",
        "td:nth-child(4)",
        "td.left > a",
        new ElectronicsEngineeringLink()
    ),
    INTERNATIONAL(
        "https://home.knu.ac.kr/HOME/global/sub.htm?nav_code=glo1549935200",
        "td.fst",
        "#body_content > div.board_list > table > tbody > tr",
        "td:nth-child(4)",
        "td.subject > a",
        "td:nth-child(5)",
        "td.subject > a",
        new InternationalLink()
    );

    public final String url;
    public final String noticeNum;
    public final String noticeList;
    public final String tag;
    public final String title;
    public final String date;
    public final String link;
    public final LinkGenerator linkGenerator;

    HtmlSelector(String url, String noticeNum, String noticeList, String tag, String title, String date, String link, LinkGenerator linkGenerator) {
        this.url = url;
        this.noticeNum = noticeNum;
        this.noticeList = noticeList;
        this.tag = tag;
        this.title = title;
        this.date = date;
        this.link = link;
        this.linkGenerator = linkGenerator;
    }
}
