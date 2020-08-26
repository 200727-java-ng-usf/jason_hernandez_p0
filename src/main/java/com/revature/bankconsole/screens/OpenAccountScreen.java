package com.revature.bankconsole.screens;

import com.revature.bankconsole.models.AccountInfo;
import com.revature.bankconsole.models.UserInfo;
import com.revature.bankconsole.utilities.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.revature.bankconsole.AppDriver.app;

public class OpenAccountScreen extends Screen {

    public OpenAccountScreen() {
        super("OpenAccountScreen", "/newaccount");
        System.out.println("[LOG] - Instantiating " + super.getName());
    }

    @Override
    public void render() {
        System.out.println("What kind of account do you want to open?");
        System.out.println("1) Savings account");
        System.out.println("2) Checking account");
        System.out.println("3) Logout");

        try {
            System.out.print("> ");
            String userSelection = app.getConsole().readLine();

            switch (userSelection) {
                case "1":
                    break;
                case "2":
                    break;
                case "3":
                    app.setCurrentUser(null);
                    app.getRouter().navigate("/home");
                    break;
                default:
                    System.out.println("[404] - Invalid selection!");
                    app.getRouter().navigate("/dashboard");
            }

//            public UserInfo save(AccountInfo newAccount) {
//                try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
//                    String sql = "INSERT INTO bank-console.savings_accounts " +
//                            "(balance, interest_rate) " +
//                            "VALUES (0, ?)";
//
//                    PreparedStatement pstmt = conn.prepareStatement(sql, new String[]{"id"});
//
//                    pstmt.setString(1, newAccount.getUserName());
//
//                    int affectedRows = pstmt.executeUpdate();
//                    // check the affected rows
//                    if (affectedRows > 0) {
//                        // get the ID back
//                        ResultSet rs = pstmt.getGeneratedKeys();
//                        while (rs.next()) {
//                            newUser.setId(rs.getInt(1));
//                        }
//
//                    }
//                } catch (SQLException ex) {
//                    System.out.println(ex.getMessage());
//                }
//                return newAccount;


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
