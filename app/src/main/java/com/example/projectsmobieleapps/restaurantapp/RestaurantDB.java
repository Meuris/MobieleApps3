package com.example.projectsmobieleapps.restaurantapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by MichielAdmin on 6/12/2015.
 */
public class RestaurantDB {

    private SQLiteDatabase db;
    private DBHelper dbHelper;

    public RestaurantDB(Context context) {
        dbHelper = new DBHelper(context, DB_NAME, null, DB_VERSION);
    }

    //database constants
    public static final String DB_NAME = "restaurant.db";
    public static final int DB_VERSION = 1;

    //restaurants table constants
    public static final String RESTAURANT_TABLE = "restaurant";

    public static final String RESTAURANT_ID = "_id";
    public static final int RESTAURANT_ID_COL = 0;

    public static final String RESTAURANT_NAME = "restaurant_name";
    public static final int RESTAURANT_NAME_COL = 1;

    public static final String RESTAURANT_VICINITY = "restaurant_vicinity";
    public static final int RESTAURANT_VICINITY_COL = 2;

    public static final String RESTAURANT_LATITUDE = "restaurant_latitude";
    public static final int RESTAURANT_LATITUDE_COL = 3;

    public static final String RESTAURANT_LONGITUDE = "restaurant_longitude";
    public static final int RESTAURANT_LONGITUDE_COL = 4;

    //CREATE and DROP TABLE statements
    public static final String CREATE_RESTAURANT_TABLE =
            "CREATE TABLE " + RESTAURANT_TABLE + " (" + RESTAURANT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    RESTAURANT_NAME + " TEXT NOT NULL UNIQUE, " +
                    RESTAURANT_VICINITY + " TEXT, " +
                    RESTAURANT_LATITUDE + " TEXT, " +
                    RESTAURANT_LONGITUDE + " TEXT,);";

    public static final String DROP_RESTAURANT_TABLE = "DROP TABLE IF EXISTS " + RESTAURANT_TABLE;

    private void openReadableDB() {
        db = dbHelper.getReadableDatabase();
    }

    private void openWriteableDb() {
        db = dbHelper.getWritableDatabase();
    }

    private void closeDB() {
        if (db != null) {
            db.close();
        }
    }
}
