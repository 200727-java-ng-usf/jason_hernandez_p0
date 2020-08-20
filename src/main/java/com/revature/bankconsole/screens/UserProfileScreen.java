package com.revature.bankconsole.screens;

public class UserProfileScreen extends Screen{

    public UserProfileScreen() {
        super("User Profile", "/profile");
    }

    @Override
    public void render() {
        System.out.println("Current user: " + app.getCurrentUser());
    }
}
