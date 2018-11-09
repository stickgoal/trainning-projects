package me.maiz.demo;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;

public class JsoupDemo {
    public static void main(String[] args) {
        try {
            File file = new File("c:/tmp/掘金 - juejin.im - 一个帮助开发者成长的社区.html");
            Document document = Jsoup.parse(file,"utf-8","https://juejin.im");
//在线访问   Document document = Jsoup.connect("https://juejin.im/").get();
            Elements elements = document.select(".info-box .title");
            for (Element e : elements){
                System.out.println("标题： "+e.text()+" 链接: "+e.absUrl("href"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
