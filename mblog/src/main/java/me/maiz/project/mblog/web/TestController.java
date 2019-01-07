package me.maiz.project.mblog.web;

import me.maiz.project.mblog.domain.Blog;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;

@Controller
public class TestController  {

    @RequestMapping("blogs")
    public List<Blog> getUser(){
        Blog blog = new Blog();
        List<Blog> blogs = Arrays.asList(blog);
        return blogs;
    }
}
