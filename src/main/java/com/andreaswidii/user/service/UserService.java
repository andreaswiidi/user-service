package com.andreaswidii.user.service;

import com.andreaswidii.user.beans.*;
import com.andreaswidii.user.exception.BadRequestException;
import com.andreaswidii.user.models.Users;
import com.andreaswidii.user.repository.UserRepository;
import com.andreaswidii.user.util.PasswordUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final AuthService authService;

    public UserService(UserRepository userRepository, AuthService authService) {
        this.userRepository = userRepository;
        this.authService = authService;
    }

    @Transactional
    public RegisterResponse registerUser(RegisterReq req){
        Optional<Users> userData = userRepository.findByEmailOrUsernameOrPhoneNumber(req.getEmail(), req.getUsername(), req.getPhoneNumber());
        if (userData.isPresent()){
            throw new BadRequestException("User Already Exist");
        }

        Users user = new Users();
        user.setEmail(req.getEmail());
        user.setUsername(req.getUsername());
        user.setPhoneNumber(req.getPhoneNumber());
        String hashedPassword = PasswordUtil.toHash(req.getPassword());
        user.setPassword(hashedPassword);

        user = userRepository.save(user);

        UserBean userBean = new UserBean();
        userBean.setId(user.getId());
        userBean.setEmail(user.getEmail());
        userBean.setUsername(user.getUsername());
        userBean.setPhoneNumber(user.getPhoneNumber());
        userBean.setCreatedDate(user.getCreatedDate());
        userBean.setUpdatedDate(user.getUpdatedDate());

        AuthJWTResponse jwtResponse = authService.getJWTFromAuthService(new AuthReq(userBean.getId(),
                userBean.getUsername()));

        String jwtToken = jwtResponse.getData().getJwtToken();
        String refreshToken = jwtResponse.getData().getRefreshToken();

        return new RegisterResponse(userBean,jwtToken,refreshToken);
    }

    public UserBean loginUser(LoginReq req){
        //TODO : send jwt using jwk and hash pass
        Optional<Users> userData = userRepository.findByEmailOrUsernameOrPhoneNumber(req.getEmail(), req.getUsername(), null);
        if (userData.isEmpty()){
            throw new RuntimeException("not found");
        }

        if (PasswordUtil.matches(req.getPassword(),userData.get().getPassword())){
            UserBean result = new UserBean();
            result.setId(userData.get().getId());
            result.setEmail(userData.get().getEmail());
            result.setUsername(userData.get().getUsername());
            result.setPhoneNumber(userData.get().getPhoneNumber());
            result.setCreatedDate(userData.get().getCreatedDate());
            result.setUpdatedDate(userData.get().getUpdatedDate());
            return result;
        }else {
            throw new RuntimeException("pass not match");
        }
    }

    public UserBean getUserId(Long userId){
        Optional<Users> dataUser = userRepository.findById(userId);
        if (dataUser.isEmpty()){
            throw new RuntimeException("not found");
        }
        UserBean result = new UserBean();
        result.setId(dataUser.get().getId());
        result.setEmail(dataUser.get().getEmail());
        result.setUsername(dataUser.get().getUsername());
        result.setPhoneNumber(dataUser.get().getPhoneNumber());
        result.setCreatedDate(dataUser.get().getCreatedDate());
        result.setUpdatedDate(dataUser.get().getUpdatedDate());
        return result;
    }

    public void isUserExist(RegisterReq req){
        Optional<Users> existingUser = userRepository.findByEmailOrUsernameOrPhoneNumber(req.getEmail(), req.getUsername(), req.getPhoneNumber());

        // List to collect already existing fields
        List<String> conflicts = new ArrayList<>();

        if (existingUser.isPresent()) {
            Users user = existingUser.get();
            if (user.getUsername().equals(req.getUsername())) {
                conflicts.add("username");
            }
            if (user.getEmail().equals(req.getEmail())) {
                conflicts.add("email");
            }
            if (user.getPhoneNumber().equals(req.getPhoneNumber())) {
                //maybe cek the format and size number
                conflicts.add("phone");
            }
        }

        if (!conflicts.isEmpty()) {
            throw new BadRequestException("Fields already taken: " + String.join(", ", conflicts),conflicts);
        }
    }

}
