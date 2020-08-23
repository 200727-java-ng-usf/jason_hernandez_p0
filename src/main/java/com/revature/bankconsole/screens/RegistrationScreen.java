package com.revature.bankconsole.screens;

import com.revature.bankconsole.models.UserInfo;
import com.revature.bankconsole.services.UserServices;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RegistrationScreen extends Screen {

    private UserServices userService;


    public RegistrationScreen(UserServices userService) {
        super("RegisterScreen", "/register");
        this.userService = userService;
    }

    @Override
    public void render() {

        String firstname, lastname, username, password, email;

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
            System.out.println("Email: ");
            email = app.getConsole().readLine();

            UserInfo newUser = new UserInfo(firstname, lastname, username, password, email);
            userService.register(newUser);

            if (app.isSessionValid()) {
                app.getRouter().navigate("/home");
            }

        } catch (IOException ioe) {
            System.out.println("Error: failed to register");
            app.getRouter().navigate("/register");
        }
    }
}
