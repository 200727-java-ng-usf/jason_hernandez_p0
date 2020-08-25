package com.revature.bankconsole.repos;

import com.revature.bankconsole.accounts.CheckingAccount;
import com.revature.bankconsole.accounts.SavingsAccount;
import com.revature.bankconsole.accounts.UserAccounts;
import com.revature.bankconsole.models.AccountInfo;
import com.revature.bankconsole.models.UserInfo;
import com.revature.bankconsole.utilities.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class CheckingRepo {

    private String baseQuery = "SELECT * FROM bank-console.savings_accounts ";

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

    public AccountInfo save(AccountInfo newAccount) {

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            String sql = "INSERT INTO bank-console.checking_accounts " +
                    "(account_number, balance) " +
                    "VALUES (?, ?)";

            PreparedStatement pstmt = conn.prepareStatement(sql, new String[]{"id"});

            pstmt.setFloat(1, newAccount.getAccountNumber());
            pstmt.setFloat(2, newAccount.getBalance());


            int affectedRows = pstmt.executeUpdate();
            // check the affected rows
            if (affectedRows > 0) {
                // get the ID back
                ResultSet rs = pstmt.getGeneratedKeys();
                while (rs.next()) {
                    newAccount.setAccountNumber(rs.getInt(1));
                }

            }


        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return newAccount;
    }

    private Set<CheckingAccount> mapResultSet(ResultSet rs) throws SQLException {
        Set<CheckingAccount> accounts = new HashSet<>();

        while (rs.next()) {
            CheckingAccount temp = new CheckingAccount();
            UserAccounts temp2 = new UserAccounts();
            temp2.setCheckingNumber(rs.getInt("account_number"));
            temp.setBalance(rs.getFloat("balance"));

            accounts.add(temp);
        }
        return accounts;
    }
}
