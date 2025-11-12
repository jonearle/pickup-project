package com.hoophelper.server.repository;

import com.hoophelper.server.model.User;
import org.springframework.data.repository.CrudRepository;;

public interface UserRepository extends CrudRepository<User, Integer> {
    
}
