package com.velkei.wallet.controller;

import com.velkei.wallet.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = "*")
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




        return ResponseEntity.ok().body("ok");
    }

}
