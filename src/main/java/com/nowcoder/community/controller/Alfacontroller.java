package com.nowcoder.community.controller;

import com.nowcoder.community.Service.AlfaService;
import com.nowcoder.community.util.CommunityUtil;
import jdk.nashorn.internal.ir.RuntimeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@Controller
@RequestMapping("/alfa")
public class Alfacontroller {
    @Autowired
    private AlfaService alfaService;
    @RequestMapping("/hello")
    @ResponseBody
    public String sayhello(){
        return  "hello sprint boot.";
    }

    @RequestMapping("/data")
    @ResponseBody
    public String getData(){
        return alfaService.find();
    }

    @RequestMapping("/http")
    public void http(HttpServletRequest request, HttpServletResponse response){
        //获取请求数据
        System.out.println(request.getMethod());
        System.out.println(request.getServletPath());
        Enumeration<String> enumeration=request.getHeaderNames();
        while (enumeration.hasMoreElements()){
            String name=enumeration.nextElement();
            String value=request.getHeader(name);
            System.out.println(name+":"+value);

        }
        //返回响应数据
        response.setContentType("text/html;charset=utf-8");
        try ( PrintWriter writer=response.getWriter();){
            writer.write("<h1>牛客网</h1>");
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    //Get请求两种方式
    ///students?current=1&limit=20
    @RequestMapping(path = "/students",method = RequestMethod.GET)
    @ResponseBody
    public String students(@RequestParam(name = "current",required = false,defaultValue = "1") int current,
                           @RequestParam(name = "limit",required = false,defaultValue = "20") int limit){
        System.out.println(current);
        System.out.println(limit);
        return "students";
    }

    //students/123
    @RequestMapping(path = "/student/{id}",method = RequestMethod.GET)
    @ResponseBody
    public String getStudent(@PathVariable("id")int id){
        System.out.println(id);
        return "a student";
    }
    //Post请求
    @RequestMapping(path = "/student",method = RequestMethod.POST)
    @ResponseBody
    public String savestudent(String name,int age){
        System.out.println(name);
        System.out.println(age);
        return "success";
    }

    //响应Html数据
    @RequestMapping(path = "/teacher",method = RequestMethod.GET)
    public ModelAndView getteacher(){
        ModelAndView mav=new ModelAndView();
        mav.addObject("name","张三");
        mav.addObject("age","23");
        mav.setViewName("/demo/view");
        return mav;
    }

    @RequestMapping(path = "/school",method = RequestMethod.GET)
    public String getSchool(Model model){
        model.addAttribute("name","北京大学");
        model.addAttribute("age","88");
        return "/demo/view";
    }

    @RequestMapping(path = "/emp",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> getemp(){
        Map<String,Object> map=new HashMap<>();
        map.put("name","张三");
        map.put("age",23);
        map.put("sex","男");
        return map;
    }
    @RequestMapping(path = "/emps",method = RequestMethod.GET)
    @ResponseBody
    public List<Map<String,Object>> getemps(){
        List<Map<String,Object>> list=new ArrayList<>();
        Map<String,Object> map=new HashMap<>();
        map.put("name","张三");
        map.put("age",23);
        map.put("sex","男");
        list.add(map);

        map=new HashMap<>();
        map.put("name","李四");
        map.put("age",23);
        map.put("sex","男");
        list.add(map);

        map=new HashMap<>();
        map.put("name","老王");
        map.put("age",26);
        map.put("sex","男");
        list.add(map);
        return list;

    }

    //cookie示例

    @RequestMapping(path = "/cookie/set",method = RequestMethod.GET)
    @ResponseBody
    public String setCookies(HttpServletResponse response){
        //创建cookie
        Cookie cookie=new Cookie("code", CommunityUtil.generateUUID());

        //设置cookie生效范围
        cookie.setPath("/community/alfa");

        //设置cookie生效时间
        cookie.setMaxAge(10*60);

        response.addCookie(cookie);
        return "set cookie";
    }

    @RequestMapping(path = "/cookie/get", method = RequestMethod.GET)
    @ResponseBody
    public String getCookie(@CookieValue("code") String code) {
        System.out.println(code);
        return "get cookie";
    }

    //session示例
    @RequestMapping(path = "/session/set",method = RequestMethod.GET)
    @ResponseBody
    public String setSession(HttpSession session){//这里面的session通过springmvc注入，不用自己声明
        session.setAttribute("id",1);
        session.setAttribute("name","test");
        return "set session";
    }

    @RequestMapping(path = "/session/get",method = RequestMethod.GET)
    @ResponseBody
    public String getSession(HttpSession session){
        System.out.println(session.getAttribute("id"));
        System.out.println(session.getAttribute("name"));
        return "get session";
    }





}
