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

    public CheckingRepo() {

    }
    // For deposits and withdrawals
    public void updateAccount(int accountNumber, float balance) {

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            String pgsql = "UPDATE bankconsole.checking_accounts " +
                    "SET balance = ? " +
                    "WHERE account_number = ?";
            PreparedStatement pstmt = conn.prepareStatement(pgsql);
            pstmt.setFloat(1, balance);
            pstmt.setInt(2, accountNumber);
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }

    private String baseQuery = "SELECT * FROM bankconsole.checking_accounts ";

    public Optional<CheckingAccount> findBalance(Integer user_id) {

        Optional<CheckingAccount> _account = Optional.empty();

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            String sql = baseQuery +
                    "JOIN user_accounts ua \n" +
                    "ON ca.account_number=ua.checking_number \n" +
                    "AND ua.user_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, user_id);

            ResultSet rs = pstmt.executeQuery();
            Set<CheckingAccount> acc = new HashSet<>();
            while (rs.next()) {
                CheckingAccount temp = new CheckingAccount();
                temp.setBalance(rs.getFloat("balance"));
                temp.setAccountNo(rs.getInt("checking_no"));
                System.out.println(temp);
                acc.add(temp);
            }
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
            Set<CheckingAccount> acc = new HashSet<>();
            while (rs.next()) {
                CheckingAccount temp = new CheckingAccount();
                temp.setBalance(rs.getFloat("balance"));
                temp.setAccountNo(rs.getInt("checking_no"));
                System.out.println(temp);
                acc.add(temp);
            }
            return acc.stream().findFirst();


        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return _account;
    }
        // For adding a new account
        public AccountInfo saveNewAccount(AccountInfo newAccount) {

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            String sql = "INSERT INTO bankconsole.checking_accounts " +
                    "(account_number, balance) " +
                    "VALUES (?, ?)";

            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, newAccount.getAccountNumber());
            pstmt.setFloat(2, 0);

            pstmt.executeUpdate();

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
