package com.velkei.wallet.controller;


import com.velkei.wallet.repository.GroupRepository;
import com.velkei.wallet.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GroupController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    GroupRepository groupRepository;

    @RequestMapping(value = "/creategroup", method= RequestMethod.GET)
    public ResponseEntity<?>  createGroup(){

        return ResponseEntity.ok().body("");
    }

}
