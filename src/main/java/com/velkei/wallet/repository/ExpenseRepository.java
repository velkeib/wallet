package com.velkei.wallet.repository;

import com.velkei.wallet.dto.ChartDataInterface;
import com.velkei.wallet.entity.Expense;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Calendar;
import java.util.List;

public interface ExpenseRepository extends CrudRepository<Expense, Long>{
    @Query(value = "select sum(amount) as amount, month(date) - month(:date) as off, DENSE_RANK() OVER (ORDER BY u.user_name) as 'rank', u.user_name as userName from expense e inner join user u on e.user_name = u.user_name where date >= :date  group by userName, month(date), year(date) order by userName, year(date), month(date)", nativeQuery = true)
    List<ChartDataInterface> getChartData(@Param("date") Calendar calendar);

}
