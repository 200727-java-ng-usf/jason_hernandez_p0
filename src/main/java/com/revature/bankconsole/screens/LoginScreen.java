package com.revature.bankconsole.screens;

import com.revature.bankconsole.services.UserServices;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LoginScreen extends Screen {

    private UserServices userService;

    public LoginScreen(UserServices userService) {
        System.out.println("[LOG]");
        this.userService = userService;
    }

    @Override
    public String render() {

        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
        String username, password;

        // Prompt user for login credentials
        try {
            System.out.println("Please provide your login credentials.");
            System.out.print("Username: ");
            username = console.readLine();

            System.out.println("Password: ");
            password = console.readLine();

            System.out.println("You entered username: " + username + " / " + password);

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return username;
    }
}
