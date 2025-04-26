package com.example.projet;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class EditTransactionActivity extends AppCompatActivity {

    private EditText editAmount, editCategory,editNote;
    private Spinner spinnerType;
    private AppDataBase db;
    private Transaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //initialisation des variables
        setContentView(R.layout.edit_transaction);
        int id = getIntent().getIntExtra("transaction_id", -1);
        db = AppDataBase.getDatabase(this);
        editAmount = findViewById(R.id.edit_amount);
        editCategory = findViewById(R.id.edit_category);
        editNote = findViewById(R.id.edit_note);
        spinnerType = findViewById(R.id.spinner_type);
        Button btnSave = findViewById(R.id.btn_save);
        //importation des elements de la liste deroulante declaré dans strings.xml
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.transaction_type_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerType.setAdapter(adapter);

        // Charger les données à modifier
        new Thread(() -> {
            transaction = db.transactionDao().getTransactionById(id);
            runOnUiThread(() -> {
                if (transaction != null) {
                    editAmount.setText(String.valueOf(transaction.getAmount()));
                    editCategory.setText(transaction.getCategory());
                    editNote.setText(transaction.getNote());
                    spinnerType.setSelection(transaction.getType().equals("Revenu") ? 0 : 1);
                }
            });
        }).start();


//l'enregistrement de la modification
        btnSave.setOnClickListener(v -> {
            transaction.setAmount(Double.parseDouble(editAmount.getText().toString()));
            transaction.setCategory(editCategory.getText().toString());
            transaction.setNote(editNote.getText().toString());
            transaction.setType(spinnerType.getSelectedItem().toString());

            new Thread(() -> {
                db.transactionDao().update(transaction);
                finish();
            }).start();
        });
    }
}
