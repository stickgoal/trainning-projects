参考资料：

- mustache
  - https://blog.csdn.net/wangwenjun69/article/details/45971563
  - https://www.cnblogs.com/JoannaQ/p/3462318.html
  - http://mustache.github.io/mustache.5.html
- jpa
  - https://www.ibm.com/developerworks/cn/opensource/os-cn-spring-jpa/index.html

  - https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repositories.core-concepts

  - 如何配合spring boot使用jpa时使用innoDB引擎
    > spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL55Dialect
  - 如何解决字符编码集的问题
    > create database qblog default charset utf8 COLLATE utf8_general_ci;
  - jpa执行更新时，使用@Modifying报无事务的问题,在repo的相关方法加上注解
    > @Transactional
    
- nginx
  - http://nginx.org/en/docs/windows.html
  - 简单静态服务器的配置
  ```config
  server {
          listen       8888;
          listen       localhost:8888;
          server_name  localhost  alias  static.server;
  
          location  / {
              root   /tmp/upload;
              index  index.html index.htm;
          }
      }
  ```
  
    
  项目
  
  基于springboot的个人博客系统
  
  知识点
  
  - *前端layui 
  - *springboot通用开发流程
    - springmvc
    - hibernate-jpa
    - web-service-dal
    - mustache模板引擎
  - *Springboot Interceptor开发
  - *富文本编辑器 simeditor
  - *博客热词词云 https://github.com/kennycason/kumo
  - *图片上传组件 及解决方案
    - 前端layui.upload https://www.layui.com/doc/modules/upload.html
    - spring文件上传支持
    - ng代理/tomcat vdir
  - 图像处理
    - *EXIF 读取及记录 https://github.com/drewnoakes/metadata-extractor
    - 百度图像识别API 获取信息及记录 http://ai.baidu.com/docs#/ImageClassify-API/141c7bfa
    - *图像timeline展示  https://www.layui.com/doc/element/timeline.html
  - 部署相关
    - spring-boot的打包
    - spring.profiles.active
    - linux基本命令
    - 部署依赖（JDK、MySQL、ng）
  - sm.ms 图床
  
  实训计划
  
    时间    	知识点                 	进度                
    day0  	需求宣讲、分组、数据库设计       	确认项目需求、完成数据库设计    
    day1  	springboot通用开发流程    	确认项目UI、完成登录与注册开发  
    day2  	spring interceptor开发	完成登录的interceptor开发
    day3-4	layui+simeditor     	完成博客撰写页\完成首页及详情页  
    day5-6	图片上传组件 及解决方案        	完成相册页             
    day7  	其他知识点提示             	集成各功能             
          	                    	                  
  
   day0任务
  
  - 确认项目需求
  - 完成数据库设计
  - 组名
  - 名牌
  
  
