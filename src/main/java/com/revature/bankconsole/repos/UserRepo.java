package com.revature.bankconsole.repos;

import com.revature.bankconsole.models.UserInfo;
import com.revature.bankconsole.utilities.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class UserRepo {

    public UserRepo() {

    }

    private String baseQuery = "SELECT * FROM bankconsole.user_credentials ";

    // For finding existing customer
    public Optional<UserInfo> findUserByCredentials(String username, String password) {

        Optional<UserInfo> _user = Optional.empty();

        try (Connection conn = ConnectionFactory.getInstance().getConnection()){
            String sql =  baseQuery +
                    "WHERE username = ? AND password = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, password);

            ResultSet rs = pstmt.executeQuery();

            _user = mapResultSet(rs).stream().findFirst();

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }

        return _user;
    }

    // Another way to find an existing user
    public Optional<UserInfo> findUserByUsername(String username) {

        Optional<UserInfo> _user = Optional.empty();

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            String sql2 = baseQuery + "WHERE username = ?";
            PreparedStatement pstmt2 = conn.prepareStatement(sql2);
            pstmt2.setString(1, username);

            ResultSet rs = pstmt2.executeQuery();
            _user = mapResultSet(rs).stream().findFirst();


        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return _user;

    }

    // When we need to refer to account number
    public Optional<UserInfo> findUserByAccountNumber(Integer accountNumber) {
        Optional<UserInfo> _user = Optional.empty();

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            String pgsql2 = "Select * FROM user_accounts WHERE account_number = ?";
            PreparedStatement pstmt = conn.prepareStatement(pgsql2);
            pstmt.setInt(1, accountNumber);

            ResultSet rs = pstmt.executeQuery();
            _user = mapResultSet(rs).stream().findFirst();
        } catch (SQLException sqle){
            sqle.printStackTrace();
        }
        return _user;
    }

    // For registring a new customer
    public boolean save(UserInfo newUser) {

        boolean success = false;

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            String sql3 = "INSERT INTO bankconsole.users " +
                    "(first_name, last_name, email) " +
                    "VALUES (?, ?, ?)";

            PreparedStatement pstmt3 = conn.prepareStatement(sql3, new String[]{"id"});

            pstmt3.setString(1, newUser.getFirstName());
            pstmt3.setString(2, newUser.getLastName());
            pstmt3.setString(3, newUser.getEmail());

            pstmt3.executeUpdate();

            String sql4 = "INSERT INTO bankconsole.user_credentials " +
                    "(username, password) " +
                    "VALUES (?, ?)";

            PreparedStatement pstmt4 = conn.prepareStatement(sql4, new String[]{"id"});
            pstmt4.setString(1, newUser.getUserName());
            pstmt4.setString(2, newUser.getPassword());

            pstmt4.executeUpdate();

            success = true;

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return success;
    }


    private Set<UserInfo> mapResultSet(ResultSet rs) throws SQLException {
        Set<UserInfo> users = new HashSet<>();

        while (rs.next()) {
            UserInfo temp = new UserInfo();
            temp.setId(rs.getInt("id"));
            temp.setUserName(rs.getString("username"));
            temp.setPassword(rs.getString("password"));
            temp.setFirstName(rs.getString("first_name"));
            temp.setLastName(rs.getString("last_name"));
            temp.setEmail(rs.getString(rs.getString("email")));

            users.add(temp);
        }
        return users;
    }


}
