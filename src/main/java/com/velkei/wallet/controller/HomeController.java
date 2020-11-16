package com.velkei.wallet.controller;

import com.velkei.wallet.WalletApplication;
import com.velkei.wallet.dto.ExpenseDTO;
import com.velkei.wallet.entity.UserGroup;
import com.velkei.wallet.entity.User;
import com.velkei.wallet.repository.ExpenseRepository;
import com.velkei.wallet.repository.UserGroupRepository;
import com.velkei.wallet.repository.UserRepository;
import com.velkei.wallet.service.HomeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.velkei.wallet.entity.Expense;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.CommandLineRunner;

import java.security.Principal;
import java.util.*;

@RestController
@CrossOrigin(origins = "*")
public class HomeController {

    @Autowired
    HomeService homeService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private UserGroupRepository groupRepository;

    private static final Logger log = LoggerFactory.getLogger(WalletApplication.class);

    @RequestMapping(value = "/group/{groupId}", method= RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> getExpenses(@PathVariable String groupId, Principal principal){

        return ResponseEntity.ok()
                .body(homeService.getChartData(groupId));
    }

    @RequestMapping(value = "/users/{groupId:[\\d]+}", method= RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> getUsers(@PathVariable long groupId){

        return ResponseEntity.ok()
                .body(userRepository.findAll());
    }

    @RequestMapping(value = "/expenses/{groupId:[\\d]+}", method= RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> getExpensesPage(@PathVariable long groupId, @RequestParam int page){

        return ResponseEntity.ok()
                .body(homeService.getExpensePaged(groupId, page-1));
    }

    @RequestMapping(value = "/group/{groupId}", method= RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> setExpenses(@RequestBody ExpenseDTO expense, @PathVariable String groupId) throws Exception{

        return ResponseEntity.ok()
                .body(homeService.setExpenseEntity(expense, groupId));
    }
}
