package com.velkei.wallet.service;

import com.velkei.wallet.entity.User;
import com.velkei.wallet.exception.EmailAlreadyExist;
import com.velkei.wallet.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.velkei.wallet.exception.ExceptionHandlerImpl;

import org.springframework.security.crypto.password.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
public class AuthenticationServiceImpl implements AuthenticationService{

    @Autowired
    UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public User createUserEntity(User user) {
        if(userRepository.findByUserName(user.getUserName()) == null){
            return userRepository.save(new User(user.getUserName(), passwordEncoder.encode(user.getPassword()), user.getFirstName(), user.getLastName()));
        }else{
            throw new EmailAlreadyExist();
        }

    }
}