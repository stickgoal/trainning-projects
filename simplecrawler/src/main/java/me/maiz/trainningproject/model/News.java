package me.maiz.trainningproject.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.apache.http.util.EntityUtils;

import java.util.Date;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class News {
    @NonNull
    private String title;
    private String intro;
    @NonNull
    private String url;

    private String content;

    public News(String title,String intro,String url){
        this.title=title;
        this.intro=intro;
        this.url=url;
    }

    public String toString(){
        return new StringBuilder()
                .append("{\"title\":\"").append(title)
                .append("\",\"intro\":\"").append(intro)
                .append("\",\"url\":\"").append(url)
                .append("\",\"content\":\"").append(content)
                .append("\"}").toString();
    }
}
