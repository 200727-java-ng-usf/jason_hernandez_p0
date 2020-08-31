package com.revature.bankconsole.utilities;
import com.revature.bankconsole.accounts.UserAccounts;
import com.revature.bankconsole.screens.*;
import com.revature.bankconsole.models.UserInfo;
import com.revature.bankconsole.repos.*;
import com.revature.bankconsole.services.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class AppState {
    private BufferedReader console;
    private UserInfo currentUser;
    private ScreenRouter router;
    private boolean appRunning;
    private AccountServices accountServices;

    public AppState() {
        System.out.println("Initializing application...");

        appRunning = true;
        console = new BufferedReader(new InputStreamReader(System.in));

        final UserRepo userRepo = new UserRepo();
        final CheckingRepo checkingRepo = new CheckingRepo();
        final UserServices userService = new UserServices();
        final AccountServices accountServices = new AccountServices(checkingRepo);
        UserAccounts userAccounts = new UserAccounts();

        router = new ScreenRouter();
        router.addScreen(new HomeScreen())
                .addScreen(new RegistrationScreen())
                .addScreen(new LoginScreen(userService))
                .addScreen(new DashboardScreen());


    }

    public BufferedReader getConsole() {
        return console;
    }

    public UserInfo getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(UserInfo currentUser) {
        this.currentUser = currentUser;
    }

    public ScreenRouter getRouter() {
        return router;
    }

    public boolean isAppRunning() {
        return appRunning;
    }

    public void setAppRunning(boolean appRunning) {
        this.appRunning = appRunning;
    }

    public boolean isSessionValid() {
        return (this.currentUser != null);
    }

}
