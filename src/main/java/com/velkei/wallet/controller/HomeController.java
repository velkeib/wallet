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
                .body(userRepository.findById());
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

    @Bean
    public CommandLineRunner demo(UserRepository userRepository, ExpenseRepository expenseRepository, UserGroupRepository groupRepository ) {
        return (args) -> {

            //userRepository.save(new User("og@gmail.com", "$2a$10$IT.xwh1CFa.k9xqzJsK3xOzYClrtvfHBm9PinY5UEAPvSU2CAmirO", "Bence", "Velkei"));
            //userRepository.save(new User("vb@gmail.com", "test2", "Gabriella", "Orosz"));

            //List<User> list = new ArrayList<>();
            //list.add(userRepository.findByUserName("vb@gmail.com"));
            //list.add(userRepository.findByUserName("og@gmail.com"));

            //groupRepository.save(new UserGroup(1L, "Velkei család", list, userRepository.findByUserName("vb@gmail.com")));


            /*expenseRepository.save(new Expense(5L, 2000, "szék", userRepository.findById(1L).get(), new GregorianCalendar(2020, 5, 27)));
            expenseRepository.save(new Expense(6L, 2000, "szék", userRepository.findById(1L).get(), new GregorianCalendar(2020, 6, 27)));

            expenseRepository.save(new Expense(7L, 2000, "szék", userRepository.findById(1L).get(), new GregorianCalendar(2020, 7, 27)));
            expenseRepository.save(new Expense(8L, 2000, "szék", userRepository.findById(1L).get(), new GregorianCalendar(2020, 7, 27)));

            expenseRepository.save(new Expense(9L, 2000, "szék", userRepository.findById(2L).get(), new GregorianCalendar(2020, 2, 27)));
            expenseRepository.save(new Expense(10L, 2000, "szék", userRepository.findById(2L).get(), new GregorianCalendar(2020, 3, 27)));
            expenseRepository.save(new Expense(11L, 2000, "szék", userRepository.findById(2L).get(), new GregorianCalendar(2020, 4, 27)));
            expenseRepository.save(new Expense(12L, 2000, "szék", userRepository.findById(2L).get(), new GregorianCalendar(2020, 5, 27)));
            expenseRepository.save(new Expense(13L, 2000, "szék", userRepository.findById(2L).get(), new GregorianCalendar(2020, 6, 27)));*/
            //expenseRepository.save(new Expense(14L, 0, "szék", userRepository.findByUserName("vb@gmail.com"), new GregorianCalendar(2020, 7, 27), groupRepository.findById(1L).get()));
            //expenseRepository.save(new Expense(15L,     0, "szék", userRepository.findByUserName("og@gmail.com"), new GregorianCalendar(2020, 8, 27), groupRepository.findById(1L).get()));



            //expenseRepository.save(new Expense(16L, 2000, "szék", userRepository.findById(2L).get(), new GregorianCalendar(2019, 8, 27)));
        };
    }






}
