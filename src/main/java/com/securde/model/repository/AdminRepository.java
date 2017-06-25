package com.securde.model.repository;

import com.securde.model.account.Admin;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by kevin on 6/25/2017.
 */
public interface AdminRepository extends CrudRepository<Admin, Integer> {

    Admin findByUsername(String username);
}
