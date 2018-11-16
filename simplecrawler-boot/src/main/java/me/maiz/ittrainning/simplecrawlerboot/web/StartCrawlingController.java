package me.maiz.ittrainning.simplecrawlerboot.web;

import me.maiz.ittrainning.simplecrawlerboot.service.crawler.MovieCrawlConfig;
import me.maiz.ittrainning.simplecrawlerboot.service.crawler.NewsCrawlConfig;
import me.maiz.ittrainning.simplecrawlerboot.service.crawler.SimpleMovieCrawler;
import me.maiz.ittrainning.simplecrawlerboot.service.crawler.SimpleNewsCrawler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@Controller
@RequestMapping("crawling")
public class StartCrawlingController {

    @Autowired
    private SimpleNewsCrawler simpleNewsCrawler;

    @Autowired
    private SimpleMovieCrawler simpleMovieCrawler;

    @RequestMapping("start")
    @ResponseBody
    public String start(String type){
        /*try {
            simpleNewsCrawler.startCrawlingWith(NewsCrawlConfig.THEPAPER_NEWS_CONFIG);
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        MovieCrawlConfig config = MovieCrawlConfig.builder().configName("时光网top100电影爬取").crawlTimesMax(10).seedsUrls(new String[]{"http://www.mtime.com/top/movie/top100/"}).build();
        try {
            simpleMovieCrawler.startCrawlingWith(config);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "redirect:/list.html";
    }



}
