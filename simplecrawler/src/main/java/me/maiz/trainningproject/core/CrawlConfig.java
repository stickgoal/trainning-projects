package me.maiz.trainningproject.core;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class CrawlConfig {
    /**
     * 抓取的最大限制
     */
    private int crawlTimesMax=5;
    /**
     * 起始URL数组
     */
    private String[] seedsUrls;
    /**
     * 内容选择器
     */
    private String contentSelector;
    /**
     * 新增连接选择器
     */
    private String newLinkSelector;

}
