package me.maiz.ittrainning.simplecrawlerboot.domain;

import javax.persistence.*;

@Table(name = "ss_news")
@Entity
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int newsId;

    private String site;

    private String title;

    private String intro;

    private String url;

    @Column(length = 10240)
    private String content;

    public News() {
    }

    public News(String title, String intro, String url, String site){
        this.title=title;
        this.intro=intro;
        this.url=url;
        this.site=site;
    }

    public int getNewsId() {
        return newsId;
    }

    public void setNewsId(int newsId) {
        this.newsId = newsId;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "News{" +
                "newsId=" + newsId +
                ", site='" + site + '\'' +
                ", title='" + title + '\'' +
                ", intro='" + intro + '\'' +
                ", url='" + url + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
