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