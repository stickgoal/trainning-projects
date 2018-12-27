package me.maiz.ittrainning.simplecrawlerboot.domain;

import javax.persistence.*;

@Table(name = "ss_config")
@Entity
public class Config {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer configId;
    private int userId;
    /**配置名称    */
    private String configName;
    /**抓取的最大限制  */
    private int crawlTimesMax = 5;
    /**起始URL   */
    private String seedsUrl;
    /** 内容选择器*/
    private String targetElementSelector;
    /* 新增连接选择器*/
    private String newLinkSelector;
    /*标题选择器*/
    private String titleSelector;
    /**简介选择器     */
    private String introSelector;
    /**URL选择器   */
    private String urlSelector;
    /*内容体选择器     */
    private String contentBodySelector;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getConfigName() {
        return configName;
    }

    public void setConfigName(String configName) {
        this.configName = configName;
    }

    public int getCrawlTimesMax() {
        return crawlTimesMax;
    }

    public void setCrawlTimesMax(int crawlTimesMax) {
        this.crawlTimesMax = crawlTimesMax;
    }

    public String getSeedsUrl() {
        return seedsUrl;
    }

    public void setSeedsUrl(String seedsUrl) {
        this.seedsUrl = seedsUrl;
    }

    public String getTargetElementSelector() {
        return targetElementSelector;
    }

    public void setTargetElementSelector(String targetElementSelector) {
        this.targetElementSelector = targetElementSelector;
    }

    public String getNewLinkSelector() {
        return newLinkSelector;
    }

    public void setNewLinkSelector(String newLinkSelector) {
        this.newLinkSelector = newLinkSelector;
    }

    public String getTitleSelector() {
        return titleSelector;
    }

    public void setTitleSelector(String titleSelector) {
        this.titleSelector = titleSelector;
    }

    public String getIntroSelector() {
        return introSelector;
    }

    public void setIntroSelector(String introSelector) {
        this.introSelector = introSelector;
    }

    public String getUrlSelector() {
        return urlSelector;
    }

    public void setUrlSelector(String urlSelector) {
        this.urlSelector = urlSelector;
    }

    public String getContentBodySelector() {
        return contentBodySelector;
    }

    public void setContentBodySelector(String contentBodySelector) {
        this.contentBodySelector = contentBodySelector;
    }

    public Integer getConfigId() {
        return configId;
    }

    public void setConfigId(Integer configId) {
        this.configId = configId;
    }


    @Override
    public String toString() {
        return "Config{" +
                "userId='" + userId + '\'' +
                "configName='" + configName + '\'' +
                ", crawlTimesMax=" + crawlTimesMax +
                ", seedsUrl='" + seedsUrl + '\'' +
                ", targetElementSelector='" + targetElementSelector + '\'' +
                ", newLinkSelector='" + newLinkSelector + '\'' +
                ", titleSelector='" + titleSelector + '\'' +
                ", introSelector='" + introSelector + '\'' +
                ", urlSelector='" + urlSelector + '\'' +
                ", contentBodySelector='" + contentBodySelector + '\'' +
                '}';
    }

}
