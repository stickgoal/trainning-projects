package me.maiz.project.mblog.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class PhotoController {

    @RequestMapping("photo")
    public String toPhoto(){
        return "album";
    }

    @RequestMapping(value="upload",method = RequestMethod.POST)
    @ResponseBody
    public String upload(MultipartFile photo,String memo){
        System.out.println("photo/upload");
        return "true";
    }


}
