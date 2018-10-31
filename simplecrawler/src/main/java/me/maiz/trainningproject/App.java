package me.maiz.trainningproject;

import me.maiz.trainningproject.core.SimpleCrawler;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {

        SimpleCrawler crawler = new SimpleCrawler();
        crawler.startCrawlingWith(new String[]{"http://www.baidu.com"});


    }
}
