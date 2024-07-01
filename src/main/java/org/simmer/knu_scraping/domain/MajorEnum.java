package org.simmer.knu_scraping.domain;

public enum MajorEnum {
    COMPUTER_SCIENCE_AND_ENGINEERING(
            "https://cse.knu.ac.kr/bbs/board.php?bo_table=sub5_1",
            "",
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
            "https://discord.com/api/webhooks/1255930388241448990/8ylzq2hw6ARc97OzTVafjzrTWozeax4qby_tZpICpkbQdaPnYzNBCsxKJ_f0MVRJMUgg",
            "td.num",
            "#btinForm > div > table > tbody > tr",
            null,
            "td.subject > a",
            "td.date",
            "td.subject > a",
            new KnuAcademic()
    );

    public final String url;
    public final String webhookUrl;
    public final String currentNoticeNum;
    public final String noticeList;
    public final String tag;
    public final String title;
    public final String date;
    public final String link;
    public final SiteFactory siteFactory;

    MajorEnum(String url, String webhookUrl, String currentNoticeNum, String noticeList, String tag, String title, String date, String link, SiteFactory siteFactory) {
        this.url = url;
        this.webhookUrl = webhookUrl;
        this.currentNoticeNum = currentNoticeNum;
        this.noticeList = noticeList;
        this.tag = tag;
        this.title = title;
        this.date = date;
        this.link = link;
        this.siteFactory = siteFactory;
    }
}
