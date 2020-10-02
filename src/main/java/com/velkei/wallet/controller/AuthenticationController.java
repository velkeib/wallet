package com.velkei.wallet.controller;

import com.velkei.wallet.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

public class AuthenticationController {


    @RequestMapping(value = "/login", method= RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> login(@RequestBody User user){


        return  ResponseEntity.ok()
                .body(new User(1L, "asd", "asd"));
    }


    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> userRegistration(@RequestBody User user){




        return ResponseEntity.ok().body();
    }

}
