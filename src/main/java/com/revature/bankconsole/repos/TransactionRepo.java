package com.revature.bankconsole.repos;

import com.revature.bankconsole.accounts.CheckingAccount;
import com.revature.bankconsole.accounts.SavingsAccount;
import com.revature.bankconsole.models.AccountInfo;
import com.revature.bankconsole.utilities.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class TransactionRepo {

    AccountInfo queryAccount;

    public void save(SavingsAccount newTransaction) {


        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            // Pre-made query template
            String sql = "Update bank-console.savings_account " +
                    "Set balance = '?' " +
                    "Where account_number = ?";
            // Prepare a statement
            PreparedStatement pstmt = conn.prepareStatement(sql, new String[]{"id"});
            // Retrieve values from getters
            pstmt.setFloat(1, newTransaction.getBalance());
            pstmt.setInt(2, queryAccount.getAccountNumber());

            int affectedRows = pstmt.executeUpdate();
            // check the affected rows
            if (affectedRows > 0) {
                // get the ID back
                ResultSet rs = pstmt.getGeneratedKeys();
                while (rs.next()) {
                    newTransaction.setBalance(rs.getInt(1));
                }

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void save(CheckingAccount newTransaction) {

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            String sql = "UPDATE bank-console.checking_account " +
                    "Set balance = '?'  " +
                    "Where account_number = ?";

            PreparedStatement pstmt = conn.prepareStatement(sql, new String[]{"id"});

            pstmt.setFloat(1, newTransaction.getBalance());
            pstmt.setInt(2, queryAccount.getAccountNumber());

            int affectedRows = pstmt.executeUpdate();
            // check the affected rows
            if (affectedRows > 0) {
                // get the ID back
                ResultSet rs = pstmt.getGeneratedKeys();
                while (rs.next()) {
                    newTransaction.setBalance(rs.getInt(1));
                }

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
