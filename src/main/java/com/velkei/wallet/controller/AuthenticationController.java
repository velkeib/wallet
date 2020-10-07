package com.velkei.wallet.controller;

import com.velkei.wallet.entity.User;
import com.velkei.wallet.service.AuthenticationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = "*")
public class AuthenticationController {


    @Autowired
    AuthenticationServiceImpl authenticationService;

    @RequestMapping(value = "/login", method= RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> login(@RequestBody User user){


        return  ResponseEntity.ok()
                .body(new User(1L, "asd", "asd", "asd", "asd"));
    }


    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> userRegistration(@RequestBody User user){

        return ResponseEntity.ok().body(authenticationService.createUserEntity(user));
    }

}
