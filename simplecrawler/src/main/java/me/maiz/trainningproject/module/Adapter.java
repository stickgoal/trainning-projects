package me.maiz.trainningproject.module;

import me.maiz.trainningproject.core.CrawlConfig;
import me.maiz.trainningproject.model.Page;

import java.io.IOException;
import java.util.Set;

public interface Adapter {

    CrawlConfig getConfig();

    void extractAnKeepContent(Page page);

    Set<String> getNewLinks(Page page);

    void persist() throws IOException;

}
