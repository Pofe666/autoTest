package com.course.httpclient;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.Test;

import java.io.IOException;


/**
 * @author 86186
 * @date 2020/3/22 16:02
 * @Description 测试HttpClient
 */
public class MyHttpClient {
    @Test
    public void test1() throws IOException {
        String result;
        HttpGet httpGet = new HttpGet("http://www.baidu.com");
        //这个用来执行get方法的
        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpResponse response = httpClient.execute(httpGet);
        result = EntityUtils.toString(response.getEntity(), "UTF-8");
        System.out.println(result);
    }
}
