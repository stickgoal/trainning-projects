package me.maiz.project.mblog.web;

import me.maiz.project.mblog.dal.BlogRepo;
import me.maiz.project.mblog.domain.Blog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.HtmlUtils;

import java.util.Date;

@Controller
public class WriteController {

    private Logger logger = LoggerFactory.getLogger(WriteController.class);

    @Autowired
    private BlogRepo blogRepo;


    @RequestMapping(value = "write", method = RequestMethod.GET)
    public String index(ModelMap modelmap) {
        modelmap.put("name", "lucas");
        return "write";
    }

    @RequestMapping(value = "write", method = RequestMethod.POST)
    public String doWrite(String title,String blogContent) {
        logger.info("{},{}",title,blogContent);
        Blog b = new Blog();
        b.setTitle(title);
        b.setBlogContent(blogContent);
        b.setShortDesc(blogContent.length()>=50?blogContent.substring(0,50):blogContent);
        b.setCreateDate(new Date());
        b.setUpdateDate(new Date());
        b.setTags("a,b,c");
        blogRepo.save(b);

        return "redirect:/index";
    }


}
