package com.velkei.wallet.repository;

import com.velkei.wallet.entity.Expense;
import org.springframework.data.repository.CrudRepository;

public interface ExpenseRepository extends CrudRepository<Expense, Long>{

}
