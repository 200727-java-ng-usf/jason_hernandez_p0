package com.revature.bankconsole.repos;

import com.revature.bankconsole.accounts.SavingsAccount;
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

public class SavingsRepo {

    private String baseQuery = "SELECT * FROM bank-console.savings_accounts ";


    public Optional<SavingsAccount> findAccountNumber(int savings_number, float balance) {

        Optional<SavingsAccount> _account = Optional.empty();

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            String sql = baseQuery +
                    "WHERE account_number = ? AND balance = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, savings_number);
            pstmt.setFloat(2, balance);

            ResultSet rs = pstmt.executeQuery();

            _account = mapResultSet(rs).stream().findFirst();

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }

        return _account;
    }
    public AccountInfo save(AccountInfo newAccount) {

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            String sql = "INSERT INTO bank-console.savings_accounts " +
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

            pstmt2.setFloat(1, newAccount.getAccountNumber());
            pstmt2.setFloat(2, newAccount.getAccountNumber());

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return newAccount;
    }
        private Set<SavingsAccount> mapResultSet(ResultSet rs) throws SQLException {
            Set<SavingsAccount> accounts = new HashSet<>();

            while (rs.next()) {
                SavingsAccount temp = new SavingsAccount();
                UserAccounts temp2 = new UserAccounts();
                temp2.setAccountNumber(rs.getInt("account_number"));
                temp.setBalance(rs.getFloat("balance"));

                accounts.add(temp);
            }
            return accounts;
        }
    }

