package com.revature.bankconsole.screens;

import com.revature.bankconsole.models.UserInfo;
import com.revature.bankconsole.services.UserServices;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RegistrationScreen extends Screen {

    private UserServices userService;


    public RegistrationsScreen(UserService userService) {
        super("RegisterScreen", "/register");
        this.userService = userService;
    }

    @Override
    public void render() {

        String firstname, lastname, username, password;

        try {
            System.out.println("Sign up for a new account.");
            System.out.print("first name: ");
            firstname = app.getConsole().readLine();
            System.out.print("last name: ");
            lastname = app.getConsole().readLine();
            System.out.print("Username: ");
            username = app.getConsole().readLine();
            System.out.println("Password: ");
            password = app.getConsole().readLine();

            AppUser newUser = new AppUser(firstname, lastname, username, password);
            userService.register(newUser);

            if (app.isSessionValid()) {
                app.getRouter().navigate("/home");
            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
