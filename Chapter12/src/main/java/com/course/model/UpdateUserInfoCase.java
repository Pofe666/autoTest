package com.course.model;

import lombok.Data;

/**
 * @author 86186
 * @date 2020/3/25 13:27
 * @Description
 */
@Data
public class UpdateUserInfoCase {

    private int id;
    private int userId;
    private String userName;
    private String password;
    private String sex;
    private String age;
    private String permission;
    private String isDelete;
    private String expected;



}
