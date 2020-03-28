package com.course.tesng.exception;

import org.testng.annotations.Test;

/**
 * @author 86186
 * @date 2020/3/21 22:53
 * @Description
 */
public class ExceptedException {

    /**
     * 什么时候会用到异常测试？
     * 在我们期望结果为某一个异常的时候
     * 比如：我们传入了一些不合法的参数，程序抛出了异常
     * 也就说我们期望结果就是这个异常
     */
@Test(expectedExceptions = Exception.class)
    public void runTimeException (){
        System.out.println("这是我期望的异常结果");
        throw new RuntimeException();
}
}
