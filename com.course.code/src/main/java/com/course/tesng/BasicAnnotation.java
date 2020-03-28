package com.course.tesng;

import org.testng.annotations.*;

/**
 * @author 86186
 * @date 2020/3/21 17:24
 * @Description 测试testng
 */
public class BasicAnnotation {
    @Test
    public void testCase1(){
        System.out.println("Test这是测试用例1");
    }
    @Test
    public  void testCase2(){ System.out.println("Test这是测试用例2");

    }
    @BeforeMethod
    public void BeforeMethod(){
        System.out.println("BeforeMethod是在方法之前运行");
    }
    @AfterMethod
    public  void AfterMethod(){
        System.out.println("AfterMethod在测试方法之后运行的");
    }

    @BeforeClass
    public  void BeforeClass(){
        System.out.println("BeforeClass在类之前运行");
    }

    @AfterClass
    public void AfterClass(){
        System.out.println("AfterClass在类之前运行");
    }
    @BeforeSuite
    public  void BeforeSuite(){
        System.out.println("BeforeSuite套件运行");
    }
    @AfterSuite
    public void AfterSuite(){
        System.out.println("AfterSuite套件运行");
    }

}
