package me.maiz.ittrainning.simplecrawlerboot.service.crawler;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NewsCrawlConfig {

    public static final NewsCrawlConfig ZAOBAO_NEWS_CONFIG=NewsCrawlConfig.builder().crawlTimesMax(10)
            .configName("联合早报新闻抓取")
            .newLinkSelector(".nav a")
            .seedsUrls(new String[]{"http://www.zaobao.com/"})
            .targetElementSelector(new String[]{".post-item"})
            .urlSelector("a")
            .titleSelector(".post-title")
            .introSelector(".post-title")
            .contentBodySelector("article-content-container")
            .build();

    public static final   NewsCrawlConfig FT_CHINESE_CONFIG = NewsCrawlConfig.builder().crawlTimesMax(10)
            .configName("金融时报中文版新闻抓取")
            .seedsUrls(new String[]{"http://www.ftchinese.com"})
            .newLinkSelector(null)
            .targetElementSelector(new String[]{".items>.item-container"})
            .titleSelector(".item-headline-link")
            .urlSelector(".item-headline-link")
            .introSelector(".item-lead")
            .contentBodySelector("#story-body-container")
            .build();


    public static final NewsCrawlConfig CSDN_NEWS_CONFIG = NewsCrawlConfig.builder()
            .configName("CSDN博客抓取")
            .seedsUrls(new String[]{"https://blog.csdn.net/"})
            .crawlTimesMax(5)
            .targetElementSelector(new String[]{"#feedlist_id [data-type=blog]","#feedlist_id [data-type=top]"})
            .titleSelector("h2>a")
            .urlSelector("h2>a")
            .newLinkSelector(".nav_com li>a")
            .introSelector(".summary")
            .contentBodySelector(".rich_media_content")
            .build();

    public static final NewsCrawlConfig THEPAPER_NEWS_CONFIG = NewsCrawlConfig.builder()
            .configName("澎湃新闻抓取")
            .seedsUrls(new String[]{"https://www.thepaper.cn/"})
            .crawlTimesMax(1)
            .targetElementSelector(new String[]{".news_li",".pdtt_rt"})
            .titleSelector("h2>a")
            .urlSelector("h2>a")
            .newLinkSelector(null)
            .introSelector("p")
            .contentBodySelector(".news_txt")
            .build();


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
