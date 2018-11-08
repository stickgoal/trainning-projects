package me.maiz.trainningproject;

import me.maiz.trainningproject.core.SimpleNewsCrawler;
import me.maiz.trainningproject.module.CSDNNewsAdapter;
import me.maiz.trainningproject.core.SimpleCrawler;
import me.maiz.trainningproject.module.NewsCrawlConfig;

import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {

//        SimpleCrawler crawler = new SimpleCrawler();
//        crawler.startCrawlingWith(new String[]{"http://news.baidu.com/"});

        SimpleNewsCrawler simpleNewsCrawler = new SimpleNewsCrawler();
        try {
//            simpleNewsCrawler.startCrawlingWith(NewsCrawlConfig.CSDN_NEWS_CONFIG);
            NewsCrawlConfig crawlConfig = NewsCrawlConfig.builder().crawlTimesMax(10)
                    .seedsUrls(new String[]{"http://www.ftchinese.com"})
                    .newLinkSelector(null)
                    .targetElementSelector(new String[]{".items>.item-container"})
                    .titleSelector(".item-headline-link")
                    .urlSelector(".item-headline-link")
                    .introSelector(".item-lead")
                    .contentBodySelector("#story-body-container")
                    .build();
            simpleNewsCrawler.startCrawlingWith(NewsCrawlConfig.THEPAPER_NEWS_CONFIG);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
