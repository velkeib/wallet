package com.velkei.wallet.repository;

import com.velkei.wallet.dto.ChartData;
import com.velkei.wallet.entity.Expense;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Calendar;
import java.util.List;

public interface ExpenseRepository extends CrudRepository<Expense, Long>{
    @Query(value = "select sum(amount) as amount, name, month(date) - month(:date) as off  from expense e where date >= :date  group by name, month(date), year(date) order by name, year(date), month(date)", nativeQuery = true)
    List<ChartData> getChartData(@Param("date") Calendar calendar);

}
