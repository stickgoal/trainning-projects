package me.maiz.project.mblog.web;

import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
public class PhotoController {

    private static final Logger logger = LoggerFactory.getLogger(PhotoController.class);

    @RequestMapping("photo")
    public String toPhoto(ModelMap modelMap){
//        modelMap.put("staticServerAddress",staticServerAddress);
        return "album";
    }

    @RequestMapping(value="upload",method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> upload(MultipartFile file,String memo){
        System.out.println(file.getOriginalFilename() + " :: "+memo);
        String fileName = null;
        try {
            fileName = getFileName(file.getOriginalFilename());
            String filePath = "/tmp/myfile/" + fileName;
            logger.info("path : {}",filePath);
            file.transferTo(new File(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Map<String,Object> result = new HashMap<>();
        result.put("success",true);
        result.put("data",fileName);
        return result;
    }

    private String getFileName(String originalFilename) {
        return format(new Date())+"_"+originalFilename;
    }

    private String format(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmssSSS");
        String formated = sdf.format(date);
        return formated;
    }


}
