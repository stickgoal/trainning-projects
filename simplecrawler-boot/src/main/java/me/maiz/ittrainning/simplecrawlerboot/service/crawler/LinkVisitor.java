package me.maiz.ittrainning.simplecrawlerboot.service.crawler;

import me.maiz.ittrainning.simplecrawlerboot.domain.Page;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;

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

        CloseableHttpClient httpClient =HttpClients.custom()
                .setRetryHandler(new DefaultHttpRequestRetryHandler())
                .setDefaultRequestConfig(config)
                .setUserAgent("Mozilla/5.0(Windows;U;Windows NT 5.1;en-US;rv:0.9.4)")
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

}
