package me.maiz.trainningproject.core;

import me.maiz.trainningproject.adapter.Adapter;
import me.maiz.trainningproject.dal.NewsRepo;
import me.maiz.trainningproject.dal.PageRepository;
import me.maiz.trainningproject.dal.impl.NewsRepoImpl;
import me.maiz.trainningproject.dal.impl.PageFileRepositoryImpl;
import me.maiz.trainningproject.model.BaiduNews;
import me.maiz.trainningproject.model.Page;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 简单爬虫实现
 */
public class SimpleCrawler {

    private static  final Logger logger = LoggerFactory.getLogger(SimpleCrawler.class);


    //链接访问器
    private LinkVisitor visitor = new LinkVisitor();

    //页面解析器
    private Parser parser = new Parser();


    /**
     * 使用指定的种子开始爬取
     */
    public void startCrawlingWith(Adapter adapter) throws IOException {
        LinkStore linkStore = new LinkStore(adapter.getConfig().getSeedsUrl());

        while(canCrawl(linkStore,adapter.getConfig().getCrawlTimesMax())){
            String toVisitURL = linkStore.pollUnVisited();
            logger.debug("访问：{}",toVisitURL);
            Page page = visitor.visit(toVisitURL);



            //得到指定的内容
            if(page != null) {
                adapter.extractAnKeepContent(page);
            }

            //添加新的URL
            Set<String> links = adapter.getNewLinks(page);

            linkStore.addUnvisitedAll(links);
            linkStore.addVisited(toVisitURL);
        }

       adapter.persist();
    }

    /**
     * 是否爬取
     * @param linkStore
     * @param crawlTimesMax
     * @return
     */
    private boolean canCrawl(LinkStore linkStore, int crawlTimesMax) {
        return !linkStore.isUnVisitedUrlQueueEmpty()&&linkStore.countVisited()<=crawlTimesMax;
    }
}
