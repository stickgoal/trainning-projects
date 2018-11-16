package me.maiz.ittrainning.simplecrawlerboot.service.crawler;

import com.google.common.base.Joiner;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import me.maiz.ittrainning.simplecrawlerboot.dal.MovieRepo;
import me.maiz.ittrainning.simplecrawlerboot.dal.NewsRepo;
import me.maiz.ittrainning.simplecrawlerboot.domain.Movie;
import me.maiz.ittrainning.simplecrawlerboot.domain.News;
import me.maiz.ittrainning.simplecrawlerboot.domain.Page;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Set;

/**
 * 简单爬虫实现
 */
@Service("simpleMovieCrawler")
public class SimpleMovieCrawler {

    private static  final Logger logger = LoggerFactory.getLogger(SimpleMovieCrawler.class);

    @Autowired
    //链接访问器
    private LinkVisitor visitor = new LinkVisitor();

    @Autowired
    private MovieRepo movieRepo;

    @Autowired
    //页面解析器
    private Parser parser;

    private List<Movie> movies = Lists.newArrayList();

    /**
     * 使用指定的种子开始爬取
     */
    public void startCrawlingWith(MovieCrawlConfig config) throws IOException {
        LinkStore linkStore = new LinkStore(config.getSeedsUrls());

        while(canCrawl(linkStore,config.getCrawlTimesMax())){
            String toVisitURL = linkStore.pollUnVisited();
            logger.debug("访问：{}",toVisitURL);
            Page page = null;
            try {
                page = visitor.visit(toVisitURL);

            //得到指定的内容
            if(page != null) {
                Elements elements= parser.getTargetElements(page,".mov_con");
                logger.info("获得内容条数{}",elements.size());
                for (Element e : elements) {
                    Movie movie = new Movie();
                    //标题
                    Elements titlePart = e.select("h2");
                    //获取url和多个名字
                    if(titlePart!=null&& titlePart.size()>0) {
                       Element titleElement = titlePart.select("a").get(0);
                        movie.setUrl(titleElement.absUrl("href"));
                        movie.setName(titleElement.text());

                      }
                    //获取简介
                    movie.setQuote(e.select(".mt3").first().text());

                    Page contentPage = visitor.visit(movie.getUrl());

                    Element element = parser.getTargetElements(contentPage,".info_l").first();

                    //导演
                    List<String> directors = Lists.newArrayList();
                    for (Element d : element.select("[rel=v:directedBy]")){
                        directors.add(d.text());
                    }
                    movie.setDirector(directors);

                    //编剧
                    List<String> writers = Lists.newArrayList();
                    for(Element w : element.select("dd:eq(1) a")){
                        writers.add(w.text());
                    }

                    movie.setWriters(writers);


                    Element storyElement = element.select("[pan=M14_Movie_Overview_PlotsSummary]>.mt6").first();
                    movie.setStoryLine(storyElement.text());


                    Elements actorElements = parser.getTargetElements(contentPage,".main_actor");
                    //主演
                    List<String> stars = Lists.newArrayList();
                    for(Element s : actorElements.select("[rel=v:starring]")){
                        stars.add(s.text());
                    }
                    movie.setStars(stars);


                    movies.add(movie);
                    }

                }

            }catch (RuntimeException e){
                logger.warn("访问出现异常",e);
                continue;
            }

            //添加新的URL
            Set<String> links = getNewLinks(page,"#PageNavigator>a");

            linkStore.addUnvisitedAll(links);
            linkStore.addVisited(toVisitURL);
        }
        logger.info("爬取结果：{}",movies);

       persist(config.getConfigName());
    }

    private Set<String> getNewLinks(Page page,String newLinkSelector) {
        if (Strings.isNullOrEmpty(newLinkSelector)){
            logger.info("无新增连接配置，只抓取本页面");
            return Sets.newHashSet();
        }
        return parser.getLinks(page,newLinkSelector);
    }

    private void persist(String configName) throws IOException {
        movieRepo.saveAll(movies);
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
