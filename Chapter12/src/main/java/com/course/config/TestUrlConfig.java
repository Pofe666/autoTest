package com.course.config;


import org.apache.http.client.CookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

/**
 * @author 86186
 * @date 2020/3/25 13:51
 * @Description
 */
public class TestUrlConfig {

    public static String loginUrl;
    public static String updateUserInfoUrl;
    public static String getUserListUrl;
    public static String getUserInfoUrl;
    public static String addUserUrl;
//    public static CloseableHttpClient closeableHttpClient;
    public static DefaultHttpClient defaultHttpClient;
    public static CookieStore cookieStore;
}
