package com.course.utils;

import com.course.model.CaseInterfaceName;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @author 86186
 * @date 2020/3/25 13:53
 * @Description
 */
public class ConfigUrlFile {

    private static ResourceBundle bundle = ResourceBundle.getBundle("application", Locale.CHINA);

    public static String getUrl(CaseInterfaceName name){
        String address = bundle.getString("test.url");
        String uri = "";

        if (name.equals(name.GETUSERLIST)){
            uri = bundle.getString("getUserList.uri");
        }
        if (name.equals(name.GETUSERINFO)){
            uri = bundle.getString("getUserInfo.uri");
        }
        if (name.equals(name.LOGIN)){
            uri = bundle.getString("login.uri");
        }
        if (name.equals(name.ADDUSER)){
            uri = bundle.getString("addUser.uri");
        }
        if (name.equals(name.UPDATEUSERINFO)){
            uri = bundle.getString("updateUserInfo.uri");
        }
        String testUrl = address + uri;
        return testUrl;
    }



}
