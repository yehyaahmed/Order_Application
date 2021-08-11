package com.example.ordersapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DBNAME = "order.db";

    public DBHelper(@Nullable Context context) {
        super(context, DBNAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create Table favorite(id Integer primary key,isFavorite TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop Table if exists favorite");
    }

    public Boolean insertData(int id, String isFavorite) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("id", id);
        contentValues.put("isFavorite", isFavorite);

        long result = sqLiteDatabase.insert("favorite", null, contentValues);
        if (result > -1) {
            return true;
        } else {
            return false;
        }
    }

    public Cursor getAllMeals() {
        SQLiteDatabase sd = this.getReadableDatabase();
        Cursor cursor = sd.rawQuery("select * from favorite", null);

        return cursor;

    }

    public void updateMeal(int id, String isFavorite) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("isFavorite", isFavorite);
        sqLiteDatabase.update("favorite", cv, "id =?", new String[]{id + ""});

    }

    public Boolean checkMeal(int id) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("Select * from favorite where id = ?", new String[]{id + ""});
        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public void removeMeal(int id) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete("favorite", "id =?", new String[]{id + ""});

    }
}
