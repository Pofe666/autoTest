package com.course.model;

import lombok.Data;

import java.lang.ref.PhantomReference;

/**
 * @author 86186
 * @date 2020/3/25 13:27
 * @Description
 */
@Data
public class LoginCase {

    private int id;
    private String userName;
    private String password;
    private String expected;
}
