package com.dov.firstapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.bumptech.glide.Glide;
import com.dov.firstapp.R;
import com.dov.firstapp.services.AccountService;

public class HelloActivity extends AppCompatActivity {
    AppCompatTextView usernameText;
    AppCompatImageView profileImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setViewItems();
    }

    private void setViewItems() {
        AppCompatButton logoutButton = findViewById(R.id.logout_button);
        usernameText = findViewById(R.id.login_display_header);
        profileImage = findViewById(R.id.profile_image);

        usernameText.setText(AccountService.get(getIntent().getStringExtra(MainActivity.LOGIN_KEY)).getUsername());

        Glide.with(this)
                .load("https://source.unsplash.com/random")
                .placeholder(R.drawable.ic_launcher_background)
                .into(profileImage);

        findViewById(R.id.essentials_button).setOnClickListener(view -> startActivity(new Intent(HelloActivity.this, AndroidEssentialsActivity.class)));
        findViewById(R.id.accounts_button).setOnClickListener(view -> startActivity(new Intent(HelloActivity.this, AccountsActivity.class)));

        logoutButton.setOnClickListener(view -> startActivity(new Intent(HelloActivity.this, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)));
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
