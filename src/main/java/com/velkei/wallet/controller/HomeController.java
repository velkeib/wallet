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

    @RequestMapping(value = "/expenses", method= RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> getExpenses(Principal principal){

        return ResponseEntity.ok()
                .body(homeService.getChartData());
    }

    @RequestMapping(value = "/users", method= RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> getUsers(){

        return ResponseEntity.ok()
                .body(userRepository.findAll());
    }

    @RequestMapping(value = "/expenses", method= RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> setExpenses(@RequestBody ExpenseDTO expense) throws Exception{

        return ResponseEntity.ok()
                .body(homeService.setExpenseEntity(expense));
    }

    @Bean
    public CommandLineRunner demo(UserRepository userRepository, ExpenseRepository expenseRepository, UserGroupRepository groupRepository ) {
        return (args) -> {

            userRepository.save(new User("Bence", "$2a$10$IT.xwh1CFa.k9xqzJsK3xOzYClrtvfHBm9PinY5UEAPvSU2CAmirO", "", ""));
            userRepository.save(new User("Gabika", "test2", "", ""));

            List<User> list = new ArrayList<>();
            list.add(userRepository.findByUserName("Bence"));
            list.add(userRepository.findByUserName("Gabika"));

            groupRepository.save(new UserGroup(1L, "Elsőcsapat", list, userRepository.findByUserName("Bence")));


            /*expenseRepository.save(new Expense(5L, 2000, "szék", userRepository.findById(1L).get(), new GregorianCalendar(2020, 5, 27)));
            expenseRepository.save(new Expense(6L, 2000, "szék", userRepository.findById(1L).get(), new GregorianCalendar(2020, 6, 27)));

            expenseRepository.save(new Expense(7L, 2000, "szék", userRepository.findById(1L).get(), new GregorianCalendar(2020, 7, 27)));
            expenseRepository.save(new Expense(8L, 2000, "szék", userRepository.findById(1L).get(), new GregorianCalendar(2020, 7, 27)));

            expenseRepository.save(new Expense(9L, 2000, "szék", userRepository.findById(2L).get(), new GregorianCalendar(2020, 2, 27)));
            expenseRepository.save(new Expense(10L, 2000, "szék", userRepository.findById(2L).get(), new GregorianCalendar(2020, 3, 27)));
            expenseRepository.save(new Expense(11L, 2000, "szék", userRepository.findById(2L).get(), new GregorianCalendar(2020, 4, 27)));
            expenseRepository.save(new Expense(12L, 2000, "szék", userRepository.findById(2L).get(), new GregorianCalendar(2020, 5, 27)));
            expenseRepository.save(new Expense(13L, 2000, "szék", userRepository.findById(2L).get(), new GregorianCalendar(2020, 6, 27)));*/
            expenseRepository.save(new Expense(14L, 0, "szék", userRepository.findByUserName("Bence"), new GregorianCalendar(2020, 7, 27)));
            expenseRepository.save(new Expense(15L,     0, "szék", userRepository.findByUserName("Gabika"), new GregorianCalendar(2020, 8, 27)));



            //expenseRepository.save(new Expense(16L, 2000, "szék", userRepository.findById(2L).get(), new GregorianCalendar(2019, 8, 27)));
        };
    }






}
