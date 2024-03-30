package com.example.utib;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class UserManager {
    private final DatabaseHelper dbHelper;

    public UserManager(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public void addUser(String userName, String email, String password) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            ContentValues values = new ContentValues();
            values.put(DatabaseHelper.COLUMN_USER_NAME, userName);
            values.put(DatabaseHelper.COLUMN_USER_EMAIL, email);
            values.put(DatabaseHelper.COLUMN_USER_PASSWORD, password);

            db.insert(DatabaseHelper.TABLE_USER, null, values);
        } finally {
            db.close();
        }
    }

    public boolean checkUser(String email, String password) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = null;
        try {
            String[] selectionArgs = {email, password};
            cursor = db.query(DatabaseHelper.TABLE_USER,
                    null,
                    DatabaseHelper.COLUMN_USER_EMAIL + " = ? AND " + DatabaseHelper.COLUMN_USER_PASSWORD + " = ?",
                    selectionArgs,
                    null,
                    null,
                    null);

            return cursor.getCount() > 0;
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            db.close();
        }
    }
}