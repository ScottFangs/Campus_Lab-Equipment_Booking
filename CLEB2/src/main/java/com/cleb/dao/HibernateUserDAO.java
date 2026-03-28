package com.cleb.dao;

import java.util.List;

import com.cleb.model.User;

public class HibernateUserDAO implements UserDAO {

    @Override
    public User authenticate(String username, String password) {
        throw new UnsupportedOperationException("Hibernate version not implemented yet");
    }

    @Override
    public void addUser(User user) {
        throw new UnsupportedOperationException("Hibernate version not implemented yet");
    }

    @Override
    public List<User> getAllUsers() {
        throw new UnsupportedOperationException("Hibernate version not implemented yet");
    }
}
