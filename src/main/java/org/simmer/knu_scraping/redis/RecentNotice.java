package org.simmer.knu_scraping.redis;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Getter
@AllArgsConstructor
@RedisHash
public class RecentNotice {
    @Id
    private int major;
    private int recentNoticeNum;

    public void setRecentNoticeNum(int recentNoticeNum) {
        this.recentNoticeNum = recentNoticeNum;
    }
}
