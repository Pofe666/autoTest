package com.course.cases;

import com.course.config.TestUrlConfig;
import com.course.model.CaseInterfaceName;
import com.course.model.LoginCase;
import com.course.utils.ConfigUrlFile;
import com.course.utils.DataBaseUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * @author 86186
 * @date 2020/3/25 14:25
 * @Description
 */

public class LoginTest {
    @BeforeTest(groups = "loginTrue", description = "测试准备工作，获取HttpClient对象")
    public void beforeTest() {
        TestUrlConfig.getUserInfoUrl = ConfigUrlFile.getUrl(CaseInterfaceName.GETUSERINFO);
        TestUrlConfig.getUserListUrl = ConfigUrlFile.getUrl(CaseInterfaceName.GETUSERLIST);
        TestUrlConfig.updateUserInfoUrl = ConfigUrlFile.getUrl(CaseInterfaceName.UPDATEUSERINFO);
        TestUrlConfig.addUserUrl = ConfigUrlFile.getUrl(CaseInterfaceName.ADDUSER);
        TestUrlConfig.loginUrl = ConfigUrlFile.getUrl(CaseInterfaceName.LOGIN);
        TestUrlConfig.defaultHttpClient = new DefaultHttpClient();
    }

    @Test(groups = "loginTrue",description = "用户登陆成功接口测试")
    public void loginTrue() throws IOException {
        SqlSession sqlSession = null;
        String result = null;
        try {
            sqlSession = DataBaseUtils.getSqlSession();
            LoginCase loginCase = sqlSession.selectOne("loginCase", 1);
            System.out.println(loginCase.toString());
            System.out.println(TestUrlConfig.loginUrl);
            //发送请求
            result = getResult(loginCase);
            //验证结果
            Assert.assertEquals(loginCase.getExpected(), result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test(groups = "loginFalse", description = "用户登录失败接口测试")
    public void loginFalse() {

        SqlSession sqlSession = null;
        String result = null;
        try {
            sqlSession = DataBaseUtils.getSqlSession();
            LoginCase loginCase = sqlSession.selectOne("loginCase", 2);
            System.out.println(loginCase.toString());
            System.out.println(TestUrlConfig.loginUrl);
            //发送请求
            result = getResult(loginCase);
            Thread.sleep(5000);
            //验证结果
            Assert.assertEquals(loginCase.getExpected(), result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private String getResult(LoginCase loginCase) throws IOException {
        HttpPost post = new HttpPost(TestUrlConfig.loginUrl);
        JSONObject param = new JSONObject();
        param.put("userName", loginCase.getUserName());
        param.put("password", loginCase.getPassword());
        post.setHeader("Content-Type", "application/json");
        StringEntity entity = new StringEntity(String.valueOf(param), "utf-8");
        post.setEntity(entity);
        HttpResponse response = TestUrlConfig.defaultHttpClient.execute(post);
        String result = EntityUtils.toString(response.getEntity(),"utf-8");
//        TestUrlConfig.cookieStore = new BasicCookieStore();
//        TestUrlConfig.closeableHttpClient = HttpClients.custom().setDefaultCookieStore(TestUrlConfig.cookieStore).build();
        TestUrlConfig.cookieStore = TestUrlConfig.defaultHttpClient.getCookieStore();
        return result;
    }

}
