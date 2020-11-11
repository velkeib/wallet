package com.velkei.wallet.repository;

import com.velkei.wallet.dto.UserInterface;
import com.velkei.wallet.entity.User;
import com.velkei.wallet.entity.UserGroup;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Calendar;
import java.util.List;

public interface UserGroupRepository extends CrudRepository<UserGroup, Long> {

    @Query(value = "select * from user_group_group_users uggu left join user_group ug on uggu.user_group_group_id = ug.group_id where group_users_user_name = :user_name", nativeQuery = true)
    List<UserGroup> getUserGroupByUser(@Param("user_name") String userName);

    @Query(value = "select count(*) as sum from user_group_group_users uggu where user_group_group_id = :id", nativeQuery = true)
    int getGroupUserCount(@Param("id") long groupId);

    @Query(value = "select u.user_name as userName, u.first_name as firstName, u.last_name as lastName from user_group_group_users uggu left join user u on u.user_name = uggu.group_users_user_name where uggu.user_group_group_id = :id", nativeQuery = true)
    List<UserInterface> getGroupUsers(@Param("id") long groupId);

}
