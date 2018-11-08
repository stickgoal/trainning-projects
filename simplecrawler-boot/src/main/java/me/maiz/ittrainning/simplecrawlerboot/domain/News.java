package me.maiz.ittrainning.simplecrawlerboot.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "ss_news")
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int news_id;



}
