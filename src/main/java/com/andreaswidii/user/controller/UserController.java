package com.andreaswidii.user.controller;

import com.andreaswidii.user.beans.LoginReq;
import com.andreaswidii.user.beans.RegisterReq;
import com.andreaswidii.user.beans.ResponseWrapper;
import com.andreaswidii.user.beans.UserBean;
import com.andreaswidii.user.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/open/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseWrapper<UserBean> registerUser(@RequestBody RegisterReq request) {
        return new ResponseWrapper<UserBean>()
                .success(userService.registerUser(request));

    }

    @PostMapping("/login")
    public ResponseWrapper<UserBean>LoginUser(@RequestBody LoginReq request){
        return new ResponseWrapper<UserBean>()
                .success(userService.loginUser(request));
    }
}
