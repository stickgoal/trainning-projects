package me.maiz.project.mblog.web;

import com.kennycason.kumo.CollisionMode;
import com.kennycason.kumo.WordCloud;
import com.kennycason.kumo.WordFrequency;
import com.kennycason.kumo.bg.CircleBackground;
import com.kennycason.kumo.font.scale.SqrtFontScalar;
import com.kennycason.kumo.nlp.FrequencyAnalyzer;
import com.kennycason.kumo.palette.ColorPalette;
import me.maiz.project.mblog.common.AppConstants;
import me.maiz.project.mblog.dal.BlogRepo;
import me.maiz.project.mblog.domain.Blog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.awt.*;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
public class IndexController {

    @Autowired
    private BlogRepo blogRepo;

    @RequestMapping("index")
    public String index(ModelMap modelmap, @RequestParam(required = false, defaultValue = "0") Integer pageIdx, @RequestParam(required = false, defaultValue = "5") Integer pageSize) {
        modelmap.put("currIndex", pageIdx);
        PageRequest pageRequest = PageRequest.of(pageIdx, pageSize);
        Page<Blog> blogPage = blogRepo.findAll(pageRequest);
        modelmap.addAttribute("blogPage", blogPage);
        return "index";
    }

    @RequestMapping("detail")
    public String detail(int blogId, ModelMap modelmap) {
        Optional<Blog> blog = blogRepo.findById(blogId);
        System.out.println(blog.get());
        modelmap.addAttribute("blog", blog.get());
        return "detail";
    }

    @RequestMapping(value = "comment", method = RequestMethod.POST)
    @ResponseBody
    public String comment(String comment) {
        return "";
    }

    @RequestMapping("testd")
    public String testDefaultData(ModelMap modelMap, @RequestParam(required = false, defaultValue = "user") String userType) {
        modelMap.put("userType", userType);
        return "testDefaultValue";
    }


    public String wordCloud() throws IOException {

        final FrequencyAnalyzer frequencyAnalyzer = new FrequencyAnalyzer();
        final List<WordFrequency> wordFrequencies = frequencyAnalyzer.load(AppConstants.BLOG_WORD_CLOUD_TEXT_FILE);
        final Dimension dimension = new Dimension(600, 600);
        final WordCloud wordCloud = new WordCloud(dimension, CollisionMode.PIXEL_PERFECT);
        wordCloud.setPadding(2);
        wordCloud.setBackground(new CircleBackground(300));
        wordCloud.setColorPalette(new ColorPalette(new Color(0x4055F1), new Color(0x408DF1), new Color(0x40AAF1), new Color(0x40C5F1), new Color(0x40D3F1), new Color(0xFFFFFF)));
        wordCloud.setFontScalar(new SqrtFontScalar(10, 40));
        wordCloud.build(wordFrequencies);
        wordCloud.writeToFile("/tmp/myfile/wordCloud.png");
        return "wordCloud.png";
    }

}
