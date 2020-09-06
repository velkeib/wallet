package com.velkei.wallet.controller;


import com.velkei.wallet.WalletApplication;
import com.velkei.wallet.repository.ExpenseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.velkei.wallet.entity.Expense;

import java.util.List;


@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
public class HomeController {

    @Autowired
    private ExpenseRepository expenseRepository;

    private static final Logger log = LoggerFactory.getLogger(WalletApplication.class);

    @RequestMapping(value = "/expenses", method= RequestMethod.GET)
    @ResponseBody
    public List<Expense> getExpenses(){
        return (List<Expense>) expenseRepository.findAll();
    }

    @RequestMapping(value = "/expenses", method= RequestMethod.POST)
    @ResponseBody
    public List<Expense> setExpenses(@RequestBody Expense expense){

        Expense expenseEntity = new Expense();
        expenseEntity.setUserName(expense.getUserName());
        expenseEntity.setAmount(expense.getAmount());
        expenseEntity.setDescription(expense.getDescription());

        expenseRepository.save(expenseEntity);

        return (List<Expense>) expenseRepository.findAll();
    }

}
