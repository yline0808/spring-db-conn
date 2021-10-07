package com.tstory.yline.hellouser.controller;

import com.tstory.yline.hellouser.domain.UserInfo;
import com.tstory.yline.hellouser.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UserInfoController {

    private final UserInfoService userInfoService;

    @Autowired
    public UserInfoController(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    @GetMapping(value = "/users/new")
    public String createForm(){
        return "users/createUserForm";
    }

    @PostMapping(value = "/users/new")
    public String create(UserInfoForm form){
        UserInfo userInfo = new UserInfo(form.getName(), form.getEmail());
        userInfoService.join(userInfo);

        return "redirect:/";
    }

    @GetMapping(value = "/users")
    public String list(Model model){
        List<UserInfo> users = userInfoService.findUserInfos();
        model.addAttribute("users", users);
        return "users/userList";
    }
}
