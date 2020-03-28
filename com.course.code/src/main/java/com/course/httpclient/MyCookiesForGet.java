package com.course.httpclient;

import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @author 86186
 * @date 2020/3/22 21:46
 * @Description
 */
public class MyCookiesForGet {

    private String url;
    private ResourceBundle bundle;
    private CookieStore cookieStore;

    //读取配置文件
    @BeforeTest
    public void beforeTest(){
       bundle = ResourceBundle.getBundle("application");
       url = bundle.getString("test.url");
    }

    @Test
    public void getCookiesTest() throws IOException {
        String result;
        String uri = bundle.getString("getCookies.uri");
        //从配置文件拼接测试的URL
        String testUrl=this.url+uri;
        //测试请求URL
        HttpGet httpGet = new HttpGet(testUrl);
        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpResponse response = httpClient.execute(httpGet);
        result=EntityUtils.toString(response.getEntity(),"UTF-8");
        System.out.println(result);

        //获取Cookie信息
        this.cookieStore = httpClient.getCookieStore();
        List<Cookie> cookies = cookieStore.getCookies();
        for (Cookie cookie : cookies) {
            String name = cookie.getName();
            String value = cookie.getValue();
            System.out.println("cookie的name="+name+" "+"value="+value);
        }
    }

    @Test(dependsOnMethods = {"getCookiesTest"})
    public void getWithCookiesTest() throws IOException {
        String uri = bundle.getString("get.with.cookies.uri");
        String testUrl = this.url+uri;
        HttpGet httpGet = new HttpGet(testUrl);
        DefaultHttpClient httpClient = new DefaultHttpClient();
        //设置cookies
        httpClient.setCookieStore(this.cookieStore);
        HttpResponse response = httpClient.execute(httpGet);
        //获取状态码
        int statusCode = response.getStatusLine().getStatusCode();
        System.out.println("状态码："+statusCode);
        if (statusCode==200){
            String result=EntityUtils.toString(response.getEntity(),"utf-8");
            System.out.println(result);
        }
    }
}
