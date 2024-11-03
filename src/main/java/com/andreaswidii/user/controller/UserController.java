package com.andreaswidii.user.controller;

import com.andreaswidii.user.beans.*;
import com.andreaswidii.user.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.validation.Valid;
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
    public ResponseWrapper<RegisterResponse> registerUser(@RequestBody @Valid RegisterReq request)
            throws JsonProcessingException {
        userService.isUserExist(request);
        return new ResponseWrapper<RegisterResponse>()
                .success(userService.registerUser(request));

    }

    @PostMapping("/login")
    public ResponseWrapper<UserBean>LoginUser(@RequestBody LoginReq request){
        return new ResponseWrapper<UserBean>()
                .success(userService.loginUser(request));
    }
}
