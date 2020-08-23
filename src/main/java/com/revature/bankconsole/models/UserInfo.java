package com.revature.bankconsole.models;

import java.util.Objects;

public class UserInfo {

    // Set up fields
    private Integer id;
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private String email;

    public UserInfo(){}

    // Basic registration: first and last name, email, pick a username and password
    public UserInfo(String firstName, String lastName, String userName,
                    String password, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.email = email;
    }

    // Assigns an id
    public UserInfo(Integer id, String firstName, String lastName,
                    String userName, String password, String email) {
        this(firstName, lastName, userName, password, email);
        this.id = id;
    }

    // Makes a copy (for thread safety)
    public UserInfo(UserInfo copy) {
        this(copy.id, copy.firstName, copy.lastName, copy.userName,
                copy.password, copy.email);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "appUser{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", email=" + email +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserInfo appUser = (UserInfo) o;
        return Objects.equals(id, appUser.id) &&
                Objects.equals(firstName, appUser.firstName) &&
                Objects.equals(lastName, appUser.lastName) &&
                Objects.equals(userName, appUser.userName) &&
                Objects.equals(password, appUser.password) &&
                Objects.equals(email, appUser.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName,
                userName, password, email);
    }
}
