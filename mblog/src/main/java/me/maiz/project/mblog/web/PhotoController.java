package me.maiz.project.mblog.web;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.MetadataException;
import com.drew.metadata.Tag;
import com.drew.metadata.exif.ExifSubIFDDescriptor;
import com.drew.metadata.exif.ExifSubIFDDirectory;
import com.drew.metadata.jpeg.JpegDescriptor;
import com.drew.metadata.jpeg.JpegDirectory;
import me.maiz.project.mblog.component.BaiduAIClient;
import me.maiz.project.mblog.domain.Photo;
import org.apache.commons.lang3.time.DateUtils;
import org.json.JSONObject;
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
import java.util.*;

@Controller
public class PhotoController {

    private static final Logger logger = LoggerFactory.getLogger(PhotoController.class);

    @RequestMapping("photo")
    public String toPhoto(ModelMap modelMap){
        //拉取照片
        return "album";
    }

    @RequestMapping(value="upload",method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> upload(MultipartFile file,String memo) throws ImageProcessingException {
        logger.info(file.getOriginalFilename() + " :: "+memo);
        Photo photo = new Photo();
        String fileName = null;
        try {
            fileName = getFileName(file.getOriginalFilename());
            String filePath = "/tmp/myfile/" + fileName;
            logger.info("path : {}",filePath);
            photo.setPath(filePath);
            photo.setDescription(memo);
            final File dest = new File(filePath);
            file.transferTo(dest);

            parseExif(dest,photo);

            baiduAI(filePath,photo);

            //save

        } catch (IOException e) {
            e.printStackTrace();
        } catch (MetadataException e) {
            e.printStackTrace();
        }
        Map<String,Object> result = new HashMap<>();
        result.put("success",true);
        result.put("data",fileName);
        return result;
    }

    private void baiduAI(String filePath, Photo photo) {
        final List<String> tags = BaiduAIClient.getTagsOfImage(filePath);
        logger.info("图片标签:{}",tags);
        photo.setTags(String.join(",",tags));
    }

    /**
     * 解析上传图片的EXIF信息
     * @param dest
     * @param photo
     * @throws ImageProcessingException
     * @throws IOException
     * @throws MetadataException
     */
    private void parseExif(File dest, Photo photo) throws ImageProcessingException, IOException, MetadataException {
        Metadata metadata = ImageMetadataReader.readMetadata(dest);
        //获取拍摄时间
        ExifSubIFDDirectory subIFDDirectory = metadata.getFirstDirectoryOfType(ExifSubIFDDirectory.class);
        Date date = subIFDDirectory.getDate(ExifSubIFDDirectory.TAG_DATETIME_ORIGINAL);
        //曝光时间
        long exposureTime =subIFDDirectory.getLong(ExifSubIFDDirectory.TAG_EXPOSURE_TIME);
        //相机型号
        String cameraModel = subIFDDirectory.getString(ExifSubIFDDirectory.TAG_MODEL);
        //图片尺寸
        JpegDirectory jpegDirectory =  metadata.getFirstDirectoryOfType(JpegDirectory.class);
        int imageWidth =jpegDirectory.getImageWidth();
        int imageHeight =jpegDirectory.getImageHeight();

        logger.info("{} {} {} {}x{}",format(date),exposureTime,cameraModel,imageWidth,imageHeight);
        photo.setCameraModel(cameraModel);
        photo.setTakeDate(date);
        photo.setWidth(imageWidth);
        photo.setHeight(imageHeight);
    }

    private String getFileName(String originalFilename) {
        return format(new Date())+"_"+originalFilename;
    }

    private String format(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmssSSS");
        return sdf.format(date);
    }


}
