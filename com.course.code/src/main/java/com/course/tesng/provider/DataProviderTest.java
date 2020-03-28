package com.course.tesng.provider;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

/**
 * @author 86186
 * @date 2020/3/22 8:50
 * @Description
 */
public class DataProviderTest {
    @Test(dataProvider = "prov")
    public void test1(String name, int age) {
        System.out.println("test1方法 name=" + name + " " + "age=" + age);
    }
    @Test(dataProvider = "prov")
    public void test2(String name, int age) {
        System.out.println("test2方法 name=" + name + " " + "age=" + age);
    }

    @DataProvider(name = "prov")
    public Object[][] provTest(Method method) {
        Object[][] o = null;
        if (method.getName().equals("test1")) {
             o = new Object[][]{
                    {"zhangsan", 20},
                    {"lisi", 30}
            };
        }else if(method.getName().equals("test2")){
             o = new Object[][]{
                    {"王五",40},
                    {"赵六",50}
            };
        }
        return o;
    }
}
