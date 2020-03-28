package com.course.tesng.paramter;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * @author 86186
 * @date 2020/3/22 8:11
 * @Description
 */
public class ParameterTest {
    @Test
    @Parameters({"name","age"})
    public void test(String name,int age){
        System.out.println("name ="+name +" "+"age ="+age);
    }
}
