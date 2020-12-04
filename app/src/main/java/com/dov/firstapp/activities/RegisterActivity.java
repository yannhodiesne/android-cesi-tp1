package com.dov.firstapp.activities;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;

import com.dov.firstapp.R;
import com.dov.firstapp.models.Account;
import com.dov.firstapp.services.AccountService;

public class RegisterActivity extends AppCompatActivity {
    private AppCompatEditText loginEdit;
    private AppCompatEditText usernameEdit;
    private AppCompatEditText passwordEdit;
    private AppCompatButton createButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setViewItems();
    }

    private void setViewItems() {
        loginEdit = findViewById(R.id.login_text);
        usernameEdit = findViewById(R.id.name_text);
        passwordEdit = findViewById(R.id.password_text);
        createButton = findViewById(R.id.create_account_button);

        createButton.setOnClickListener(view -> {
            Account account = new Account(usernameEdit.getText().toString(), loginEdit.getText().toString(), passwordEdit.getText().toString());
            AccountService.AccountResponse response = AccountService.add(account);

            if (response.isSuccessful()) {
                Toast.makeText(RegisterActivity.this, "Successfully registered", Toast.LENGTH_LONG).show();
                finish();
            } else {
                Toast.makeText(RegisterActivity.this, response.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
