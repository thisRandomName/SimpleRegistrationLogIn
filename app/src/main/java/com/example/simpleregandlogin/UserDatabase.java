package com.example.simpleregandlogin;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
@Database(entities={UserEntity.class},version=1)
public abstract class UserDatabase extends RoomDatabase {

    //prin:
    // public abstract UserDao getUserDao();

    //meta:
    private static final String dbName="furious_ladle_db";
    private static UserDatabase userDatabase;

    public static synchronized UserDatabase getUserDatabase(Context context){
        if(userDatabase==null){
            userDatabase= Room.databaseBuilder(context,UserDatabase.class,dbName).fallbackToDestructiveMigration().build();

        }
        return userDatabase;
    }

    public abstract UserDao userDao();
}
