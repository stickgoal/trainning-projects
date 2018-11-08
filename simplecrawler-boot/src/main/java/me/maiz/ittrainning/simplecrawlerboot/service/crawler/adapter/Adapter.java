package me.maiz.ittrainning.simplecrawlerboot.service.crawler.adapter;



import me.maiz.ittrainning.simplecrawlerboot.domain.Page;
import me.maiz.ittrainning.simplecrawlerboot.service.crawler.CrawlConfig;

import java.io.IOException;
import java.util.Set;

public interface Adapter {

    CrawlConfig getConfig();

    void extractAnKeepContent(Page page);

    Set<String> getNewLinks(Page page);

    void persist() throws IOException;
}
