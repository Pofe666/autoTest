package com.course.tesng.suite;

import org.testng.annotations.*;

/**
 * @author 86186
 * @date 2020/3/21 21:07
 * @Description
 */
public class SuiteConfig {
    @BeforeSuite
    public void beforeSuite(){
        System.out.println("beforeSuite运行啦");
    }
    @AfterSuite
    public void afterSuite(){
        System.out.println("afterSuite运行啦");
    }
    @BeforeTest
    public void beforeTest(){
        System.out.println("beforeTest运行啦");
    }
    @AfterTest
    public void afterTest(){
        System.out.println("afterTest运行啦");
    }

    @BeforeGroups("server")
    public void beforeGroupOnServer(){
        System.out.println("这是服务端运行之前执行的方法！！！！");
    }
    @AfterGroups("server")
    public void afterGroupOnServer(){
        System.out.println("这是服务端运行之后执行的方法！！！");
    }

    @BeforeGroups("client")
    public void beforeGroupOnClient(){
        System.out.println("beforeGroup-------");
    }
    @AfterGroups("client")
    public void afterGroupOnClient(){
        System.out.println("afterGroup-------------");
    }
}
