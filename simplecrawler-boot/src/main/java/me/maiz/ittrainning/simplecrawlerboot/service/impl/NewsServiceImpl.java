package me.maiz.ittrainning.simplecrawlerboot.service.impl;

import me.maiz.ittrainning.simplecrawlerboot.dal.NewsRepo;
import me.maiz.ittrainning.simplecrawlerboot.domain.News;
import me.maiz.ittrainning.simplecrawlerboot.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {
    @Autowired
    private NewsRepo newsRepo;

    @Override
    public List<News> query(int userId, int pageIdx, int pageSize) {
        Page<News> all = newsRepo.findAll(PageRequest.of(pageIdx, pageSize));
        return all.getContent();
    }
}
