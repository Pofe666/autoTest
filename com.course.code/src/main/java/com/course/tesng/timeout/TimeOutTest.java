package com.course.tesng.timeout;

import org.testng.annotations.Test;

import javax.swing.plaf.TableHeaderUI;

/**
 * @author 86186
 * @date 2020/3/22 10:54
 * @Description
 */
public class TimeOutTest {
    /**
     * 超时成功的测试
     */
    @Test(timeOut = 3000)
    public void testSuccess() throws InterruptedException {
        Thread.sleep(Long.parseLong("2000"));
    }

    /**
     * 超时失败的测试
     */
    @Test(timeOut = 2000)
    public void testFail() throws InterruptedException {
        Thread.sleep(3000);
    }
}
