package me.maiz.ittrainning.simplecrawlerboot.service.crawler.adapter;


import me.maiz.ittrainning.simplecrawlerboot.dal.NewsRepo;
import me.maiz.ittrainning.simplecrawlerboot.dal.NewsRepoImpl;
import me.maiz.ittrainning.simplecrawlerboot.domain.Page;
import me.maiz.ittrainning.simplecrawlerboot.domain.TechNews;
import me.maiz.ittrainning.simplecrawlerboot.service.crawler.CrawlConfig;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component("csdnNewsAdapter")
public class CSDNNewsAdapter extends AbstractAdapter {

    List<TechNews> techNews = new ArrayList<>();

    NewsRepo newsRepo = new NewsRepoImpl();


    @Override
    public CrawlConfig getConfig() {
        return CrawlConfig.builder().crawlTimesMax(3).seedsUrl(new String[]{"https://blog.csdn.net/"}).build();
    }

    @Override
    public void extractAnKeepContent(Page page) {
        Elements elements= parser.getTargetElements(page,"#feedlist_id [data-type=blog]","#feedlist_id [data-type=top]");
        logger.info("获得内容条数{}",elements.size());
        for (Element e : elements) {
            Element link = e.select("h2>a").get(0);
            String title = link.text();
            String url = link.absUrl("href");
            String intro = e.select(".summary").get(0).text();
            techNews.add(new TechNews(title,intro,url));
        }
    }

    @Override
    public Set<String> getNewLinks(Page page) {
        return parser.getLinks(page,".nav_com li>a");
    }

    @Override
    public void persist() throws IOException {
        try {
            logger.info("共爬取了{}条数据",techNews.size());
            newsRepo.save(techNews);
        } catch (IOException e) {
            logger.error("写入新闻时失败",e);
        }
    }
}
