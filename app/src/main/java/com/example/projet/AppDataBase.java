package com.example.projet;
import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Transaction.class}, version = 1)
public abstract class AppDataBase extends RoomDatabase {

    // Abstract method to get the DAO (Data Access Object)
    public abstract TransactionDao transactionDao();

    // Singleton instance of the database
    private static volatile AppDataBase INSTANCE;

    // Method to get the database instance
    public static AppDataBase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDataBase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDataBase.class, "my_database").build();
                }
            }
        }
        return INSTANCE;
    }
}


