package com.example.winecellarsensor.database;

import android.content.Context;
import android.os.AsyncTask;


import com.example.winecellarsensor.model.Warning;

import java.util.Date;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Warning.class}, version = 1,  exportSchema = false)
public abstract class WarningDatabase extends RoomDatabase {

    private static WarningDatabase instance;

    public abstract WarningDao warningDao();

    public static synchronized  WarningDatabase getInstance(Context context){

        if(instance == null)
        {
            instance = Room.databaseBuilder(context.getApplicationContext(),
            WarningDatabase.class, "warning_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback()
    {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void>
    {
        private PopulateDbAsyncTask(WarningDatabase db)
        {
            warningDao = db.warningDao();
        }
        private WarningDao warningDao;
        @Override
        protected Void doInBackground(Void... voids) {
            return null;

        }
    }
}
