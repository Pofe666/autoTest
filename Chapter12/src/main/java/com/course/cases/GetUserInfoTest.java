package com.course.cases;

import com.course.config.TestUrlConfig;
import com.course.model.GetUserInfoCase;
import com.course.model.User;
import com.course.utils.DataBaseUtils;
import lombok.Builder;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author 86186
 * @date 2020/3/27 15:23
 * @Description
 */
@Test(dependsOnGroups = "loginTrue", description = "获取性别为男的用户信息")
public class GetUserInfoTest {

    public void getUserInfoTest(){
        SqlSession sqlSession = null;
        try {
            sqlSession = DataBaseUtils.getSqlSession();
            GetUserInfoCase getUserInfoCase = sqlSession.selectOne("getUserInfoCase", 1);
            //发送请求，获取结果
            JSONArray resultJson = getResult(getUserInfoCase);
            Thread.sleep(5000);
            User user = sqlSession.selectOne(getUserInfoCase.getExpected(),getUserInfoCase);
            System.out.println("user的结果："+user);
            List userList = new ArrayList();
            userList.add(user);
            JSONArray userarray = new JSONArray(userList);
            System.out.println("userarray的结果是："+userarray);
            JSONArray casearray = new JSONArray(resultJson.getString(0));
            System.out.println("casearray的结果是："+userarray);
            Assert.assertEquals(userarray.toString(),casearray.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private JSONArray getResult(GetUserInfoCase getUserInfoCase){
        String result = null;
        JSONArray array = null;
        try {
            HttpPost httpPost = new HttpPost(TestUrlConfig.getUserInfoUrl);
            JSONObject param = new JSONObject();
            param.put("id",getUserInfoCase.getUserId());
            httpPost.setHeader("content-type","application/json");
            StringEntity entity = new StringEntity(param.toString(), "utf-8");
            httpPost.setEntity(entity);
            TestUrlConfig.defaultHttpClient.setCookieStore(TestUrlConfig.cookieStore);
            HttpResponse response =TestUrlConfig.defaultHttpClient.execute(httpPost);
            result = EntityUtils.toString(response.getEntity());
            List resultList = Arrays.asList(result);
            array = new JSONArray(resultList);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return array;
    }
}
