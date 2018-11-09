package me.maiz.demo;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HTTP;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;

public class HttpClientDemo {
    public static void main(String[] args) {
        //创建HttpClient
        HttpClient hc = HttpClients.createDefault();
        //设置请求
        HttpUriRequest request = RequestBuilder.get("http://www.baidu.com").build();
        try {
            //获得响应
            HttpResponse response = hc.execute(request);
            //处理响应
            if(response.getStatusLine().getStatusCode()==HttpStatus.SC_OK){
                response.getEntity().writeTo(new FileOutputStream("c:/tmp/baidu.txt"));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                ((CloseableHttpClient) hc).close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
