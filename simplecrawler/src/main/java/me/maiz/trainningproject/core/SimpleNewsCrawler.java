package me.maiz.trainningproject.core;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import me.maiz.trainningproject.dal.NewsRepo;
import me.maiz.trainningproject.dal.impl.NewsRepoImpl;
import me.maiz.trainningproject.model.Page;
import me.maiz.trainningproject.model.News;
import me.maiz.trainningproject.module.Adapter;
import me.maiz.trainningproject.module.NewsCrawlConfig;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.SocketException;
import java.util.List;
import java.util.Set;

/**
 * 简单爬虫实现
 */
public class SimpleNewsCrawler {

    private static  final Logger logger = LoggerFactory.getLogger(SimpleNewsCrawler.class);


    //链接访问器
    private LinkVisitor visitor = new LinkVisitor();

    //页面解析器
    private Parser parser = new Parser();

    private List<News> newsList = Lists.newArrayList();

    private NewsRepo newsRepo = new NewsRepoImpl();


    /**
     * 使用指定的种子开始爬取
     */
    public void startCrawlingWith(NewsCrawlConfig config) throws IOException {
        LinkStore linkStore = new LinkStore(config.getSeedsUrls());

        while(canCrawl(linkStore,config.getCrawlTimesMax())){
            String toVisitURL = linkStore.pollUnVisited();
            logger.debug("访问：{}",toVisitURL);
            Page page = null;
            try {
                page = visitor.visit(toVisitURL);

            //得到指定的内容
            if(page != null) {
                Elements elements= parser.getTargetElements(page,config.getTargetElementSelector());
                logger.info("获得内容条数{}",elements.size());
                for (Element e : elements) {
                    Elements titleElement = e.select(config.getTitleSelector());
                    if(titleElement!=null&& titleElement.size()>0) {
                        String title = titleElement.get(0).text();
                        String url = e.select(config.getUrlSelector()).get(0).absUrl("href");
                        Elements introElement = e.select(config.getIntroSelector());
                        String intro = null;
                        if (introElement != null && introElement.size() > 0) {
                            intro = introElement.get(0).text();
                        }
                        //获取内容页，目前不分页
                        Page contentPage = visitor.visit(url);
                        Element contentElement =  parser.getTargetElement(contentPage,config.getContentBodySelector());
                        News news = new News(title, intro, url);
                        if(contentElement!=null) {
                            news.setContent(contentElement.html());
                        }
                        newsList.add(news);
                    }
                }

            }
            }catch (RuntimeException e){
                logger.warn("访问出现异常",e);
                continue;
            }

            //添加新的URL
            Set<String> links = getNewLinks(page,config.getNewLinkSelector());

            linkStore.addUnvisitedAll(links);
            linkStore.addVisited(toVisitURL);
        }

       persist(config.getConfigName());
    }

    public Set<String> getNewLinks(Page page,String newLinkSelector) {
        if (Strings.isNullOrEmpty(newLinkSelector)){
            logger.info("无新增连接配置，只抓取本页面");
            return Sets.newHashSet();
        }
        return parser.getLinks(page,newLinkSelector);
    }

    public void persist(String configName) throws IOException {
        try {
            newsRepo.save(newsList,configName);
        } catch (IOException e) {
            logger.error("写入新闻时失败",e);
        }
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
