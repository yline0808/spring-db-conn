package com.tstory.yline.hellouser.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class HelloController {

    // url을 매핑
    @GetMapping("hello")
    public String hello(Model model){
        // data리턴 값으로 문자를 반환하면 viewResolver가 화면을 찾아서 처리
        model.addAttribute("data", "hello!!!");
        // viewName(hello.html)을 매핑
        return "hello";
    }

    // url을 매핑
    @GetMapping("test/isWork")
    public String test(Model model){
        // data리턴 값으로 문자를 반환하면 viewResolver가 화면을 찾아서 처리
        model.addAttribute("message", "이거 가능하군");
        // viewName(isWork.html)을 매핑
        return "test/isWork";
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model){
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam("name") String name){
        return "hello " + name;
    }

    @GetMapping("hello-api")
    @ResponseBody
    public MyInfo helloApi(
            @RequestParam("name") String name,
            @RequestParam("age") int age,
            @RequestParam("friendList") List<String> friendList
    ){
        MyInfo m = new MyInfo(name, age, friendList);
        return m;
    }

    static class MyInfo{
        private String name;
        private int age;
        private List<String> friendList;

        public MyInfo(String name, int age, List<String> friendList) {
            this.name = name;
            this.age = age;
            this.friendList = friendList;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }

        public List<String> getFriendList() {
            return friendList;
        }
    }
}