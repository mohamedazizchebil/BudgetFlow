package com.example.projet;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class TransactionDetailsActivity extends AppCompatActivity {

    private AppDataBase db;
    private Transaction transaction;
    private int transactionId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //initialisation des variables
        setContentView(R.layout.transaction_details);
        TextView detailAmount = findViewById(R.id.detail_amount);
        TextView detailType = findViewById(R.id.detail_type);
        TextView detailCategory = findViewById(R.id.detail_category);
        TextView detailDate = findViewById(R.id.detail_date);
        TextView detailNote = findViewById(R.id.detail_note);
        Button btnDelete = findViewById(R.id.btn_delete);
        Button btnEdit = findViewById(R.id.btn_edit);

        transactionId = getIntent().getIntExtra("transaction_id", 0);
        db = AppDataBase.getDatabase(this);
        //vérification et affichage de données
        if (transactionId != 0) {
            new Thread(() -> {
                Transaction transaction = db.transactionDao().getTransactionById(transactionId);
                runOnUiThread(() -> {
                    if (transaction != null) {
                        detailAmount.setText(String.format("%.2f DT", transaction.getAmount()));
                        detailType.setText(transaction.getType());
                        detailCategory.setText(transaction.getCategory());
                        detailDate.setText(transaction.getDate());
                        detailNote.setText(transaction.getNote());
                        //button pour modifier la transaction dans l'activité edit
                        btnEdit.setOnClickListener(v -> {

                            if (transaction != null) {
                                Intent intent = new Intent(this, EditTransactionActivity.class);
                                intent.putExtra("transaction_id", transaction.getId());
                                startActivity(intent);
                            }
                        });
                    }
                });
            }).start();

        }
        //button pour la suppression de la transaction avec alert
        btnDelete.setOnClickListener(v -> {
            new AlertDialog.Builder(this)
                    .setTitle("Supprimer")
                    .setMessage("Supprimer cette transaction ?")
                    .setPositiveButton("Oui", (dialog, which) -> {
                        new Thread(() -> {
                            Transaction transaction = db.transactionDao().getTransactionById(transactionId);
                            db.transactionDao().delete(transaction);
                            runOnUiThread(this::finish);
                        }).start();
                    })
                    .setNegativeButton("Non", null)
                    .show();
        });




    }

    //redefinition de la methode onResume (récuperation de donnnées lors de retour à l'activité de details
    @Override
    protected void onResume() {
        super.onResume();
        if (transactionId != 0) {
            new Thread(() -> {
                transaction = db.transactionDao().getTransactionById(transactionId);
                runOnUiThread(() -> {
                    if (transaction != null) {
                        ((TextView) findViewById(R.id.detail_amount)).setText(String.format("%.2f DT", transaction.getAmount()));
                        ((TextView) findViewById(R.id.detail_type)).setText(transaction.getType());
                        ((TextView) findViewById(R.id.detail_category)).setText(transaction.getCategory());
                        ((TextView) findViewById(R.id.detail_date)).setText(transaction.getDate());
                        ((TextView) findViewById(R.id.detail_note)).setText(transaction.getNote());
                    }
                });
            }).start();
        }
}}
