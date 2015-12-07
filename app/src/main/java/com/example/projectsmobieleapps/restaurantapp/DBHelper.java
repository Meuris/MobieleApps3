package com.example.projectsmobieleapps.restaurantapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.util.Log;

/**
 * Created by MichielAdmin on 7/12/2015.
 */
public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context, String name, CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE_TABLE_RESTAURANT");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        Log.d("Restaurant", "Upgrading db from version " + oldVersion + "to " + newVersion);
        db.execSQL(RestaurantDB.DROP_RESTAURANT_TABLE);
        onCreate(db);
    }
}
