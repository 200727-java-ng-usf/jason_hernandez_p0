package com.revature.bankconsole.repos;

import com.revature.bankconsole.accounts.CheckingAccount;
import com.revature.bankconsole.accounts.SavingsAccount;
import com.revature.bankconsole.models.AccountInfo;
import com.revature.bankconsole.models.UserInfo;
import com.revature.bankconsole.utilities.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class TransactionRepo {

    public void save(SavingsAccount newTransaction) {

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            // Pre-made query template
            String sql = "Update bank-console.savings_account " +
                    "Set balance = ? " +
                    "Where account_number = ?";
            // Prepare a statement
            PreparedStatement pstmt = conn.prepareStatement(sql, new String[]{"id"});
            // Retrieve values from getters
            pstmt.setFloat(1, newTransaction.getBalance());
            pstmt.setInt(2, AccountInfo.getAccountNumber());

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
                    "Set balance = ?  " +
                    "Where account_number = ?";

            PreparedStatement pstmt = conn.prepareStatement(sql, new String[]{"id"});

            pstmt.setString(1, newTransaction.getBalance());
            pstmt.setInt(2, AccountInfo.getAccountNumber());

            int affectedRows = pstmt.executeUpdate();
            // check the affected rows
            if (affectedRows > 0) {
                // get the ID back
                ResultSet rs = pstmt.getGeneratedKeys();
                while (rs.next()) {
                    newUser.setTrans(rs.getInt(1));
                }

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

//    private Set<AppUser> mapResultSet(ResultSet rs) throws SQLException {
//        Set<AppUser> users = new HashSet<>();
//
//        while (rs.next()) {
//            AppUser temp = new AppUser();
//            temp.setId(rs.getInt("id"));
//            temp.setUserName(rs.getString("username"));
//            temp.setPassword(rs.getString("password"));
//            temp.setFirstName(rs.getString("first_name"));
//            temp.setLastName(rs.getString("last_name"));
//            temp.setRole(Role.getByName(rs.getString("name")));
//
//            users.add(temp);
//        }
//        return users;
}
