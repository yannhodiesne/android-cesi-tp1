package com.dov.firstapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.dov.firstapp.R;
import com.dov.firstapp.services.AccountService;

public class MainActivity extends AppCompatActivity {
    AppCompatEditText loginEdit;
    AppCompatEditText passwordEdit;
    AppCompatButton loginButton;
    AppCompatButton registerButton;

    public static final String LOGIN_KEY = "LOGIN_KEY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setViewItems();
    }

    private void setViewItems() {
        loginEdit = findViewById(R.id.login_text);
        passwordEdit = findViewById(R.id.password_text);
        loginButton = findViewById(R.id.login_button);
        registerButton = findViewById(R.id.register_button);

        loginButton.setOnClickListener(view -> {
            AccountService.AccountResponse response = AccountService.login(loginEdit.getText().toString(), passwordEdit.getText().toString());

            if (response.isSuccessful()) {
                Intent intent = new Intent(MainActivity.this, HelloActivity.class);
                intent.putExtra(LOGIN_KEY, response.getAccount().getLogin());
                startActivity(intent);
            } else {
                Toast.makeText(MainActivity.this, response.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        registerButton.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, RegisterActivity.class)));
    }
}
