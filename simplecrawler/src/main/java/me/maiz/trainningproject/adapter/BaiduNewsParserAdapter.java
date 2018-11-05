package me.maiz.trainningproject.adapter;

import me.maiz.trainningproject.core.CrawlConfig;
import me.maiz.trainningproject.core.Parser;
import me.maiz.trainningproject.core.SimpleCrawler;
import me.maiz.trainningproject.dal.NewsRepo;
import me.maiz.trainningproject.dal.impl.NewsRepoImpl;
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

public class BaiduNewsParserAdapter extends AbstractAdapter {

    List<BaiduNews> baiduNewsList = new ArrayList<>();

    private NewsRepo repo = new NewsRepoImpl();

    @Override
    public CrawlConfig getConfig() {
    return CrawlConfig.builder().crawlTimesMax(100).seedsUrl(new String[]{"http://news.baidu.com/"}).build();
    }

    @Override
    public void extractAnKeepContent(Page page) {
        Elements elements= parser.getTargetElements(page,"#pane-news a",".l-left-col a");
        logger.info("获得内容条数{}",elements.size());
        for (Element e : elements) {
            logger.info(e.text()+":"+e.attr("href"));
            baiduNewsList.add(new BaiduNews(e.text(),e.absUrl("href")));
        }
    }

    @Override
    public Set<String> getNewLinks(Page page) {
        return  parser.getLinks(page,".menu-list a");
    }

    @Override
    public void persist() throws IOException {
        repo.save(baiduNewsList);
    }
}
