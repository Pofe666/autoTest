package com.course.tesng.multiThreadOnXml;

import org.testng.annotations.Test;

import java.lang.Thread;

/**
 * @author 86186
 * @date 2020/3/22 9:50
 * @Description
 */
public class multiTreadTest {
    @Test
    public void test1() {
        System.out.printf("Thread ID :%s%n", Thread.currentThread().getId());
    }

    @Test
    public void test2() {
        System.out.printf("Thread ID :%s%n", Thread.currentThread().getId());
    }

    @Test
    public void test3() {
        System.out.printf("Thread ID :%s%n", Thread.currentThread().getId());
    }

    @Test
    public void test4() {
        System.out.printf("Thread ID :%s%n", Thread.currentThread().getId());
    }
}
