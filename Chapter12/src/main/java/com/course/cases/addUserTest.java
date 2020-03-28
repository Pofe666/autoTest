package com.course.cases;

import com.course.config.TestUrlConfig;
import com.course.model.User;
import com.course.model.addUserCase;
import com.course.utils.DataBaseUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * @author 86186
 * @date 2020/3/26 11:47
 * @Description
 */
public class addUserTest {

    @Test(dependsOnGroups = "loginTrue", description = "添加用户接口测试")
    public void addUser() {
        SqlSession sqlSession = null;
        try {
            sqlSession = DataBaseUtils.getSqlSession();
            addUserCase addUserCase = sqlSession.selectOne("addUserCase", 1);
            //发送请求,获取结果
            String result = getResult(addUserCase);
            //验证返回结果
            Thread.sleep(5000);
            User user = sqlSession.selectOne("addUser", addUserCase);
            Assert.assertEquals(addUserCase.getExpected(), result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public String getResult(addUserCase addUserCase) throws IOException {
        String result = null;
        HttpPost httpPost = new HttpPost(TestUrlConfig.addUserUrl);
        JSONObject param = new JSONObject();
        param.put("userName", addUserCase.getUserName());
        param.put("password", addUserCase.getPassword());
        param.put("sex", addUserCase.getSex());
        param.put("age", addUserCase.getAge());
        param.put("permission", addUserCase.getPermission());
        param.put("isDelete", addUserCase.getIsDelete());
        httpPost.setHeader("content-type", "application/json");
        StringEntity entity = new StringEntity(param.toString(), "utf-8");
        httpPost.setEntity(entity);
        TestUrlConfig.defaultHttpClient.setCookieStore(TestUrlConfig.cookieStore);
        HttpResponse response = TestUrlConfig.defaultHttpClient.execute(httpPost);
        result = EntityUtils.toString(response.getEntity(),"utf-8");
        System.out.println(result);
        return result;
}

}
