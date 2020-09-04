package com.velkei.wallet.controller;


import com.velkei.wallet.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.velkei.wallet.entity.Expense;

import java.util.List;


@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
public class HomeController {

    @Autowired
    private ExpenseRepository expenseRepository;

    @RequestMapping(value = "/expenses", method= RequestMethod.GET)
    @ResponseBody
    public List<Expense> getExpenses(){
        return (List<Expense>) expenseRepository.findAll();
    }

    @RequestMapping(value = "/expenses", method= RequestMethod.POST)
    @ResponseBody
    public List<Expense> setExpenses(){

        Expense expense = new Expense();
        expenseRepository.save(expense);

        return (List<Expense>) expenseRepository.findAll();
    }

}
