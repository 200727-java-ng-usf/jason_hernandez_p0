package com.revature.bankconsole.repos;

import com.revature.bankconsole.models.UserInfo;

public class UserRepo {

    public UserRepo() {
        System.out.println("[LOG] - Instantiating " + this.getClass().getName());
    }

    private UserDb userDataset = UserDb.userDataset;

    public UserInfo findUserByCredentials(String username, String password) {
        if (!username.equals("admin") && !password.equals("P4ssw0rd")) {
            throw new RuntimeException();
        }
        return new UserInfo(1, "Adam", "Inn", "admin", "p4ssw0rd", Role.ADMIN);
    }

    public UserInfo findUserByUsername(String username) {
        return userDataset.findUserByUsername(username);
    }

    public UserInfo save(UserInfo newUser) {
        return userDataset.addUser(newUser);
    }
}
