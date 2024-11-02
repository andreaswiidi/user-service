package com.andreaswidii.user.repository;

import com.andreaswidii.user.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users,Long> {
    Optional<Users> findByEmail(String email);
    Optional<Users> findByUsername(String username);
    Optional<Users> findByPhoneNumber(String phoneNumber);
    Optional<Users> findByEmailOrUsernameOrPhoneNumber(String email,String username,String phoneNumber);

}
