package com.dov.firstapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;

import com.dov.firstapp.adapters.EssentialsRecyclerViewAdapter;
import com.dov.firstapp.R;

import java.util.ArrayList;
import java.util.Arrays;

public class AndroidEssentialsActivity extends AppCompatActivity {
    private final ArrayList<String> androidEssentials = new ArrayList<>(Arrays.asList("Activity", "Intent",
            "Layout", "Manifest", "Gradle", "Fragment","LifeCycle" , "Sharedpreference","ROOM","LiveData", "MVVM"));
    private EssentialsRecyclerViewAdapter essentialsRecyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_essentials);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setViewItems();
    }

    private void setViewItems() {
        RecyclerView essentialsRecyclerView = findViewById(R.id.essentials_RV);
        essentialsRecyclerViewAdapter = new EssentialsRecyclerViewAdapter(androidEssentials, new EssentialsRecyclerViewAdapter.DeleteCallback() {
            @Override
            public void onDelete(String essential) {
                androidEssentials.remove(essential);
                essentialsRecyclerViewAdapter.setEssentials(androidEssentials);
            }
        });
        essentialsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        essentialsRecyclerView.setAdapter(essentialsRecyclerViewAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}