package com.velkei.wallet.controller;


import com.velkei.wallet.WalletApplication;
import com.velkei.wallet.repository.ExpenseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.velkei.wallet.entity.Expense;

import java.time.Instant;
import java.util.List;
import java.util.Date;

@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
public class HomeController {

    @Autowired
    private ExpenseRepository expenseRepository;

    private static final Logger log = LoggerFactory.getLogger(WalletApplication.class);

    @RequestMapping(value = "/expenses", method= RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> getExpenses(){



        return ResponseEntity.ok()
                .body(expenseRepository.findAll());
  
    }

    @RequestMapping(value = "/expenses", method= RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> setExpenses(@RequestBody Expense expense){

        Expense expenseEntity = new Expense();
        expenseEntity.setName(expense.getName());
        expenseEntity.setAmount(expense.getAmount());
        expenseEntity.setDescription(expense.getDescription());
        expenseEntity.setDate(Date.from(Instant.now()));

        expenseRepository.save(expenseEntity);

        return ResponseEntity.ok()
                .body(expenseRepository.findAll());
    }

}
