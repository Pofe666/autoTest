package com.course.tesng.suite;

import org.testng.annotations.Test;

/**
 * @author 86186
 * @date 2020/3/21 21:08
 * @Description
 */
public class PayTest {
    @Test(enabled = false)
    public void pay(){
        System.out.println("支付成功啦");
    }
}
