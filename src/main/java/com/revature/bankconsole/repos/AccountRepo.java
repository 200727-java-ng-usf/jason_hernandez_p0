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

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            String sql = baseQuery +
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

            String sql2 = "INSERT INTO bank-console.user_accounts " +
                    "(user_id, checking_number) " +
                    "VALUES (?, ?)";

            PreparedStatement pstmt2 = conn.prepareStatement(sql, new String[]{"id"});

            pstmt2.setFloat(1, UserInfo.getId());
            pstmt2.setFloat(2, newAccount.getAccountNumber());

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return newAccount;
    }

        private Set<AccountRepo> mapResultSet(ResultSet rs) throws SQLException {
            Set<AccountInfo> accounts = new HashSet<>();

            while (rs.next()) {
                AccountInfo temp = new AccountInfo();
                temp.setAccountNumber(rs.getInt("account_number"));
                temp.setBalance(rs.getFloat("balance"));

                accounts.add(temp);
            }
            return accounts;
        }
    }
}
