package com.cleb.dao;

import com.cleb.model.User;
import java.util.List;

public interface UserDAO {
    
	User authenticate(String username, String password);
	void addUser(User user);
	List<User> getAllUsers();
    
    
}

