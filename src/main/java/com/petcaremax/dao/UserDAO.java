package com.petcaremax.dao;

import com.petcaremax.model.User;

public interface UserDAO {

    User findByUsername(String username)
            throws Exception;
}