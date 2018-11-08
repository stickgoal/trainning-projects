package me.maiz.ittrainning.simplecrawlerboot.domain;

import lombok.Data;
import lombok.NonNull;
import lombok.ToString;

import javax.persistence.*;

@Data
@Table(name = "ss_news")
@Entity
@ToString
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int newsId;

    private String site;

    @NonNull
    private String title;

    private String intro;
    @NonNull
    private String url;

    @Column(length = 10240)
    private String content;

    public News(String title,String intro,String url,String site){
        this.title=title;
        this.intro=intro;
        this.url=url;
        this.site=site;
    }


}
