package me.maiz.project.mblog.service.impl;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.lang.GeoLocation;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.MetadataException;
import com.drew.metadata.Tag;
import com.drew.metadata.exif.ExifSubIFDDirectory;
import com.drew.metadata.exif.GpsDirectory;
import com.drew.metadata.jpeg.JpegDirectory;
import lombok.extern.slf4j.Slf4j;
import me.maiz.project.mblog.component.BaiduAIClient;
import me.maiz.project.mblog.dal.PhotoRepo;
import me.maiz.project.mblog.domain.Photo;
import me.maiz.project.mblog.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class PhotoServiceImpl implements PhotoService {
    @Autowired
    private PhotoRepo photoRepo;

    @Override
    public Photo upload(MultipartFile file, String memo) {
        String fileName = null;
        Photo photo = new Photo();
        try {
            fileName = getFileName(file.getOriginalFilename());
            String filePath = "/tmp/myfile/" + fileName;
            final File dest = upload(file, memo, photo, fileName, filePath);

            parseExif(dest, photo);

            baiduAI(filePath, photo);

            photoRepo.save(photo);

        } catch (IOException | MetadataException | ImageProcessingException e) {
            throw new RuntimeException(e);
        }
        return photo;
    }

    @Override
    public List<Photo> load(int page, int limit) {
        final PageRequest pageRequest = PageRequest.of(page, limit, new Sort(Sort.Direction.DESC, "takeDate"));
        Page<Photo> all = photoRepo.findAll(pageRequest);
        return all.getContent();
    }


    private File upload(MultipartFile file, String memo, Photo photo, String fileName, String filePath) throws IOException {
        log.info("path : {}", filePath);
        photo.setPath(fileName);
//        photo.setDescription(memo);
        photo.setMemo(memo);
        final File dest = new File(filePath);
        file.transferTo(dest);
        return dest;
    }

    private void baiduAI(String filePath, Photo photo) {
        final List<String> tags = BaiduAIClient.getTagsOfImage(filePath);
        log.info("图片标签:{}", tags);
        photo.setTags(String.join(",", tags));
    }

    /**
     * 解析上传图片的EXIF信息
     *
     * @param dest
     * @param photo
     * @throws ImageProcessingException
     * @throws IOException
     * @throws MetadataException
     */
    private void parseExif(File dest, Photo photo) throws ImageProcessingException, IOException, MetadataException {
        Metadata metadata = ImageMetadataReader.readMetadata(dest);

        for (Directory d : metadata.getDirectories()
                ) {
            for (Tag tag : d.getTags())
                log.info("{}", tag);
            log.info("\n");
        }

        ExifSubIFDDirectory directory
                = metadata.getFirstDirectoryOfType(ExifSubIFDDirectory.class);
        Date date
                = directory.getDate(ExifSubIFDDirectory.TAG_DATETIME_ORIGINAL);
        int width
                = directory.getInt(ExifSubIFDDirectory.TAG_EXIF_IMAGE_WIDTH);
        String cameraModel = directory.getString(ExifSubIFDDirectory.TAG_MODEL);
        int height
                = directory.getInt(ExifSubIFDDirectory.TAG_EXIF_IMAGE_HEIGHT);
        log.info("拍摄日期：{} ,尺寸：{}x{}", date, width, height);

         GpsDirectory gpsDirectory = metadata.getFirstDirectoryOfType(GpsDirectory.class);
        final GeoLocation geoLocation = gpsDirectory.getGeoLocation();
        double latitude = geoLocation.getLatitude();
        double longitude = geoLocation.getLongitude();
        log.info("经度{},纬度{}",longitude,latitude);


//        //获取拍摄时间
//        ExifSubIFDDirectory subIFDDirectory = metadata.getFirstDirectoryOfType(ExifSubIFDDirectory.class);
//        Date date = subIFDDirectory.getDate(ExifSubIFDDirectory.TAG_DATETIME_ORIGINAL);
//        //曝光时间
//        long exposureTime =subIFDDirectory.getLong(ExifSubIFDDirectory.TAG_EXPOSURE_TIME);
//        //相机型号
//        String cameraModel = subIFDDirectory.getString(ExifSubIFDDirectory.TAG_MODEL);
//        //图片尺寸
//        JpegDirectory jpegDirectory =  metadata.getFirstDirectoryOfType(JpegDirectory.class);
//        int imageWidth =jpegDirectory.getImageWidth();
//        int imageHeight =jpegDirectory.getImageHeight();
//        //地理位置
//        GpsDirectory gpsDirectory = metadata.getFirstDirectoryOfType(GpsDirectory.class);
//        double latitude =-1;
//        double longitude  = -1;
//        if(gpsDirectory!=null) {
//            GeoLocation geoLocation = gpsDirectory.getGeoLocation();
//
//            if (geoLocation != null) {
//                //纬度
//                latitude = geoLocation.getLatitude();
//                //经度
//                longitude = geoLocation.getLongitude();
//            }
//        }
//
//        log.info("{} {} {} {}x{} @ {},{}",format(date),exposureTime,cameraModel,imageWidth,imageHeight,latitude,longitude);
//        photo.setCameraModel(cameraModel);
//        photo.setTakeDate(date);
//        photo.setWidth(imageWidth);
//        photo.setHeight(imageHeight);
    }

    private String getFileName(String originalFilename) {
        return format(new Date()) + "_" + originalFilename;
    }

    private String format(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmssSSS");
        return sdf.format(date);
    }
}
