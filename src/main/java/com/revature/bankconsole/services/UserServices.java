package com.revature.bankconsole.services;
import com.revature.bankconsole.accounts.CheckingAccount;
import com.revature.bankconsole.exceptions.AuthenticationException;
import com.revature.bankconsole.models.AccountInfo;
import com.revature.bankconsole.models.UserInfo;
import com.revature.bankconsole.repos.CheckingRepo;
import com.revature.bankconsole.repos.UserRepo;
import static com.revature.bankconsole.AppDriver.app;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class UserServices {

    private UserRepo userRepo;
    private CheckingRepo checkingRepo;

    public UserServices() {
        userRepo = new UserRepo();
        checkingRepo = new CheckingRepo();
    }

    // Authenticate an existing customer
    public UserInfo authenticate(String username, String password) throws AuthenticationException {
        if(username == null || username.trim().equals("") || password == null || password.trim().equals("")) {
            throw new RuntimeException("401 - Invalid credentials provided!");
        }
        UserInfo authUser = userRepo.findUserByCredentials(username, password)
                .orElseThrow(AuthenticationException::new);
        if (authUser == null) {
            throw new AuthenticationException();
        }

        // Get the user's account number here
        Integer accountNumber = authUser.getAccountNumber();
        AccountInfo chk = checkingRepo.findAccountByNumber(accountNumber).orElseThrow(AuthenticationException::new);

        authUser.setAccount(chk);
        return authUser;
    }

    // For registring a new user
    public boolean register(UserInfo newUser) {

        boolean successful = false;

        if (!validateUserFields(newUser)) {
            throw new RuntimeException("Invalid user fields provided during registration.");
        }

        if (userRepo.findUserByUsername(newUser.getUserName()).isPresent()) {
            throw new RuntimeException("That username is already in use.");
        }

        successful = userRepo.save(newUser); // Add user to database
        if(successful) checkingRepo.saveNewAccount(newUser);

        return successful;
    }

    public Set<UserInfo> getAllUsers() {
        return new HashSet<>();
    }

    public boolean validateUserFields(UserInfo user) {
        if(user == null) return false;
        if(user.getFirstName() == null || user.getFirstName().trim().equals("")) return false;
        if(user.getLastName() == null || user.getLastName().trim().equals("")) return false;
        if(user.getUserName() == null || user.getUserName().trim().equals("")) return false;
        if (user.getPassword() != null && !user.getPassword().trim().equals(""));
        return true;
    }
}
