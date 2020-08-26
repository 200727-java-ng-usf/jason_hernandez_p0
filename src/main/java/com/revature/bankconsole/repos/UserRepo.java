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

    private String baseQuery = "SELECT * FROM bank-console.users u ";

    public UserRepo() {
        System.out.println("[LOG] - Instantiating " + this.getClass().getName());
    }

    public Optional<UserInfo> findUserByCredentials(String username, String password) {

        Optional<UserInfo> _user = Optional.empty();

        try (Connection conn = ConnectionFactory.getInstance().getConnection()){
            String sql =  baseQuery +
                    "WHERE username = ? AND password = ?;";
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

    public Optional<UserInfo> findUserByUsername(String username) {

        Optional<UserInfo> _user = Optional.empty();

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            String sql = baseQuery + "WHERE username = ?;";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);

            ResultSet rs = pstmt.executeQuery();
            _user = mapResultSet(rs).stream().findFirst();


        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return _user;

    }


    public UserInfo save(UserInfo newUser) {

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            String sql = "INSERT INTO bank-console.users " +
                    "(first_name, last_name, email) " +
                    "VALUES (?, ?, ?);";

            PreparedStatement pstmt = conn.prepareStatement(sql, new String[]{"id"});

            pstmt.setString(1, newUser.getFirstName());
            pstmt.setString(2, newUser.getLastName());
            pstmt.setString(3, newUser.getEmail());

            int affectedRows = pstmt.executeUpdate();
            // check the affected rows
            if (affectedRows > 0) {
                // get the ID back
                ResultSet rs = pstmt.getGeneratedKeys();
                while (rs.next()) {
                    newUser.setId(rs.getInt(1));
                }
            }

            String sql2 = "INSERT INTO bank-console.user_credentials " +
                    "(username, password) " +
                    "VALUES (?, ?);";

            PreparedStatement pstmt2 = conn.prepareStatement(sql2, new String[]{"id"});
            pstmt2.setString(1, newUser.getUserName());
            pstmt2.setString(2, newUser.getPassword());

            int affectedRows2 = pstmt2.executeUpdate();
            // check the affected rows
            if (affectedRows2 > 0) {
                // get the ID back
                ResultSet rs = pstmt2.getGeneratedKeys();
                while (rs.next()) {
                    newUser.setId(rs.getInt(1));
                }
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return newUser;
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
