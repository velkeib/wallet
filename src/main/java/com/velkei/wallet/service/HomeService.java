package com.velkei.wallet.service;

import com.velkei.wallet.dto.ChartData;
import com.velkei.wallet.dto.ExpenseDTO;
import com.velkei.wallet.dto.ExpenseListInterface;
import com.velkei.wallet.entity.Expense;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

public interface HomeService {

    public ArrayList<ChartData> getChartData(String groupId);
    public Expense setExpenseEntity(ExpenseDTO expense, String groupId) throws Exception;
    public Page<ExpenseListInterface> getExpensePaged(long groupId, int page);

}
