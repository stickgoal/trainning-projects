package me.maiz.ittrainning.simplecrawlerboot.web;

import com.google.common.eventbus.AsyncEventBus;
import me.maiz.ittrainning.simplecrawlerboot.common.Result;
import me.maiz.ittrainning.simplecrawlerboot.component.CrawlEvent;
import me.maiz.ittrainning.simplecrawlerboot.domain.News;
import me.maiz.ittrainning.simplecrawlerboot.service.NewsService;
import me.maiz.ittrainning.simplecrawlerboot.service.crawler.MovieCrawlConfig;
import me.maiz.ittrainning.simplecrawlerboot.service.crawler.NewsCrawlConfig;
import me.maiz.ittrainning.simplecrawlerboot.service.crawler.SimpleMovieCrawler;
import me.maiz.ittrainning.simplecrawlerboot.service.crawler.SimpleNewsCrawler;
import me.maiz.ittrainning.simplecrawlerboot.web.form.ResultQueryForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("crawling")
public class CrawlingController {

    @Autowired
    private SimpleNewsCrawler simpleNewsCrawler;

    @Autowired
    private SimpleMovieCrawler simpleMovieCrawler;

    @Autowired
    private AsyncEventBus asyncEventBus;

    @Autowired
    private NewsService newsService;


    @RequestMapping("start")
    public Result start(String type, int configId){

        asyncEventBus.post(new CrawlEvent(configId,type));

        return Result.success("接收成功");
    }

    @GetMapping("result")
    public Result getResult(ResultQueryForm resultQueryForm){
        try {
            List<News> news = newsService.query(resultQueryForm.getUserId(), resultQueryForm.getIndex(), resultQueryForm.getPageSize());
            return Result.success(news);
        }catch (Exception e){
            e.printStackTrace();
            return Result.generalFail();
        }
    }



}
