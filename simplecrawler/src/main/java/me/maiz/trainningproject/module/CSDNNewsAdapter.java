package me.maiz.trainningproject.module;

import me.maiz.trainningproject.core.CrawlConfig;
import me.maiz.trainningproject.dal.NewsRepo;
import me.maiz.trainningproject.dal.impl.NewsRepoImpl;
import me.maiz.trainningproject.model.Page;
import me.maiz.trainningproject.model.News;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CSDNNewsAdapter extends AbstractAdapter {

    List<News> techNews = new ArrayList<>();

    NewsRepo newsRepo = new NewsRepoImpl();


    @Override
    public CrawlConfig getConfig() {
        return CrawlConfig.builder().crawlTimesMax(3).seedsUrls(new String[]{"https://blog.csdn.net/"}).build();
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
            techNews.add(new News(title,intro,url));
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
            newsRepo.save(techNews,"默认");
        } catch (IOException e) {
            logger.error("写入新闻时失败",e);
        }
    }
}
