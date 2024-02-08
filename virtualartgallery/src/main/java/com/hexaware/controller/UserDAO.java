package com.hexaware.controller;

import com.hexaware.model.User;



public interface UserDAO {

    User getUserById(int userID);

    void close();
}
