package com.velkei.wallet.repository;

import com.velkei.wallet.dto.ChartDataInterface;
import com.velkei.wallet.dto.ExpenseListInterface;
import com.velkei.wallet.entity.Expense;
import com.velkei.wallet.entity.UserGroup;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.Calendar;
import java.util.List;

public interface ExpenseRepository extends PagingAndSortingRepository<Expense, Long> {
    @Query(value = "select sum(amount) as amount, month(date) - month(:date) as off, DENSE_RANK() OVER (ORDER BY u.user_name) as 'rank', u.user_name as userName from expense e inner join user u on e.user_name = u.user_name where date >= :date and user_group = :user_group group by userName, month(date), year(date) order by userName, year(date), month(date)", nativeQuery = true)
    List<ChartDataInterface> getChartData(@Param("date") Calendar calendar, @Param("user_group") long groupId);

    @Query(value = "select e.amount as amount, e.description as description, e.date as dateOfCreation, u.user_name as userName, u.first_name as firstName, u.last_name as lastName  from expense e left join user u on e.user_name = u.user_name where e.user_group = :user_group",
            countQuery = "select count(*) from expense e left join user u on e.user_name = u.user_name where e.user_group = :user_group",
            nativeQuery = true)
    Page<ExpenseListInterface> findByUserGroup(@Param("user_group") long userGroup, Pageable pageable);

}
