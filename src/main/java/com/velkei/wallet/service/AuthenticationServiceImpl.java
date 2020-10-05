package com.velkei.wallet.service;

import com.velkei.wallet.entity.User;

public class AuthenticationServiceImpl implements AuthenticationService{

    public User createUserEntity(User user){
        user.getUserName();
        user.getPassword();
        return new User();
    }
}
