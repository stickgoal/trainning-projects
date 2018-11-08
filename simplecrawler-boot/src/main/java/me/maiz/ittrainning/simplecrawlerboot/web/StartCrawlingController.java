package me.maiz.ittrainning.simplecrawlerboot.web;

import me.maiz.ittrainning.simplecrawlerboot.service.crawler.NewsCrawlConfig;
import me.maiz.ittrainning.simplecrawlerboot.service.crawler.SimpleNewsCrawler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@Controller
@RequestMapping("crawling")
public class StartCrawlingController {

    @Autowired
    private SimpleNewsCrawler simpleNewsCrawler;

    @RequestMapping("start")
    public String start(String type){
        /*try {
            simpleNewsCrawler.startCrawlingWith(NewsCrawlConfig.THEPAPER_NEWS_CONFIG);
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        return "redirect:/list.html";
    }



}
