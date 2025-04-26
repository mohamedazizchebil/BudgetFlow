package com.example.projet;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddTransactionActivity extends AppCompatActivity {

    private EditText editAmount, editCategory, editNote;
    private Spinner spinnerType;
    private AppDataBase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //initialisation des variables
        setContentView(R.layout.add_transaction);
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
        db = AppDataBase.getDatabase(this);

        //enregistrement dans la base

        btnSave.setOnClickListener(v -> {
            double amount = Double.parseDouble(editAmount.getText().toString());
            String type = spinnerType.getSelectedItem().toString();
            String category = editCategory.getText().toString();
            String date = DateUtil.getCurrentDateTime();
            String note = editNote.getText().toString();
            if (validateFields()) {
                return;
            } else {
                Transaction transaction = new Transaction(amount, type, category, date, note);
                new Thread(() -> {

                    db.transactionDao().insert(transaction);
                    finish();
                }).start();

            }

        });

    }

//fonction pour la vérification des champs
    private boolean validateFields() {
        if (editAmount.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Amount is required", Toast.LENGTH_SHORT).show();
            return true;
        }

        if (spinnerType.getSelectedItem() == null || spinnerType.getSelectedItem().toString().trim().isEmpty()) {
            Toast.makeText(this, "Transaction type is required", Toast.LENGTH_SHORT).show();
            return true;
        }

        if (editCategory.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Category is required", Toast.LENGTH_SHORT).show();
            return true;
        }



        if (editNote.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Note is required", Toast.LENGTH_SHORT).show();
            return true;
        }

        return false; // Tous les champs sont valides
    }









}


