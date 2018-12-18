package maiz.me.toyapplication.integration.api.dto;

public class News {
    private int newsId;

    private String site;

    private String title;

    private String intro;

    private String url;

    public News() {
    }

    public News(String title, String intro) {
        this.title = title;
        this.intro = intro;
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

    @Override
    public String toString() {
        return "News{" +
                "newsId=" + newsId +
                ", site='" + site + '\'' +
                ", title='" + title + '\'' +
                ", intro='" + intro + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
