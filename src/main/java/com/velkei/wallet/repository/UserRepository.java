package com.velkei.wallet.repository;

import com.velkei.wallet.dto.UserInterface;
import com.velkei.wallet.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByUserName(String userName);

    @Query()
    List<UserInterface> findUsersByGroup

}
