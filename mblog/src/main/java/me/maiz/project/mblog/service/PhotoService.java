package me.maiz.project.mblog.service;

import me.maiz.project.mblog.domain.Photo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PhotoService {
    /**
     * 上传
     * @param photo
     * @param memo
     * @return
     */
    Photo upload(MultipartFile photo,String memo);

    /**
     * 拉取照片
     * @param start
     * @param limit
     * @return
     */
    List<Photo>  load(int start,int limit);

}
