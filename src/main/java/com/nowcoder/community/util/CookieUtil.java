package com.nowcoder.community.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class CookieUtil {
    public static String getValue(HttpServletRequest request,String name){
        if(request==null||name==null){//一定要有判空的意识
            throw new IllegalArgumentException("参数为空");
        }
        Cookie[] cookies=request.getCookies();
        if(cookies!=null) {//一定要有判空的意识
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(name)) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }
}
