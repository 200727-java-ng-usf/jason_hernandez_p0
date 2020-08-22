package com.revature.bankconsole.repos;

import com.revature.bankconsole.accounts.CheckingAccount;
import com.revature.bankconsole.accounts.SavingsAccount;
import com.revature.bankconsole.utilities.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class AccountRepo {

    private String baseQuery = "SELECT * FROM revabooks.app_users au " +
            "JOIN revabooks.user_roles ur " +
            "ON au.role_id = ur.id ";

    public AccountRepo() {
        System.out.println("[LOG] - Instantiating " + this.getClass().getName());
    }

    public Optional<SavingsAccount> findAccountNumber(String username, String password) {

        Optional<SavingsAccount> _account = Optional.empty();

        try (Connection conn = ConnectionFactory.getInstance().getConnection()){
            String sql =  baseQuery +
                    "WHERE account_number = ? AND balance = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, account_number);
            pstmt.setString(2, balance);

            ResultSet rs = pstmt.executeQuery();

            _account = mapResultSet(rs).stream().findFirst();

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }

        return _account;
    }

    public Optional<CheckingAccount> findBalance(String username) {

        Optional<CheckingAccount> _account = Optional.empty();

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            String sql = baseQuery + "WHERE username = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);

            ResultSet rs = pstmt.executeQuery();
            _account = mapResultSet(rs).stream().findFirst();


        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return _account;

    }
}
