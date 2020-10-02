package com.velkei.wallet.service;

import com.velkei.wallet.dto.ChartData;
import com.velkei.wallet.dto.ChartDataInterface;
import com.velkei.wallet.dto.ExpenseDTO;
import com.velkei.wallet.entity.Expense;
import com.velkei.wallet.repository.ExpenseRepository;
import com.velkei.wallet.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class HomeServiceImpl implements HomeService {

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public ArrayList<ChartData> getChartData (){
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

        return response;
    }

    @Override
    public Expense setExpenseEntity(ExpenseDTO expense) throws Exception{

        GregorianCalendar calendar = new GregorianCalendar();

        if(!expense.getDate().equals("")) {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = dateFormat.parse(expense.getDate());
            calendar.setTime(date);
        }
        Expense expenseEntity = new Expense();
        expenseEntity.setUserName(userRepository.findById(Long.parseLong(expense.getName())).get());
        expenseEntity.setAmount(expense.getAmount());
        expenseEntity.setDescription(expense.getDescription());
        expenseEntity.setDate(calendar);

        return expenseRepository.save(expenseEntity);

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
