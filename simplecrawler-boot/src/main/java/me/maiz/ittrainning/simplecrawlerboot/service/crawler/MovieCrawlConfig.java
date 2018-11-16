package me.maiz.ittrainning.simplecrawlerboot.service.crawler;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MovieCrawlConfig {

    /**
     * 配置名称
     */
    private String configName;
    /**
     * 抓取的最大限制
     */
    private int crawlTimesMax = 5;
    /**
     * 起始URL数组
     */
    private String[] seedsUrls;
    /**
     * 内容选择器
     */
    private String[] targetElementSelector;
    /**
     * 新增连接选择器
     */
    private String newLinkSelector;
    /**
     * 标题选择器
     */
    private String titleSelector;
    /**
     * 简介选择器
     */
    private String introSelector;
    /**
     * URL选择器
     */
    private String urlSelector;

    /**
     * 内容体选择器
     */
    private String contentBodySelector;
}
