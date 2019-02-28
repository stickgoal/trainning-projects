package me.maiz.project.mblog.web;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.lang.GeoLocation;
import com.drew.metadata.Metadata;
import com.drew.metadata.MetadataException;
import com.drew.metadata.exif.ExifSubIFDDirectory;
import com.drew.metadata.exif.GpsDirectory;
import com.drew.metadata.jpeg.JpegDirectory;
import me.maiz.project.mblog.component.BaiduAIClient;
import me.maiz.project.mblog.domain.Photo;
import me.maiz.project.mblog.service.PhotoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class PhotoController {

    private static final Logger logger = LoggerFactory.getLogger(PhotoController.class);

    @Autowired
    private PhotoService photoService;

    @RequestMapping("photo")
    public String toPhoto(ModelMap modelMap){
        //拉取照片
        final List<Photo> photos = photoService.load(0, 10);
        logger.info("{}",photos);
        modelMap.put("photos",photos);
        return "album";
    }

    @RequestMapping(value="upload",method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> upload(MultipartFile file,String memo) throws ImageProcessingException {
        logger.info(file.getOriginalFilename() + " :: "+memo);

         Photo photo = photoService.upload(file, memo);

        Map<String,Object> result = new HashMap<>();
        result.put("success",true);
        result.put("data",photo);
        return result;
    }



}
