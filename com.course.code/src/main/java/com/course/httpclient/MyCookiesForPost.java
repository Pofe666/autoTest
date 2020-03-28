package com.course.httpclient;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @author 86186
 * @date 2020/3/23 18:28
 * @Description
 */
public class MyCookiesForPost {

    private String url;
    private ResourceBundle bundle;
    private CookieStore cookiesStore;

    @BeforeTest
    public void readConfigFile(){
        //读取配置文件
        bundle = ResourceBundle.getBundle("application");
        url = bundle.getString("test.url");
    }

    @Test
    public void getCookiesTest() throws IOException {
        String uri = bundle.getString("getCookies.uri");
        String testUrl = this.url + uri;
        HttpGet httpGet = new HttpGet(testUrl);
        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpResponse response = httpClient.execute(httpGet);
        String result = EntityUtils.toString(response.getEntity(),"utf-8");
        System.out.println(result);

        //获取cookie的信息
        cookiesStore = httpClient.getCookieStore();
        List<Cookie> cookies = cookiesStore.getCookies();
        for (Cookie cookie : cookies) {
            String name = cookie.getName();
            String value = cookie.getValue();
            System.out.println("cookie 的name="+name+" "+"value="+value);
        }
    }

    @Test(dependsOnMethods = {"getCookiesTest"})
    public void postMethodTest() throws IOException {
        String uri = bundle.getString("post.with.cookies.uri");
        String testUrl = this.url + uri;
        //创建一个client对象
        DefaultHttpClient httpClient = new DefaultHttpClient();
        //创建post方法
        HttpPost httpPost = new HttpPost(testUrl);
        //设置参数，json对象
        JSONObject param = new JSONObject();
        param.put("name","huhansan");
        param.put("age","18");
        //设置请求头
        httpPost.setHeader("Content-Type","application/json;charset:utf-8");
        //把参数添加到方法中
        StringEntity entity = new StringEntity(param.toString(),"utf-8");
        httpPost.setEntity(entity);
        //设置Cookies信息
        httpClient.setCookieStore(this.cookiesStore);
        //执行方法
        HttpResponse response = httpClient.execute(httpPost);
        System.out.println(response);
        String result = EntityUtils.toString(response.getEntity(),"utf-8");
        System.out.println(result);

        JSONObject resultJson = new JSONObject(result);
        String code = (String) resultJson.get("code");
        String message = (String) resultJson.get("message");
        Assert.assertEquals(0,0);
        Assert.assertEquals("返回成功","返回成功");
    }
}
