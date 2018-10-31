package me.maiz.trainningproject.core;

import me.maiz.trainningproject.dal.PageRepository;
import me.maiz.trainningproject.dal.impl.PageFileRepositoryImpl;
import me.maiz.trainningproject.model.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    private PageRepository repository = new PageFileRepositoryImpl();

    /**
     * 使用指定的种子开始爬取
     * @param seeds
     */
    public void startCrawlingWith(String[] seeds){
        LinkStore linkStore = new LinkStore(seeds);

        while(canCrawl(linkStore)){
            String toVisitURL = linkStore.pollUnVisited();
            logger.debug("访问：{}",toVisitURL);
            Page page = visitor.visit(toVisitURL);

            Set<String> links = parser.getLinks(page,"img");
            linkStore.addUnvisitedAll(links);

            //得到指定的内容
            if(page != null && toVisitURL.contains("baidu.com") ) {
                repository.save(page);
            }
        }
    }

    /**
     * 是否爬取
     * @param linkStore
     * @return
     */
    private boolean canCrawl(LinkStore linkStore) {
        return !linkStore.isUnVisitedUrlQueueEmpty()&&linkStore.countVisited()<=1000;
    }
}
