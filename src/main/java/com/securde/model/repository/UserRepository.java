package com.securde.model.repository;

import com.securde.model.account.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.ArrayList;

/**
 * Created by kevin on 6/25/2017.
 */
public interface UserRepository extends CrudRepository<User, Integer>{

    User findByUsername(String username);
    User findByEmail(String email);
    User findByIdNumber(String idNumber);
    ArrayList<User> findByActive(Boolean active);


    // Pass date, compare if user's dateCreated is less than passed

    @Modifying
    @Transactional
    @Query("UPDATE User u " +
            "SET u.active = FALSE " +
            "WHERE u.dateTimeUpdated < :date AND u.temp = TRUE AND u.active = TRUE")
    void lockTemporaryAccounts(@Param("date") String date);
}
