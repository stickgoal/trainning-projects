package me.maiz.project.mblog.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name="mblog_blog")
@AllArgsConstructor
@NoArgsConstructor
public class Blog {
    /**
     * ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int blogId;
    /**
     * 标题
     */
    @Column(length = 300)
    private String title;
    /**
     * 博客内容
     */
    @Column(length = 10240)
    private String blogContent;
    /**
     * 简介，从博客内容生成
     */
    private String shortDesc;
    /**
     * 标签，逗号分隔
     */
    private String tags;
    /**
     * 创建日期
     */
    private Date createDate;
    /**
     * 更新日期
     */
    private Date updateDate;



}
