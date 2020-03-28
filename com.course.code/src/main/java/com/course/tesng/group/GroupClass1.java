package com.course.tesng.group;

import org.testng.annotations.Test;

/**
 * @author 86186
 * @date 2020/3/21 22:20
 * @Description
 */
@Test(groups = "stu")
public class GroupClass1 {
    public  void stu1(){
        System.out.println("GoupClass1中的stu1方法执行！！！");
    }

    public  void stu2(){
        System.out.println("GoupClass1中的stu2方法执行！！！");
    }
}
