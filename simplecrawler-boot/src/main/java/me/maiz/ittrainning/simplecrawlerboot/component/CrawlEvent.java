package me.maiz.ittrainning.simplecrawlerboot.component;

public class CrawlEvent {
    private int configId;
    private String type;

    public CrawlEvent() {
    }

    public CrawlEvent(int configId, String type) {
        this.configId = configId;
        this.type = type;
    }

    public int getConfigId() {
        return configId;
    }

    public void setConfigId(int configId) {
        this.configId = configId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "CrawlEvent{" +
                "configId=" + configId +
                ", type='" + type + '\'' +
                '}';
    }
}
