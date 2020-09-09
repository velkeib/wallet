package com.velkei.wallet.repository;

import com.velkei.wallet.entity.Expense;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Calendar;
import java.util.List;

public interface ExpenseRepository extends CrudRepository<Expense, Long>{
    @Query(value = " select sum(amount) as amount, name, date, month(date) as month from expense e where date >= :date  group by name, month(date), year(date) order by name, year(date), month(date)", nativeQuery = true)
    List<Object> getChartData(@Param("date") Calendar calendar);

}
