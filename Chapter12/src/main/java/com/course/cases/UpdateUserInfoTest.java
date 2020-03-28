package com.course.cases;

import com.course.config.TestUrlConfig;
import com.course.model.UpdateUserInfoCase;
import com.course.model.User;
import com.course.utils.DataBaseUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * @author 86186
 * @date 2020/3/27 18:01
 * @Description
 */

public class UpdateUserInfoTest {

    @Test(dependsOnGroups = "loginTrue",description = "更改用户信息")
    public void updateUserInfo() throws IOException, InterruptedException {
        SqlSession session = DataBaseUtils.getSqlSession();
        UpdateUserInfoCase updateUserInfoCase = session.selectOne("updateUserInfoCase",1);
        System.out.println(updateUserInfoCase.toString());
        System.out.println(TestUrlConfig.updateUserInfoUrl);

        int result = getResult(updateUserInfoCase);
        User user = session.selectOne(updateUserInfoCase.getExpected(),updateUserInfoCase);

        Assert.assertNotNull(user);
        Assert.assertNotNull(result);

    }


    @Test(dependsOnGroups = "loginTrue",description = "删除用户信息")
    public void deleteUser() throws IOException, InterruptedException {
        SqlSession session = DataBaseUtils.getSqlSession();
        UpdateUserInfoCase updateUserInfoCase = session.selectOne("updateUserInfoCase",2);
        System.out.println(updateUserInfoCase.toString());
        System.out.println(TestUrlConfig.updateUserInfoUrl);


        int result = getResult(updateUserInfoCase);
        Thread.sleep(3000);
        User user = session.selectOne(updateUserInfoCase.getExpected(), updateUserInfoCase);
        Thread.sleep(1000);
        Assert.assertNotNull(user);
        Assert.assertNotNull(result);
    }

    public int getResult(UpdateUserInfoCase updateUserInfoCase){
        String result = null;
        try {
            HttpPost post = new HttpPost(TestUrlConfig.updateUserInfoUrl);
            JSONObject param = new JSONObject();
            param.put("id",updateUserInfoCase.getUserId());
            param.put("userName",updateUserInfoCase.getUserName());
            param.put("sex",updateUserInfoCase.getSex());
            param.put("age",updateUserInfoCase.getAge());
            param.put("permission",updateUserInfoCase.getPermission());
            param.put("isDelete",updateUserInfoCase.getIsDelete());

            post.setHeader("content-type","application/json");
            StringEntity entity = new StringEntity(param.toString(),"utf-8");
            post.setEntity(entity);

            TestUrlConfig.defaultHttpClient.setCookieStore(TestUrlConfig.cookieStore);

            HttpResponse response = TestUrlConfig.defaultHttpClient.execute(post);

            result = EntityUtils.toString(response.getEntity(),"utf-8");
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Integer.parseInt(result);
    }
}
