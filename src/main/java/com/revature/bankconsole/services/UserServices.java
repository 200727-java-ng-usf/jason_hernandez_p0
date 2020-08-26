package com.revature.bankconsole.services;
import com.revature.bankconsole.exceptions.AuthenticationException;
import com.revature.bankconsole.models.UserInfo;
import com.revature.bankconsole.repos.UserRepo;
import static com.revature.bankconsole.AppDriver.app;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class UserServices {

    private UserRepo userRepo;

    public UserServices(UserRepo repo) {
        userRepo = repo;
    }

    public UserInfo authenticate(String username, String password) {
        if(username == null || username.trim().equals("") || password == null || password.trim().equals("")) {
            throw new RuntimeException("401 - Invalid credentials provided!");
        }
        UserInfo authUser = userRepo.findUserByCredentials(username, password)
                .orElseThrow(AuthenticationException::new);

        app.setCurrentUser(authUser);
        return authUser;
    }

    public void register(UserInfo newUser) {
        if (!validateUserFields(newUser)) {
            throw new RuntimeException("Invalid user fields provided during registration.");
        }
        Optional<UserInfo> existingUser = userRepo.findUserByUsername(newUser.getUserName());
        if(userRepo.findUserByUsername(newUser.getUserName()).isPresent()) {
            throw new RuntimeException("That username is already in use.");
        }

        UserInfo registeredUser = userRepo.save(newUser);

        app.setCurrentUser(registeredUser);
    }

    public Set<UserInfo> getAllUsers() {
        return new HashSet<>();
    }

    public Set<UserInfo> getUserById() {
        return new HashSet<>();
    }
    public Set<UserInfo> getUserByName() {
        return new HashSet<>();
    }

    public boolean deleteUserById() {
        return false;
    }

    public boolean update(UserInfo updatedUser) {
        return false;
    }
    public boolean validateUserFields(UserInfo user) {
        if(user == null) return false;
        if(user.getFirstName() == null || user.getFirstName().trim().equals("")) return false;
        if(user.getLastName() == null || user.getLastName().trim().equals("")) return false;
        if(user.getUserName() == null || user.getUserName().trim().equals("")) return false;
        return user.getPassword() != null && !user.getPassword().trim().equals("");
    }
}
