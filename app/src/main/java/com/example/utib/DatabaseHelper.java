package com.example.utib;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String TABLE_USER = "user";
    public static final String COLUMN_USER_ID = "user_id";
    public static final String COLUMN_USER_NAME = "user_name";
    public static final String COLUMN_USER_EMAIL = "user_email";
    public static final String COLUMN_USER_PASSWORD = "user_password";

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "UserManager.db";

    private static final String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER + "(" +
            COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            COLUMN_USER_NAME + " TEXT," +
            COLUMN_USER_EMAIL + " TEXT," +
            COLUMN_USER_PASSWORD + " TEXT" +
            ")";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        onCreate(db);
    }
}