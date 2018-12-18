package maiz.me.toyapplication.integration.api.dto;

public class CrawlConfig {

    private int configId;

    private String configName;

    private String titleSelector;

    private String seedsUrl;

    public CrawlConfig() {
    }

    public CrawlConfig(String configName, String titleSelector, String seedsUrl) {
        this.configName = configName;
        this.titleSelector = titleSelector;
        this.seedsUrl = seedsUrl;
    }

    public String getConfigName() {
        return configName;
    }

    public void setConfigName(String configName) {
        this.configName = configName;
    }

    public String getTitleSelector() {
        return titleSelector;
    }

    public void setTitleSelector(String titleSeletor) {
        this.titleSelector = titleSeletor;
    }

    public String getSeedsUrl() {
        return seedsUrl;
    }

    public void setSeedsUrl(String seedsUrl) {
        this.seedsUrl = seedsUrl;
    }

    @Override
    public String toString() {
        return "CrawlConfig{" +
                "configName='" + configName + '\'' +
                ", titleSelector='" + titleSelector + '\'' +
                ", seedsUrl='" + seedsUrl + '\'' +
                '}';
    }

    public int getConfigId() {
        return configId;
    }

    public void setConfigId(int configId) {
        this.configId = configId;
    }
}
