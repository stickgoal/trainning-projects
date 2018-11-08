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

@Component("infoQNewsAdapter")
public class InfoQNewsAdapter extends AbstractAdapter {

    NewsRepo newsRepo = new NewsRepoImpl();

    List<TechNews> techNews = new ArrayList<>();

    @Override
    public CrawlConfig getConfig() {
        return CrawlConfig.builder().crawlTimesMax(5).seedsUrl(new String[]{"http://www.infoq.com/cn/news"}).build();
    }

    @Override
    public void extractAnKeepContent(Page page) {
        Elements elements= parser.getTargetElements(page,".news_type_block");
        logger.info("获得内容条数{}",elements.size());
        for (Element e : elements) {
            Element link = e.select("h2>a").get(0);
            String title = link.text();
            String url = link.absUrl("href");
            String intro = e.select("p").get(0).text();
            techNews.add(new TechNews(title,intro,url));
        }
    }

    @Override
    public Set<String> getNewLinks(Page page) {
        return parser.getLinks(page,".load_more_articles>.blue");
    }

    @Override
    public void persist() throws IOException {
        try {
            newsRepo.save(techNews);
        } catch (IOException e) {
            logger.error("写入新闻时失败",e);
        }
    }
}
