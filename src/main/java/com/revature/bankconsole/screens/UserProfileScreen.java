package com.revature.bankconsole.screens;

import static com.revature.bankconsole.AppDriver.app;

public class UserProfileScreen extends Screen{

    public UserProfileScreen() {
        super("User Profile", "/profile");
    }

    @Override
    public void render() {
        System.out.println("Current user: " + app.getCurrentUser());
        app.getRouter().navigate("/dashboard");
    }
}
