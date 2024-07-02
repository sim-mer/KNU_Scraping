package org.simmer.knu_scraping.domain;

import org.simmer.knu_scraping.domain.link_utils.ElectronicsEngineering;
import org.simmer.knu_scraping.domain.link_utils.KnuAcademic;
import org.simmer.knu_scraping.domain.link_utils.LinkFactory;

public enum MajorEnum {
    COMPUTER_SCIENCE_AND_ENGINEERING(
            "https://cse.knu.ac.kr/bbs/board.php?bo_table=sub5_1",
            Webhook.computerScienceAndEngineering,
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
            Webhook.knuAcademic,
            "td.num",
            "#btinForm > div > table > tbody > tr",
            null,
            "td.subject > a",
            "td.date",
            "td.subject > a",
            new KnuAcademic()
    ),
    ELECTRONICS_ENGINEERING(
            "https://see.knu.ac.kr/content/board/notice.html",
            Webhook.electronicsEngineering,
            "td:nth-child(1)",
            "#content > div > div > div.board_list > div.board_body > table > tbody > tr",
            "td.left > a > span:nth-child(1)",
            "td.left > a",
            "td:nth-child(4)",
            "td.left > a",
            new ElectronicsEngineering()
    );

    public final String url;
    public final String webhookUrl;
    public final String noticeNum;
    public final String noticeList;
    public final String tag;
    public final String title;
    public final String date;
    public final String link;
    public final LinkFactory linkFactory;

    MajorEnum(String url, String webhookUrl, String noticeNum, String noticeList, String tag, String title, String date, String link, LinkFactory linkFactory) {
        this.url = url;
        this.webhookUrl = webhookUrl;
        this.noticeNum = noticeNum;
        this.noticeList = noticeList;
        this.tag = tag;
        this.title = title;
        this.date = date;
        this.link = link;
        this.linkFactory = linkFactory;
    }
}
