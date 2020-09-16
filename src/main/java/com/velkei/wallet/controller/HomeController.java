package com.velkei.wallet.controller;

import com.velkei.wallet.dto.ChartData;
import com.velkei.wallet.dto.ChartDataInterface;
import com.velkei.wallet.WalletApplication;
import com.velkei.wallet.dto.ChartPageResponse;
import com.velkei.wallet.dto.ExpenseDTO;
import com.velkei.wallet.entity.User;
import com.velkei.wallet.repository.ExpenseRepository;
import com.velkei.wallet.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.velkei.wallet.entity.Expense;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.CommandLineRunner;

import java.util.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
public class HomeController {

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private UserRepository userRepository;

    private static final Logger log = LoggerFactory.getLogger(WalletApplication.class);

    @RequestMapping(value = "/expenses", method= RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> getExpenses(){
        List<ChartDataInterface> getChartDataResult = expenseRepository.getChartData(calculateSixMonthsBefore());

        int[][] data = new int[2][6];

        ArrayList<ChartData> response = new ArrayList<>();

        for(int i = 0; i < getChartDataResult.size(); i++){

            data[getChartDataResult.get(i).getRank() - 1][getChartDataResult.get(i).getOff()] = getChartDataResult.get(i).getAmount();

            if(i < getChartDataResult.size() -1 && getChartDataResult.get(i).getName() != getChartDataResult.get(i + 1).getName()){
                response.add(new ChartData(getChartDataResult.get(i).getUserName(), data[getChartDataResult.get(i).getRank() - 1]));
            }
        }

        response.add(new ChartData(getChartDataResult.get(getChartDataResult.size()-1).getUserName(), data[getChartDataResult.get(getChartDataResult.size() - 1).getRank() - 1]));

        return ResponseEntity.ok()
                .body(response);
    }

    @RequestMapping(value = "/users", method= RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> getUsers(){

        return ResponseEntity.ok()
                .body(userRepository.findAll());
    }

    @RequestMapping(value = "/expenses", method= RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> setExpenses(@RequestBody ExpenseDTO expense){

        Expense expenseEntity = new Expense();
        expenseEntity.setUserName(userRepository.findById(Long.parseLong(expense.getName())).get());
        expenseEntity.setAmount(expense.getAmount());
        expenseEntity.setDescription(expense.getDescription());
        expenseEntity.setDate(new GregorianCalendar());

        expenseRepository.save(expenseEntity);

        return ResponseEntity.ok()
                .body(expenseRepository.findAll());
    }

    @Bean
    public CommandLineRunner demo(UserRepository userRepository, ExpenseRepository expenseRepository) {
        return (args) -> {

            userRepository.save(new User(1L, "Bence"));
            userRepository.save(new User(2L, "Gabika"));

            /*expenseRepository.save(new Expense(5L, 2000, "szék", userRepository.findById(1L).get(), new GregorianCalendar(2020, 5, 27)));
            expenseRepository.save(new Expense(6L, 2000, "szék", userRepository.findById(1L).get(), new GregorianCalendar(2020, 6, 27)));

            expenseRepository.save(new Expense(7L, 2000, "szék", userRepository.findById(1L).get(), new GregorianCalendar(2020, 7, 27)));
            expenseRepository.save(new Expense(8L, 2000, "szék", userRepository.findById(1L).get(), new GregorianCalendar(2020, 7, 27)));

            expenseRepository.save(new Expense(9L, 2000, "szék", userRepository.findById(2L).get(), new GregorianCalendar(2020, 2, 27)));
            expenseRepository.save(new Expense(10L, 2000, "szék", userRepository.findById(2L).get(), new GregorianCalendar(2020, 3, 27)));
            expenseRepository.save(new Expense(11L, 2000, "szék", userRepository.findById(2L).get(), new GregorianCalendar(2020, 4, 27)));
            expenseRepository.save(new Expense(12L, 2000, "szék", userRepository.findById(2L).get(), new GregorianCalendar(2020, 5, 27)));
            expenseRepository.save(new Expense(13L, 2000, "szék", userRepository.findById(2L).get(), new GregorianCalendar(2020, 6, 27)));
            expenseRepository.save(new Expense(14L, 2000, "szék", userRepository.findById(2L).get(), new GregorianCalendar(2020, 7, 27)));
            expenseRepository.save(new Expense(15L, 2000, "szék", userRepository.findById(2L).get(), new GregorianCalendar(2020, 8, 27)));*/
            //expenseRepository.save(new Expense(16L, 2000, "szék", userRepository.findById(2L).get(), new GregorianCalendar(2019, 8, 27)));
        };
    }


    public static Calendar calculateSixMonthsBefore(){

        Calendar calendar = new GregorianCalendar();

        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        calendar.set(Calendar.MONTH, (calendar.get(Calendar.MONTH) - 5) % 12);

        return calendar;

    }



}
