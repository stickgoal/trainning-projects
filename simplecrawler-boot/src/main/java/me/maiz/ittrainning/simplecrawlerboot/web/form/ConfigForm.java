package me.maiz.ittrainning.simplecrawlerboot.web.form;

public class ConfigForm {

    private  String configName;

    private String seedsUrl;

    private String titleSelector;


    public String getConfigName() {
        return configName;
    }

    public void setConfigName(String configName) {
        this.configName = configName;
    }

    public String getTitleSelector() {
        return titleSelector;
    }

    public void setTitleSelector(String titleSelector) {
        this.titleSelector = titleSelector;
    }

    @Override
    public String toString() {
        return "Config{" +
                "configName='" + configName + '\'' +
                ", titleSelector='" + titleSelector + '\'' +
                '}';
    }

    public String getSeedsUrl() {
        return seedsUrl;
    }

    public void setSeedsUrl(String seedsUrl) {
        this.seedsUrl = seedsUrl;
    }
}
