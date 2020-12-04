package com.dov.firstapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dov.firstapp.R;
import com.dov.firstapp.adapters.AccountsRecyclerViewAdapter;
import com.dov.firstapp.services.AccountService;

public class AccountsActivity extends AppCompatActivity {
    private AccountsRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accounts);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setViewItems();
    }

    private void setViewItems() {
        RecyclerView recycler = findViewById(R.id.accounts_recycler);
        AppCompatButton logoutButton = findViewById(R.id.logout_button);

        adapter = new AccountsRecyclerViewAdapter(AccountService.getAccounts(), (AccountsRecyclerViewAdapter.DeleteCallback) username -> {
            AccountService.remove(username);
            adapter.setAccounts(AccountService.getAccounts());
        });

        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setAdapter(adapter);

        logoutButton.setOnClickListener(view -> startActivity(new Intent(AccountsActivity.this, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)));
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
