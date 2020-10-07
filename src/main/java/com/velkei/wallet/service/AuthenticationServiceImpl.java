package com.velkei.wallet.service;

import com.velkei.wallet.entity.User;
import com.velkei.wallet.exception.EmailAlreadyExist;
import com.velkei.wallet.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.velkei.wallet.exception.ExceptionHandlerImpl;

@Service
public class AuthenticationServiceImpl implements AuthenticationService{

    @Autowired
    UserRepository userRepository;

    @Override
    public User createUserEntity(User user) {
        if(userRepository.findByUserName(user.getUserName()) == null){
            return userRepository.save(new User(user.getUserName(), user.getPassword(), user.getFirstName(), user.getLastName()));
        }else{
            throw new EmailAlreadyExist();
        }

    }
}