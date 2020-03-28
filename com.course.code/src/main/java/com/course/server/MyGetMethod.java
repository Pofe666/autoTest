package com.course.server;

import org.apache.logging.log4j.util.StringBuilders;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author 86186
 * @date 2020/3/23 23:25
 * @Description
 */
@RestController
public class MyGetMethod {
    /**
     * 获取cookies
     * @param response
     * @return
     */
    @RequestMapping(value="/getCookies",method = RequestMethod.GET)
    public String getCookies(HttpServletResponse response){
//        HttpServletRequest 装请求信息的类
//        HttpServletResponse 装相应信息的类
        Cookie cookie = new Cookie("login","true");
        response.addCookie(cookie);
        return "恭喜你获得cookies信息成功";
    }
    /*
    携带cookies信息才能访问的get请求
     */
    @RequestMapping(value = "/get/with/cookies",method = RequestMethod.GET)
    public String getWithCookies(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        if (Objects.isNull(cookies)){
            return "必须携带cookies请求";
        }
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("login")&&cookie.getValue().equals("true")){
                return "这是一个需要携带cookies信息才能访问的get请求";
            }
        }
        return "必须携带cookies请求";
    }

    /*
    携带参数访问的get请求方法一
     */
    @RequestMapping(value = "/get/with/param",method = RequestMethod.GET)
    public Map<String,Integer> getList(@RequestParam Integer start,@RequestParam Integer end){
        Map<String,Integer> myList = new HashMap<>();
        myList.put("锅包肉",48);
        myList.put("溜肉段",42);
        myList.put("农家小炒肉",32);
        return myList;
    }
      /*
    携带参数访问的get请求方法二
     */
    @RequestMapping(value = "/get/with/param/{start}/{end}",method = RequestMethod.GET)
    public Map MygetList(@PathVariable Integer start,@PathVariable Integer end){
        Map<String,Integer> myList = new HashMap<>();
        myList.put("锅包肉",48);
        myList.put("溜肉段",42);
        myList.put("农家小炒肉",32);
        return myList;
    }
}
