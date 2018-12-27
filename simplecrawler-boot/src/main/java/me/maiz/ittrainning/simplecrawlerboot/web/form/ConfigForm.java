package me.maiz.ittrainning.simplecrawlerboot.web.form;

public class ConfigForm {
    /**用户ID*/
    private int userId;
    /**配置名称*/
    private  String configName;
    /**起始URL*/
    private String seedsUrl;
    /** 标题选择器*/
    private String titleSelector;
    /** 内容选择器*/
    private String targetElementSelector;
    /**简介选择器     */
    private String introSelector;
    /**URL选择器   */
    private String urlSelector;
    /*内容体选择器 （非必填）    */
    private String contentBodySelector;
    /* 新增连接选择器（非必填）*/
    private String newLinkSelector;

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

    public String getSeedsUrl() {
        return seedsUrl;
    }

    public void setSeedsUrl(String seedsUrl) {
        this.seedsUrl = seedsUrl;
    }

    public String getTitleSelector() {
        return titleSelector;
    }

    public void setTitleSelector(String titleSelector) {
        this.titleSelector = titleSelector;
    }

    public String getTargetElementSelector() {
        return targetElementSelector;
    }

    public void setTargetElementSelector(String targetElementSelector) {
        this.targetElementSelector = targetElementSelector;
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

    public String getNewLinkSelector() {
        return newLinkSelector;
    }

    public void setNewLinkSelector(String newLinkSelector) {
        this.newLinkSelector = newLinkSelector;
    }

    @Override
    public String toString() {
        return "ConfigForm{" +
                "userId=" + userId +
                ", configName='" + configName + '\'' +
                ", seedsUrl='" + seedsUrl + '\'' +
                ", titleSelector='" + titleSelector + '\'' +
                ", targetElementSelector='" + targetElementSelector + '\'' +
                ", introSelector='" + introSelector + '\'' +
                ", urlSelector='" + urlSelector + '\'' +
                ", contentBodySelector='" + contentBodySelector + '\'' +
                ", newLinkSelector='" + newLinkSelector + '\'' +
                '}';
    }
}
