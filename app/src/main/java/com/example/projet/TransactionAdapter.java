package com.example.projet;

import android.content.Intent;
import android.graphics.Color;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.ViewHolder>{

    private List<Transaction> data;
    //initialisation de données
    public TransactionAdapter(List<Transaction> data) {
        this.data = data;
    }


    //redefinition des methodes de l'adapter pour l'affichage

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_transaction, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Transaction transaction = data.get(position);
        holder.amountTextView.setText(String.format("%.2f DT", transaction.getAmount()));
        holder.categoryTextView.setText(transaction.getCategory());
        holder.dateTextView.setText(transaction.getDate());
        holder.textType.setText(transaction.getType());
        if ("Dépense".equals(transaction.getType())) {
            holder.textType.setTextColor(Color.parseColor("#FF0000"));

        } else {
            holder.textType.setTextColor(Color.parseColor("#00FF00"));
        }
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), TransactionDetailsActivity.class);
            intent.putExtra("transaction_id", transaction.getId());
            v.getContext().startActivity(intent);
        });


    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView amountTextView;
        TextView categoryTextView;
        TextView dateTextView;
        TextView textType;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            amountTextView = itemView.findViewById(R.id.text_amount);
            categoryTextView = itemView.findViewById(R.id.text_category);
            dateTextView = itemView.findViewById(R.id.text_date);
            textType= itemView.findViewById(R.id.text_type);
        }
    }
}
