package com.revature.bankconsole.accounts;

import com.revature.bankconsole.models.UserInfo;
import com.revature.bankconsole.utilities.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserAccounts {
    // Declare fields
    static int accountNumber;
    static double accountBalance;


    private UserInfo accessUserId;

    public UserAccounts savingsQuery() {

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            // Query the database
            String sql = "Select * FROM savings_accounts ca JOIN user_accounts ua " +
                    "ON ca.account_number=ua.savings_number " +
                    "AND ua.user_id = ?";
            PreparedStatement pstmt1 = conn.prepareStatement(sql);
            pstmt1.setInt(1, accessUserId.getId());

            int affectedRows = pstmt1.executeUpdate();
            // check the affected rows
            if (affectedRows > 0) {
                // get the ID back
                ResultSet rs = pstmt1.getGeneratedKeys();
                return (UserAccounts) rs;
                }


        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return UserAccounts.this;
    }

    public UserAccounts checkingQuery() {


        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            // Query the database
            String sql = "Select account_number, balance FROM checking_accounts ca " +
                    "JOIN user_accounts ua " +
                    "ON ca.account_number=ua.checking_number " +
                    "AND ua.user_id = ?";
            PreparedStatement pstmt2 = conn.prepareStatement(sql);
            pstmt2.setInt(1, accessUserId.getId());

            int affectedRows = pstmt2.executeUpdate();
            // check the affected rows
            if (affectedRows > 0) {
                // get the ID back
                ResultSet rs = pstmt2.getGeneratedKeys();
                return (UserAccounts) rs;
            }


        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return UserAccounts.this;
    }


    public Integer getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int savingsNumber) {
        accountNumber = savingsNumber;
    }

    public float getAccountBalance() {
        return (float) accountBalance;
    }

    public void setAccountBalance(double savingsBalance) {
        accountBalance = savingsBalance;
    }

}
