package com.revature.bankconsole.screens;

import com.revature.bankconsole.models.UserInfo;
import com.revature.bankconsole.services.UserServices;

import java.io.IOException;

import static com.revature.bankconsole.AppDriver.app;

public class LoginScreen extends Screen {
    private static int counter = 3;

    private UserServices userService;
    private UserInfo authUser;

    public LoginScreen(UserServices userService) {

        super("Login Screen", "/login");
        this.userService = userService;
        }

        public int getCounter() {
            return counter;
        }


    @Override
    public void render() {

        String username, password;

        try {
            System.out.println("Please provide your login credentials.");
            System.out.print("Username: ");
            username = app.getConsole().readLine();

            System.out.println("Password: ");
            password = app.getConsole().readLine();

            System.out.println("You entered username: " + username + " / " + password);

            app.setCurrentUser(userService.authenticate(username, password));


            if (app.isSessionValid()) {
                app.getRouter().navigate("/dashboard");
            }

        } catch (IOException ioe) {
            System.out.println("Error - failed to login.");
            app.getRouter().navigate("/login");
        }
    }
}
