package com.velkei.wallet.security;


import com.velkei.wallet.WalletApplication;
import com.velkei.wallet.entity.User;
import com.velkei.wallet.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
import java.util.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;


    private static final Logger log = LoggerFactory.getLogger(WalletApplication.class);

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }

        log.info(username);

        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), new HashSet<>());
    }

}
