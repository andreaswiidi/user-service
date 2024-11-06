package com.andreaswidii.user.controller;

import com.andreaswidii.user.beans.ResponseWrapper;
import com.andreaswidii.user.beans.UserBean;
import com.andreaswidii.user.exception.BadRequestException;
import com.andreaswidii.user.service.UserService;
import com.andreaswidii.user.util.JWTUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseWrapper<UserBean> getUser(){
        Long custId = JWTUtil.getUserId()
                .orElseThrow(() -> new BadRequestException("Not Valid Cust ID"));
        return new ResponseWrapper<UserBean>()
                .success(userService.getUserId(custId));
    }
}
