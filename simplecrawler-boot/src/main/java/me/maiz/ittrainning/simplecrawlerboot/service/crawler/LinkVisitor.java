package me.maiz.ittrainning.simplecrawlerboot.service.crawler;

import com.google.common.collect.ImmutableList;
import me.maiz.ittrainning.simplecrawlerboot.domain.Page;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 请求访问器，负责访问指定请求
 */
@Component("linkVisitor")
public class LinkVisitor {

    private Logger logger = LoggerFactory.getLogger(LinkVisitor.class);

    /**
     * 访问指定的页面，并解析返回Page对象
     *
     * @param url
     * @return
     */
    public Page visit(String url) {
        logger.info(" 访问：{}",url);
        Page page = null;
        //准备Http请求
        RequestConfig config = RequestConfig.custom()
                .setConnectTimeout(50000)
                .setConnectionRequestTimeout(10000)
                .setSocketTimeout(50000)
                .build();

        List<Header> headers =new ArrayList<>();

        headers.add(header("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8"));
        headers.add(header("Accept-Encoding","gzip, deflate, br"));
        headers.add(header("Accept-Language","zh-CN,zh;q=0.9"));
        headers.add(header("Cache-Control","max-age=0"));

        CloseableHttpClient httpClient =HttpClients.custom()
                .setRetryHandler(new DefaultHttpRequestRetryHandler())
                .setDefaultRequestConfig(config)
                .setDefaultHeaders(headers)
                .setUserAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36")
                .build();
        HttpUriRequest request = RequestBuilder.get(url).build();

        //发起请求
        try {
            CloseableHttpResponse response = httpClient.execute(request);
            if(response.getStatusLine().getStatusCode()!= HttpStatus.SC_OK){
                logger.info("访问{}失败，状态码为{}",url,response.getStatusLine().getStatusCode());
            }else {
                HttpEntity entity = response.getEntity();
                byte[] responseBody = EntityUtils.toByteArray(entity);
                String contentType = entity.getContentType().getName();

                page = new Page(responseBody, url, contentType);
            }
        } catch (IOException e) {
            logger.warn("访问"+url+"时出现IO异常",e);
            throw new RuntimeException("访问"+url+"时出现IO异常");
        }finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                logger.warn("关闭"+url+"对应的HTTPClient时出现IO异常",e);

            }
        }

        return page;
    }

    private Header header(String name, String value) {
        return new BasicHeader(name,value);
    }


}
