package me.maiz.ittrainning.simplecrawlerboot.domain;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

//@Data
//@ToString
@Table(name = "ss_movie")
@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int movieId;

    //电影名字
    private String name;

    //别名
    @ElementCollection
    private List<String> otherName;

    //电影页面
    private String url;

    private String quote;

    //导演
    @ElementCollection
    private List<String> director;
    //编剧
    @ElementCollection
    private List<String> writers;
    //主演
    @ElementCollection
    private List<String> stars;
    //时长
    private String duration;
    //上映日期
    private String releaseDate;
    //故事情节
    @Column(length = 10240)
    private String storyLine;
    //评分
    private String rank;


    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getOtherName() {
        return otherName;
    }

    public void setOtherName(List<String> otherName) {
        this.otherName = otherName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    public List<String> getDirector() {
        return director;
    }

    public void setDirector(List<String> director) {
        this.director = director;
    }

    public List<String> getWriters() {
        return writers;
    }

    public void setWriters(List<String> writers) {
        this.writers = writers;
    }

    public List<String> getStars() {
        return stars;
    }

    public void setStars(List<String> stars) {
        this.stars = stars;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getStoryLine() {
        return storyLine;
    }

    public void setStoryLine(String storyLine) {
        this.storyLine = storyLine;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "movieId=" + movieId +
                ", name='" + name + '\'' +
                ", otherName=" + otherName +
                ", url='" + url + '\'' +
                ", quote='" + quote + '\'' +
                ", director=" + director +
                ", writers=" + writers +
                ", stars=" + stars +
                ", duration='" + duration + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                ", storyLine='" + storyLine + '\'' +
                ", rank='" + rank + '\'' +
                '}';
    }
}
