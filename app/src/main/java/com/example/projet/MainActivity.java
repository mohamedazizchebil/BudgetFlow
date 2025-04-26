package com.example.projet;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


// MainActivity.java
public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private TransactionAdapter adapter;
    private AppDataBase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //initialisation des variables
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        db = AppDataBase.getDatabase(this);

        //affichage de données avec recyclerview
        new Thread(() -> {
            List<Transaction> list = db.transactionDao().getAllTransactions();
            runOnUiThread(() -> {
                adapter = new TransactionAdapter(list);
                recyclerView.setAdapter(adapter);
            });
        }).start();

        //button pour ajouter une transaction
        findViewById(R.id.fab_add).setOnClickListener(v -> {
            startActivity(new Intent(this, AddTransactionActivity.class));
        });
        scheduleTestReminderEachMinute();






    }


    private void scheduleTestReminderEachMinute() {
        Intent intent = new Intent(this, ReminderReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                this, 0, intent, PendingIntent.FLAG_IMMUTABLE);

        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        long triggerTime = System.currentTimeMillis() + 1000; // première notif dans 1 seconde
        long repeatInterval = 60 * 1000; // toutes les 60 secondes

        alarmManager.setRepeating(
                AlarmManager.RTC_WAKEUP,//temps réel
                triggerTime,
                repeatInterval,
                pendingIntent
        );
    }


    //redéfintion de la methode on resume pour la recuperation de données
    @Override
    protected void onResume() {
        super.onResume();
        loadTransactions();
    }
    private void loadTransactions() {
        new Thread(() -> {
            List<Transaction> transactions = db.transactionDao().getAllTransactions();
            runOnUiThread(() -> {
                adapter = new TransactionAdapter(transactions);
                recyclerView.setAdapter(adapter);
            });
        }).start();
    }




}

