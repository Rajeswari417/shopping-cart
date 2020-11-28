package com.example.shoppingcart.presistance;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.shoppingcart.utils.Recipe;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Recipe.class},version = 1,exportSchema = false)
public abstract class ShoppingCartRoomDB extends RoomDatabase {
    public static ShoppingCartRoomDB INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);
    public abstract ShoppingCartDao shoppingCartDao();

    public static ShoppingCartRoomDB getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (ShoppingCartRoomDB.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            ShoppingCartRoomDB.class, "shopping_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }

}