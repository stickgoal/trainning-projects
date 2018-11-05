package me.maiz.trainningproject;

import me.maiz.trainningproject.adapter.CSDNNewsAdapter;
import me.maiz.trainningproject.adapter.InfoQNewsAdapter;
import me.maiz.trainningproject.core.SimpleCrawler;

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

        SimpleCrawler crawlerTechNews = new SimpleCrawler();
        try {
            crawlerTechNews.startCrawlingWith(new CSDNNewsAdapter());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
