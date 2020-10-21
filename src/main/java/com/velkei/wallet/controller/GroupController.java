package com.velkei.wallet.controller;


import com.velkei.wallet.WalletApplication;
import com.velkei.wallet.entity.UserGroup;
import com.velkei.wallet.entity.User;
import com.velkei.wallet.repository.UserRepository;
import com.velkei.wallet.service.GroupService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@CrossOrigin(origins = "*")
public class GroupController {

    @Autowired
    GroupService groupService;

    @Autowired
    UserRepository userRepository;

    private static final Logger log = LoggerFactory.getLogger(WalletApplication.class);

    @RequestMapping(value = "/creategroup", method= RequestMethod.POST)
    public ResponseEntity<?>  createGroup(@RequestBody UserGroup group, Principal principal){

        return ResponseEntity.ok().body(groupService.createGroup(userRepository.findByUserName(principal.getName()), group.getGroupName()));
    }

    @RequestMapping(value = "/addgroupmember", method= RequestMethod.POST)
    public ResponseEntity<?>  addGroupMember(@RequestBody User user, Principal principal){

        return ResponseEntity.ok().body(groupService.addGroupUser());
    }
}
