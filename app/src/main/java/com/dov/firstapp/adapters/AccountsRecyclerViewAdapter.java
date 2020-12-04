package com.dov.firstapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.dov.firstapp.R;
import com.dov.firstapp.models.Account;

import java.util.List;

public class AccountsRecyclerViewAdapter extends RecyclerView.Adapter<AccountsRecyclerViewAdapter.AccountsViewHolder> {
    private List<Account> accounts;
    private final AccountsRecyclerViewAdapter.DeleteCallback deleteCallback;

    public interface DeleteCallback {
        void onDelete(String essential);
    }

    public AccountsRecyclerViewAdapter(List<Account> accounts, AccountsRecyclerViewAdapter.DeleteCallback deleteCallback){
        this.accounts = accounts;
        this.deleteCallback = deleteCallback;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AccountsRecyclerViewAdapter.AccountsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.accounts_list_item, parent,false);
        return new AccountsRecyclerViewAdapter.AccountsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AccountsRecyclerViewAdapter.AccountsViewHolder holder, int position) {
        holder.bind(accounts.get(position), deleteCallback);
    }

    @Override
    public int getItemCount() {
        return accounts.size();
    }

    public static class AccountsViewHolder extends RecyclerView.ViewHolder {
        AppCompatImageView deleteImage;
        AppCompatTextView usernameText;

        public AccountsViewHolder(@NonNull View itemView) {
            super(itemView);
            deleteImage = itemView.findViewById(R.id.delete);
            usernameText = itemView.findViewById(R.id.username);
        }

        public void bind(@NonNull Account account, AccountsRecyclerViewAdapter.DeleteCallback deleteCallback){
            usernameText.setText(account.getUsername());
            deleteImage.setOnClickListener(view -> deleteCallback.onDelete(account.getLogin()));
        }
    }
}
