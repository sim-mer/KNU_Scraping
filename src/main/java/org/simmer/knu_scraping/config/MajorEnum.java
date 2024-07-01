package org.simmer.knu_scraping.config;

public enum MajorEnum {
    COMPUTER_SCIENCE_AND_ENGINEERING(
            "https://cse.knu.ac.kr/bbs/board.php?bo_table=sub5_1",
            "",
            "#fboardlist > div > table > tbody > tr:nth-child(1) > td.td_num2",
            "#fboardlist > div > table > tbody > tr",
            "td.td_subject > a",
            "td.td_subject > div > a",
            "td.td_datetime.hidden-xs",
            "td.td_subject > div > a"
    );

    public final String url;
    public final String webhookUrl;
    public final String currentNoticeNum;
    public final String noticeList;
    public final String tag;
    public final String title;
    public final String date;
    public final String link;

    MajorEnum(String url, String webhookUrl, String currentNoticeNum, String noticeList, String tag, String title, String date, String link) {
        this.url = url;
        this.webhookUrl = webhookUrl;
        this.currentNoticeNum = currentNoticeNum;
        this.noticeList = noticeList;
        this.tag = tag;
        this.title = title;
        this.date = date;
        this.link = link;
    }
}
