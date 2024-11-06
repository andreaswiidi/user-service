package com.andreaswidii.user.controller.open;

import com.andreaswidii.user.beans.*;
import com.andreaswidii.user.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/public")
public class UserOpenController {
    private final UserService userService;

    public UserOpenController(UserService userService) {
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
    public ResponseWrapper<UserBean>loginUser(@RequestBody LoginReq request){
        return new ResponseWrapper<UserBean>()
                .success(userService.loginUser(request));
    }

//    @GetMapping
//    public ResponseWrapper<UserBean> getUser(){
//
//    }
}
