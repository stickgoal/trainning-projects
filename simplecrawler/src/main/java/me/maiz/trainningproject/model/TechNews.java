package me.maiz.trainningproject.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class TechNews {
    private String title;
    private String intro;
    private String url;

    public String toString(){
        return new StringBuilder()
                .append("{\"title\":\"").append(title)
                .append("\",\"intro\":\"").append(intro)
                .append("\",\"url\":\"").append(url)
                .append("\"}").toString();
    }
}
