package com.example.adminapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private Context context;
    private static final String DATABASE_NAME = "yogamobile.db";
    private static final int DATABASE_VERSION = 3;

    private static final String TABLE_YOGA_MOBILE = "yoga_mobile";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NAMECOURSE = "name";
    private static final String COLUMN_DAY_OF_WEEK = "dayOfWeek";
    private static final String COLUMN_TIME = "time";
    private static final String COLUMN_CAPACITY = "capacity";
    private static final String COLUMN_DURATION = "duration";
    private static final String COLUMN_PRICE = "price";
    private static final String COLUMN_TYPE = "type";
    private static final String COLUMN_DESCRIPTION = "description";

    private static final String CREATE_TABLE_YOGA_MOBILE = "CREATE TABLE " + TABLE_YOGA_MOBILE + " (" +
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_NAMECOURSE + " TEXT, " +
            COLUMN_DAY_OF_WEEK + " TEXT, " +
            COLUMN_TIME + " TEXT, " +
            COLUMN_CAPACITY + " INTEGER, " +
            COLUMN_DURATION + " INTEGER, " +
            COLUMN_PRICE + " REAL, " +
            COLUMN_TYPE + " TEXT, " +
            COLUMN_DESCRIPTION + " TEXT)";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_YOGA_MOBILE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_YOGA_MOBILE);
        onCreate(db);
    }

    // Phương thức thêm khóa học yoga
    public boolean addCourse(String name, String dayOfWeek, String time, int capacity, int duration, double price, String type, String description) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAMECOURSE, name);
        cv.put(COLUMN_DAY_OF_WEEK, dayOfWeek);
        cv.put(COLUMN_TIME, time);
        cv.put(COLUMN_CAPACITY, capacity);
        cv.put(COLUMN_DURATION, duration);
        cv.put(COLUMN_PRICE, price);
        cv.put(COLUMN_TYPE, type);
        cv.put(COLUMN_DESCRIPTION, description);

        try {
            long result = db.insert(TABLE_YOGA_MOBILE, null, cv);
            if (result == -1) {
                Log.e("DatabaseHelper", "Failed to insert row into " + TABLE_YOGA_MOBILE);
                return false;
            } else {
                Log.d("DatabaseHelper", "Course added successfully with ID: " + result);
                return true;
            }
        } catch (Exception e) {
            Log.e("DatabaseHelper", "Error while inserting data: " + e.getMessage());
            return false;
        }
    }


    public boolean updateCourse(String id,String name ,String dayOfWeek, String time, int capacity, int duration, double price, String type, String description) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAMECOURSE, name);
        cv.put(COLUMN_DAY_OF_WEEK, dayOfWeek);
        cv.put(COLUMN_TIME, time);
        cv.put(COLUMN_CAPACITY, capacity);
        cv.put(COLUMN_DURATION, duration);
        cv.put(COLUMN_PRICE, price);
        cv.put(COLUMN_TYPE, type);
        cv.put(COLUMN_DESCRIPTION, description);

        long result = db.update(TABLE_YOGA_MOBILE, cv, "_id=?", new String[]{id});
        return result != -1;
    }

    public void deleteAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_YOGA_MOBILE);
    }
    public boolean deleteOneCourse(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_YOGA_MOBILE, "_id=?", new String[]{id});
        return result != -1;
    }

    // Phương thức đọc tất cả dữ liệu
    public Cursor readAllData() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_YOGA_MOBILE, null);
    }


}
