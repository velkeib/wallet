package com.velkei.wallet.service;

import com.velkei.wallet.dto.ChartData;
import com.velkei.wallet.dto.ExpenseDTO;
import com.velkei.wallet.entity.Expense;

import java.util.ArrayList;

public interface HomeService {

    public abstract ArrayList<ChartData> getChartData();
    public abstract Expense setExpenseEntity(ExpenseDTO expense) throws Exception;

}
