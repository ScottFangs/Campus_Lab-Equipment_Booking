package com.cleb.client;

import com.cleb.model.User;

public class Session {

    private static User loggedInUser = null;

    public static void setLoggedInUser(User user) {
        loggedInUser = user;
    }

    public static User getLoggedInUser() {
        return loggedInUser;
    }

    public static void logout() {
        loggedInUser = null;
    }
}