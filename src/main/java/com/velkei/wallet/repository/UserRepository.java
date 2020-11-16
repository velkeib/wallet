package com.velkei.wallet.repository;

import com.velkei.wallet.dto.UserInterface;
import com.velkei.wallet.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByUserName(String userName);

    @Query(value = "select u.user_name as userName, u.first_name as firstName, u.last_name as lastName from user u left join user_group_group_users uggu on u.user_name = uggu.group_users_user_name where uggu.user_group_group_id = :groupId;", nativeQuery = true)
    List<UserInterface> findUsersByGroup(@Param("groupId") long groupId);

}
