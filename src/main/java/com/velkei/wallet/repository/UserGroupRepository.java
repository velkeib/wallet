package com.velkei.wallet.repository;

import com.velkei.wallet.entity.UserGroup;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Calendar;
import java.util.List;

public interface UserGroupRepository extends CrudRepository<UserGroup, Long> {

    @Query(value = "select * from user_group_group_users uggu left join user_group ug on uggu.user_group_group_id = ug.group_id where group_users_user_name = :user_name", nativeQuery = true)
    List<UserGroup> getUserGroupByUser(@Param("user_name") String userName);
}
