package com.example.food_reach;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {

    public Database(@Nullable Context context) {
        super(context, "FoodReach.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE IF NOT EXISTS users ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "username TEXT UNIQUE, "
                + "email TEXT, "
                + "contact TEXT, "
                + "district TEXT, "
                + "password TEXT)";
        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS users");
        onCreate(db);
    }

    // Method to register a new user (with username, email, contact, district, and password)
    public void register(String username, String email, String contact, String district, String password) {
        ContentValues cv = new ContentValues();
        cv.put("username", username);
        cv.put("email", email);
        cv.put("contact", contact);
        cv.put("district", district);
        cv.put("password", password);

        SQLiteDatabase db = getWritableDatabase();
        db.insert("users", null, cv);
        db.close();
    }

    // Method to validate login (returns 1 if credentials are valid, else 0)
    public int login(String username, String password) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM users WHERE username=? AND password=?", new String[]{username, password});

        if (cursor != null && cursor.moveToFirst()) {
            // Login successful if a record is found
            cursor.close();
            db.close();
            return 1;  // Successful login
        }

        cursor.close();
        db.close();
        return 0;  // Failed login
    }

    // Method to fetch user profile
    public String[] getUserProfile(String username) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM users WHERE username=?", new String[]{username});

        if (cursor != null && cursor.moveToFirst()) {
            String[] userData = new String[5];
            userData[0] = cursor.getString(cursor.getColumnIndex("username"));
            userData[1] = cursor.getString(cursor.getColumnIndex("email"));
            userData[2] = cursor.getString(cursor.getColumnIndex("contact"));
            userData[3] = cursor.getString(cursor.getColumnIndex("district"));
            userData[4] = cursor.getString(cursor.getColumnIndex("password"));

            cursor.close();
            db.close();
            return userData;
        }

        cursor.close();
        db.close();
        return null;
    }

    // Method to update user profile
    public boolean updateUserProfile(String username, String email, String contact, String district, String password) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email", email);
        contentValues.put("contact", contact);
        contentValues.put("district", district);
        contentValues.put("password", password);

        int rowsAffected = db.update("users", contentValues, "username=?", new String[]{username});
        db.close();

        return rowsAffected > 0;
    }

    // Method to delete user profile
    public boolean deleteUserProfile(String username) {
        SQLiteDatabase db = getWritableDatabase();
        int rowsDeleted = db.delete("users", "username=?", new String[]{username});
        db.close();

        return rowsDeleted > 0;
    }
}
