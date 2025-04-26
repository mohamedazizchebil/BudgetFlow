package com.example.projet;

import android.app.PendingIntent;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;

import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class NotificationUtil {

    public static void showNotification(Context context) {
        // Intent qui ouvrira l’app au clic sur la notif
        Intent intent = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(
                context, 0, intent, PendingIntent.FLAG_IMMUTABLE);

        // Création du canal si nécessaire
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String channelId = "my_channel";
            CharSequence name = "Canal de Rappel";
            String description = "Canal utilisé pour les rappels quotidiens";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(channelId, name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
            if (notificationManager != null) {
                notificationManager.createNotificationChannel(channel);
            }
        }

        // Construction de la notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "my_channel")
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setContentTitle("Rappel de dépenses")
                .setContentText("N'oubliez pas d'ajouter vos dépenses aujourd'hui !")
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        // Affichage de la notification
        NotificationManagerCompat manager = NotificationManagerCompat.from(context);

        manager.notify(100, builder.build());
    }
}
