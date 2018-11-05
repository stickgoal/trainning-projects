package me.maiz.trainningproject.dal.impl;

import com.google.common.io.Files;
import me.maiz.trainningproject.dal.NewsRepo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class NewsRepoImpl implements NewsRepo {

    private static final String FILE_LOCATION = "d:/tmp/news/";

    @Override
    public void save(List newsList) throws IOException {
        String fileName = FILE_LOCATION+LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddhhmmss"))+"_news.txt";
        File file = new File(fileName);
        if (!file.exists()){
            Files.createParentDirs(file);
            Files.touch(file);
        }
        BufferedWriter bw = Files.newWriter(file, Charset.defaultCharset());
        for (Object news : newsList) {
            bw.write(news.toString());
            bw.newLine();
        }

    }
}
