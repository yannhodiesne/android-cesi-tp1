package com.dov.firstapp.models;

public class Account {
    private String username;
    private String login;
    private String password;

    public Account() {

    }

    public Account(String username, String login, String password) {
        this.username = username;
        this.login = login;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
