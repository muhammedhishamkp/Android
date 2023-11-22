package com.example.sjcet.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "users.db";
    private static final String TABLE_NAME = "users_table";
    private static final String COL1 = "ID";
    private static final String COL2 = "NAME";
    private static final String COL3 = "AGE";
    private static final String COL4 = "MARK";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (" +
                COL1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL2 + " TEXT, " +
                COL3 + " INTEGER, " +
                COL4 + " INTEGER)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertUser(String name, int age, int mark) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, name);
        contentValues.put(COL3, age);
        contentValues.put(COL4, mark);

        long result = db.insert(TABLE_NAME, null, contentValues);
        return result != -1; // Insertion successful if result != -1, else return false
    }

    public Cursor getAllUsers() {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
    }

    public boolean deleteUser(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete(TABLE_NAME, COL1 + "=?", new String[]{String.valueOf(id)});
        return result > 0;
    }

    public boolean updateUser(String name, int age, int mark) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL3, age);
        contentValues.put(COL4, mark);

        // Update all records (you might need to include a WHERE clause for specific updates)
        int updatedRows = db.update(TABLE_NAME, contentValues, COL2 + "=?", new String[]{name});
        return updatedRows > 0;
    }

    // Method to retrieve user details by ID (use in MainActivity's displayDetailsForUpdate)
    public Cursor getUserById(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + COL1 + "=?", new String[]{String.valueOf(id)});
    }
}