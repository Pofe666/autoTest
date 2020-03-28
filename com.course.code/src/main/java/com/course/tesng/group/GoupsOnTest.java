package com.course.tesng.group;

import org.testng.annotations.AfterGroups;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;

import javax.xml.namespace.QName;

/**
 * @author 86186
 * @date 2020/3/21 21:55
 * @Description
 */
public class GoupsOnTest {


    @Test(groups = "server")
    public void groupOnserver1(){
        System.out.println("这是服务端server1测试组执行的方法！！！！");
    }
    @Test(groups = "server")
    public void goupOnserver2(){
        System.out.println("这是服务端server2测试组执行的方法！！！！");
    }
    @Test(groups = "client")
    public void groupOnClient1(){
        System.out.println("这是客户端client1测试组执行的方法！！！");
    }
    @Test(groups = "client")
    public void groupOnClient2(){
        System.out.println("这是客户端client2测试组执行的方法！！！");
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
