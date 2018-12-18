package me.maiz.ittrainning.simplecrawlerboot.service;

import me.maiz.ittrainning.simplecrawlerboot.domain.News;

import java.util.List;

public interface NewsService {

    List<News> query(int userId,int pageIdx,int pageSize);

}
