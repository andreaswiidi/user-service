package com.andreaswidii.user.service;

import com.andreaswidii.user.beans.LoginReq;
import com.andreaswidii.user.beans.RegisterReq;
import com.andreaswidii.user.beans.UserBean;
import com.andreaswidii.user.models.Users;
import com.andreaswidii.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public UserBean registerUser(RegisterReq req){
        Optional<Users> userData = userRepository.findByEmailOrUsernameOrPhoneNumber(req.getEmail(), req.getUsername(), req.getPhoneNumber());
        if (userData.isPresent()){
            throw new RuntimeException("already exist");
        }

        Users user = new Users();
        user.setEmail(req.getEmail());
        user.setUsername(req.getUsername());
        user.setPhoneNumber(req.getPhoneNumber());
        user.setPassword(req.getPassword());
        user.setPassword(req.getPassword());

        user = userRepository.save(user);

        UserBean result = new UserBean();
        result.setId(user.getId());
        result.setEmail(user.getEmail());
        result.setUsername(user.getUsername());
        result.setPhoneNumber(user.getPhoneNumber());
        result.setCreatedDate(user.getCreatedDate());
        result.setUpdatedDate(user.getUpdatedDate());

        //TODO : send jwt using jwk

        return result;
    }

    public UserBean loginUser(LoginReq req){
        //TODO : send jwt using jwk and hash pass
        Optional<Users> userData = userRepository.findByEmailOrUsernameOrPhoneNumber(req.getEmail(), req.getUsername(), null);
        if (userData.isEmpty()){
            throw new RuntimeException("not found");
        }

        if (req.getPassword().equals(userData.get().getPassword())){
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

    public void isUserExist(RegisterReq req){
        //TODO : throw something which param that already exist
    }

}
