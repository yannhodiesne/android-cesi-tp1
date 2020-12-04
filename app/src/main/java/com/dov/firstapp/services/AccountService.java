package com.dov.firstapp.services;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.dov.firstapp.models.Account;

import java.util.ArrayList;
import java.util.List;

public class AccountService {
    private static final List<Account> accounts = new ArrayList<>();

    @NonNull
    public static AccountResponse add(@NonNull Account account)
    {
        if (usernameExists(account.getUsername()))
            return new AccountResponse("Username already exists");
        else if (loginExists(account.getLogin()))
            return new AccountResponse("Login already exists");

        accounts.add(account);
        return new AccountResponse(account);
    }

    @Nullable
    public static Account get(@NonNull String login) {
        for (Account a : accounts) {
            if (a.getLogin().equals(login)) {
                return a;
            }
        }

        return null;
    }

    public static void remove(String login) {
       Account account = get(login);

       if (account != null)
           accounts.remove(account);
    }

    @NonNull
    public static AccountResponse login(String login, String password) {
        Account account = get(login);

        if (account == null)
            return new AccountResponse("User not found");

        if (!account.getPassword().equals(password))
            return new AccountResponse("Wrong password");

        return new AccountResponse(account);
    }

    @NonNull
    public static List<Account> getAccounts() {
        return new ArrayList<>(accounts);
    }

    private static boolean usernameExists(String username) {
        for (Account a : accounts) {
            if (a.getUsername().equals(username))
                return true;
        }

        return false;
    }

    private static boolean loginExists(String login) {
        for (Account a : accounts) {
            if (a.getLogin().equals(login))
                return true;
        }

        return false;
    }

    public static class AccountResponse {
        private final boolean success;
        private String message;
        private Account account;

        public AccountResponse(String message)
        {
            this.success = false;
            this.message = message;
        }

        public AccountResponse(Account account)
        {
            this.success = true;
            this.account = account;
        }

        public boolean isSuccessful() {
            return success;
        }

        public String getMessage() {
            return message;
        }

        public Account getAccount() {
            return account;
        }
    }
}
