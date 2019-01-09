package me.maiz.project.mblog.web;

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

import javax.validation.constraints.Null;
import java.util.List;
import java.util.Optional;

@Controller
public class IndexController {

    @Autowired
    private BlogRepo blogRepo;

    @RequestMapping("index")
    public String index(ModelMap modelmap, @RequestParam(required = false,defaultValue = "0") Integer pageIdx, @RequestParam(required = false, defaultValue = "5") Integer pageSize) {
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
    public String testDefaultData(ModelMap modelMap,@RequestParam(required = false,defaultValue = "user") String userType){
        modelMap.put("userType",userType);
        return "testDefaultValue";
    }
}
