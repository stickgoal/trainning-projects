package me.maiz.project.mblog.web;

import me.maiz.project.mblog.dal.BlogRepo;
import me.maiz.project.mblog.domain.Blog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class IndexController {

    @Autowired
    private BlogRepo blogRepo;

    @RequestMapping("index")
    public String index(ModelMap modelmap){
        modelmap.put("name","lucas");
        List<Blog> blogs = blogRepo.findAll();
        System.out.println(blogs);
        modelmap.addAttribute("blogs",blogs);
        return "index";
    }

    @RequestMapping("detail")
    public String detail(int blogId,ModelMap modelmap){
        Optional<Blog> blog = blogRepo.findById(blogId);
        System.out.println(blog.get());
        modelmap.addAttribute("blog",blog.get());
        return "detail";
    }

}
