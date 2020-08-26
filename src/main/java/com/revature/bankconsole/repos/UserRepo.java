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

            String sql2 = baseQuery + "WHERE username = ?;";
            PreparedStatement pstmt2 = conn.prepareStatement(sql2);
            pstmt2.setString(1, username);

            ResultSet rs = pstmt2.executeQuery();
            _user = mapResultSet(rs).stream().findFirst();


        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return _user;

    }


    public UserInfo save(UserInfo newUser) {

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            String sql3 = "INSERT INTO bank-console.users " +
                    "(first_name, last_name, email) " +
                    "VALUES (?, ?, ?);";

            PreparedStatement pstmt3 = conn.prepareStatement(sql3, new String[]{"id"});

            pstmt3.setString(1, newUser.getFirstName());
            pstmt3.setString(2, newUser.getLastName());
            pstmt3.setString(3, newUser.getEmail());

            int affectedRows = pstmt3.executeUpdate();
            // check the affected rows
            if (affectedRows > 0) {
                // get the ID back
                ResultSet rs = pstmt3.getGeneratedKeys();
                while (rs.next()) {
                    newUser.setId(rs.getInt(1));
                }
            }

            String sql4 = "INSERT INTO bank-console.user_credentials " +
                    "(username, password) " +
                    "VALUES (?, ?);";

            PreparedStatement pstmt4 = conn.prepareStatement(sql4, new String[]{"id"});
            pstmt4.setString(1, newUser.getUserName());
            pstmt4.setString(2, newUser.getPassword());

            int affectedRows2 = pstmt4.executeUpdate();
            // check the affected rows
            if (affectedRows2 > 0) {
                // get the ID back
                ResultSet rs = pstmt4.getGeneratedKeys();
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
