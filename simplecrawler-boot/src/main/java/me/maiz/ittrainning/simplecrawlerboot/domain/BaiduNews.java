package me.maiz.ittrainning.simplecrawlerboot.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BaiduNews {

    private String title;

    private String url;

    public String toString(){
        return title+":"+url;
    }
}
