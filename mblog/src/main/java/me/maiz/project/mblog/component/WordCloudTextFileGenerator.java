package me.maiz.project.mblog.component;

import com.kennycason.kumo.CollisionMode;
import com.kennycason.kumo.WordCloud;
import com.kennycason.kumo.WordFrequency;
import com.kennycason.kumo.bg.CircleBackground;
import com.kennycason.kumo.font.scale.SqrtFontScalar;
import com.kennycason.kumo.nlp.FrequencyAnalyzer;
import com.kennycason.kumo.nlp.tokenizers.ChineseWordTokenizer;
import com.kennycason.kumo.palette.ColorPalette;
import lombok.extern.slf4j.Slf4j;
import me.maiz.project.mblog.common.AppConstants;
import me.maiz.project.mblog.dal.BlogRepo;
import me.maiz.project.mblog.domain.Blog;
import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class WordCloudTextFileGenerator implements InitializingBean {

    @Autowired
    private BlogRepo blogRepo;

    @Override
    public void afterPropertiesSet() throws Exception {
        //createWordCloudImage();
    }

    private void createWordCloudImage() throws IOException {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start("开始读取博客内容");
        log.info("开始读取博客内容");

        List<Blog> blogs = blogRepo.findAll();
        stopWatch.stop();

        stopWatch.start("生成博客内容文件");
        String allString = blogs.stream().map(blog -> blog.getBlogContent()).collect(Collectors.joining());
        String text = Jsoup.parse(allString).text();
        FileUtils.writeStringToFile(new File(AppConstants.BLOG_WORD_CLOUD_TEXT_FILE),text);
        stopWatch.stop();


        stopWatch.start("根据博客内容文件生成词云");

        final FrequencyAnalyzer frequencyAnalyzer = new FrequencyAnalyzer();
        frequencyAnalyzer.setWordFrequenciesToReturn(100);
//        frequencyAnalyzer.setMinWordLength(2);
        frequencyAnalyzer.setWordTokenizer(new ChineseWordTokenizer());

        final List<WordFrequency> wordFrequencies = frequencyAnalyzer.load(AppConstants.BLOG_WORD_CLOUD_TEXT_FILE);
        final Dimension dimension = new Dimension(600, 600);
        final WordCloud wordCloud = new WordCloud(dimension, CollisionMode.PIXEL_PERFECT);

        wordCloud.setPadding(2);
        wordCloud.setBackground(new CircleBackground(300));
        wordCloud.setBackgroundColor(new Color(1f,1f,1f,0f));
//        wordCloud.setBackground(new PixelBoundryBackground("/tmp/docker.png"));
        wordCloud.setColorPalette(new ColorPalette(new Color(0x4055F1), new Color(0x408DF1), new Color(0x40AAF1), new Color(0x40C5F1), new Color(0x40D3F1), new Color(0xFFFFFF)));
        wordCloud.setFontScalar(new SqrtFontScalar(5, 40));
        wordCloud.build(wordFrequencies);
        wordCloud.writeToFile("/tmp/myfile/wordCloud.png");
        stopWatch.stop();

        log.info("{}",stopWatch.prettyPrint());
    }
}
