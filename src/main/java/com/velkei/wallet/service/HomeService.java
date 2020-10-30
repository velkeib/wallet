package com.velkei.wallet.service;

import com.velkei.wallet.dto.ChartData;
import com.velkei.wallet.dto.ExpenseDTO;
import com.velkei.wallet.entity.Expense;

import java.util.ArrayList;

public interface HomeService {

    public ArrayList<ChartData> getChartData(String groupId);
    public Expense setExpenseEntity(ExpenseDTO expense, String groupId) throws Exception;

}
