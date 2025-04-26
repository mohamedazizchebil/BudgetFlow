package com.example.projet;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class ReminderReceiver extends BroadcastReceiver {

    //redefinition de la methode onrecive pour l'affichage de la notification chaque minute
    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationUtil.showNotification(context);

    }
}
