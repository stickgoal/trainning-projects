package me.maiz.ittrainning.simplecrawlerboot.service.crawler;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CrawlConfig {

    private int crawlTimesMax=5;

    private String[] seedsUrl;

}
