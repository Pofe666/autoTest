package com.course.model;

import lombok.Data;

/**
 * @author 86186
 * @date 2020/3/25 13:26
 * @Description
 */
@Data
public class GetUserListCase {

    private String userName;
    private String age;
    private String sex;
    private String expected;
}
