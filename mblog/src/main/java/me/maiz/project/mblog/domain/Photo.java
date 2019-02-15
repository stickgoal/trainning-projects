package me.maiz.project.mblog.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "mblog_photo")
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Photo {
    /**
     * ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int photoId;
    //存储路径
    private String path;
    //用户输入的描述信息
    private String description;
    //拍摄日期
    private Date takeDate;
    //相机模型
    private String cameraModel;
    //宽度
    private int width;
    //高度
    private int height;
    //标签列表，逗号分隔，由百度AI的接口获得
    private String tags;

}
