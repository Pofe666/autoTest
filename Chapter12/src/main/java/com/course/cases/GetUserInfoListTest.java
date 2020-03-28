package com.course.cases;

import com.course.config.TestUrlConfig;
import com.course.model.GetUserInfoCase;
import com.course.model.GetUserListCase;
import com.course.model.User;
import com.course.utils.DataBaseUtils;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 86186
 * @date 2020/3/27 17:30
 * @Description
 */

public class GetUserInfoListTest {

    @Test(dependsOnGroups = "loginTrue", description = "获取性别为男的用户信息")
    public void getUserInfoList() {
        SqlSession sqlSession = null;
        try {
            sqlSession = DataBaseUtils.getSqlSession();
            GetUserListCase getUserListCase = sqlSession.selectOne("getUserListCase", 1);
            //发送请求获取结果
            JSONArray resultJson = getJsonResult(getUserListCase);
            Thread.sleep(5000);
            //验证结果
            List<User> userList = sqlSession.selectList(getUserListCase.getExpected(), getUserListCase);
            for (User u : userList) {
                System.out.println("获取的" + u.toString());
            }
            JSONArray userListJson = new JSONArray(userList);
            System.out.println("userList转换为JSONArray"+userListJson);
            Assert.assertEquals(userListJson.length(), resultJson.length());


            for (int i = 0; i < resultJson.length(); i++) {
                JSONObject expect = (JSONObject) resultJson.get(i);
                JSONObject actual = (JSONObject) userListJson.get(i);
                Assert.assertEquals(expect.toString(), actual.toString());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    private JSONArray getJsonResult(GetUserListCase getUserListCase) {
        String result = null;
        JSONArray jsonArray = null;
        try {
            HttpPost httpPost = new HttpPost(TestUrlConfig.getUserListUrl);
            JSONObject param = new JSONObject();
            param.put("userName", getUserListCase.getUserName());
            param.put("age", getUserListCase.getAge());
            param.put("sex", getUserListCase.getSex());
            httpPost.setHeader("content-type", "application/json");
            StringEntity entity = new StringEntity(param.toString(), "utf-8");
            httpPost.setEntity(entity);
            TestUrlConfig.defaultHttpClient.setCookieStore(TestUrlConfig.cookieStore);
            HttpResponse response = TestUrlConfig.defaultHttpClient.execute(httpPost);
            result = EntityUtils.toString(response.getEntity());
            jsonArray = new JSONArray(result);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonArray;
    }


}
