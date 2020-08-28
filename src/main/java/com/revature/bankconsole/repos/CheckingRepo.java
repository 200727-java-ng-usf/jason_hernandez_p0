package com.revature.bankconsole.repos;

import com.revature.bankconsole.accounts.CheckingAccount;
import com.revature.bankconsole.accounts.UserAccounts;
import com.revature.bankconsole.models.AccountInfo;
import com.revature.bankconsole.utilities.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class CheckingRepo {

    private String baseQuery = "SELECT * FROM bankconsole.checking_accounts ";

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

    public Optional<CheckingAccount> findAccountByNumber(Integer checking_number) {

        Optional<CheckingAccount> _account = Optional.empty();

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            String sql = baseQuery + "WHERE checking_number = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, checking_number);

            ResultSet rs = pstmt.executeQuery();
            _account = mapResultSet(rs).stream().findFirst();


        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return _account;
    }

        public AccountInfo save(AccountInfo newAccount) {

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            String sql = "INSERT INTO bankconsole.checking_accounts " +
                    "(balance) " +
                    "VALUES (?)";

            PreparedStatement pstmt = conn.prepareStatement(sql, new String[]{"id"});

            pstmt.setFloat(1, newAccount.getBalance());


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
            temp2.setAccountNumber(rs.getInt("account_number"));
            temp.setBalance(rs.getFloat("balance"));

            accounts.add(temp);
        }
        return accounts;
    }
}
