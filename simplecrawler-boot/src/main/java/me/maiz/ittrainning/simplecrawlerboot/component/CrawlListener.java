package me.maiz.ittrainning.simplecrawlerboot.component;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.Subscribe;
import me.maiz.ittrainning.simplecrawlerboot.dal.ConfigRepo;
import me.maiz.ittrainning.simplecrawlerboot.domain.Config;
import me.maiz.ittrainning.simplecrawlerboot.service.crawler.SimpleNewsCrawler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Optional;

@Component
public class CrawlListener {
    private static final Logger logger = LoggerFactory.getLogger(CrawlListener.class);
    @Autowired
    private AsyncEventBus asyncEventBus;
    @Autowired
    private SimpleNewsCrawler simpleNewsCrawler;
    @Autowired
    private ConfigRepo configRepo;

    @PostConstruct
    public void init(){
        asyncEventBus.register(this);
    }

    @Subscribe
    public void statCrawl(CrawlEvent e){
        logger.info("接收到异步爬取请求,配置ID为 "+e.getConfigId());
        Optional<Config> byId = configRepo.findById(e.getConfigId());
        if(byId.isPresent()){
            Config config = byId.get();
            logger.info("配置详情 "+config);
            try {
                logger.info("开始爬取...");
                simpleNewsCrawler.startCrawlingWith(config);
                logger.info("爬取结束！");
            } catch (IOException e1) {
                logger.warn("爬取出错",e1);
            }
        }else{
            logger.warn("没有找到ID为{}的配置",e.getConfigId());
        }
    }

}
