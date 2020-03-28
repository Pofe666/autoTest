package com.course.model;

import lombok.Data;

/**
 * @author 86186
 * @date 2020/3/26 14:23
 * @Description
 */

@Data
public class User {
    private int id;
    private String userName;
    private String password;
    private String age;
    private String sex;
    private String permission;
    private String isDelete;
}

