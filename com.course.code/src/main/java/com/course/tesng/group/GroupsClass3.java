package com.course.tesng.group;

import org.testng.annotations.Test;

/**
 * @author 86186
 * @date 2020/3/21 22:20
 * @Description
 */
@Test(groups = "teacher")
public class GroupsClass3 {

    public void teacher1(){
        System.out.println("GroupsClass3中的teacher1运行！！！");
    }

    public void teacher2(){
        System.out.println("GroupsClass3中的teacher2运行！！！");
    }
}

