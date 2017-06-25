package com.securde.model.repository;

import com.securde.model.account.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by kevin on 6/25/2017.
 */
public interface UserRepository extends CrudRepository<User, String>{

    User findByUsername(String username);
    User findByEmail(String email);
}
