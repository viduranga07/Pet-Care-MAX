package com.petcaremax.util;

import com.petcaremax.model.User;

public final class Session {

    private static User currentUser;

    private Session() {
    }

    public static void start(User user) {
        currentUser = user;
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static boolean isLoggedIn() {
        return currentUser != null;
    }

    public static void logout() {
        currentUser = null;
    }
}