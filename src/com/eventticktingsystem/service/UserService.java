package com.eventticktingsystem.service;

import java.util.List;

import com.eventticktingsystem.model.User;
import com.eventticktingsystem.util.FileUtil;

public class UserService {

    private List<User> users;

    public UserService(List<User> users) {
        this.users = users;
    }

    // ✅ Check if email already exists
    public boolean emailExists(String email) {
        return users.stream()
                .anyMatch(u -> u.getEmail().equalsIgnoreCase(email));
    }

    // ✅ Register user
    public boolean register(User u) {

        if (emailExists(u.getEmail())) {
            System.out.println("Email exists!");
            return false;
        }

        users.add(u);

        // ✅ Save to file
        FileUtil.write("data/users.txt", u.toString());

        return true;
    }

    // ✅ Login
    public User login(String email, String pass) {
        return users.stream()
                .filter(u -> u.getEmail().equalsIgnoreCase(email)
                        && u.getPassword().equals(pass))
                .findFirst()
                .orElse(null);
    }
}