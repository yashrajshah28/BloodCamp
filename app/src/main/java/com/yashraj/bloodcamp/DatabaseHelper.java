package com.yashraj.bloodcamp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "bloodcamp_db";
    private static final int DATABASE_VERSION = 1;

    private static final String CREATE_TABLE_USERS = "CREATE TABLE users (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "full_name TEXT," +
            "email TEXT," +
            "username TEXT," +
            "password TEXT)";

    private static final String CREATE_TABLE_DONOR = "CREATE TABLE donor (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "DName TEXT," +
            "DEmail TEXT," +
            "DPhone TEXT," +
            "DAddress TEXT," +
            "DCity TEXT," +
            "DPincode INTEGER," +
            "DBloodGroup TEXT," +
            "DGender TEXT)";
//            "SBloodGroup TEXT," + // Added spinner column
//            "RGGender TEXT)"; // Added radio group column

    private static final String CREATE_TABLE_CAMPDETAILS = "CREATE TABLE campdetails (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "CampName TEXT," +
            "CampAddress TEXT," +
            "PhoneNumber TEXT)";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USERS);
        db.execSQL(CREATE_TABLE_CAMPDETAILS);
        db.execSQL(CREATE_TABLE_DONOR);
    }

    public Cursor read_data() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM campdetails", null);
        return cursor;
    }

    public Cursor read_data_donor() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM donor", null);
        return cursor;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS users");
        db.execSQL("DROP TABLE IF EXISTS donor");
        db.execSQL("DROP TABLE IF EXISTS campdetails");
        onCreate(db);
    }
}












//package com.yashraj.bloodcamp;
//import android.annotation.SuppressLint;
//import android.content.Context;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteOpenHelper;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class DatabaseHelper extends SQLiteOpenHelper {
//
//    private static final String DATABASE_NAME = "bloodcamp_db";
//
//    private static final int DATABASE_VERSION = 1;
//
//    private static final String CREATE_TABLE_USERS = "CREATE TABLE users (" +
//            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
//                "full_name TEXT," +
//                "email TEXT," +
//                "username TEXT," +
//                "password TEXT)";
//
//    private static final String CREATE_TABLE_DONOR = "CREATE TABLE donor (" +
////            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
////            "name TEXT," +
////            "email TEXT," +
////            "phoneNumber TEXT,"+
////            "address TEXT,"+
////            "city TEXT,"+
////            "pincode NUMBER,"+
////            "bloodgroup STRING,"+
////            "gender STRING)";
//    // ---------------------------
////            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
////            "DName TEXT," +
////            "DEmail TEXT," +
////            "DPhone TEXT,"+
////            "DAddress TEXT,"+
////            "DCity TEXT,"+
////            "DPincode INTEGER," +
////            "SBloodGroup STRING," +
////            "RGgender TEXT)";
//    //---------------------------
//            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
//            "DName TEXT," +
//            "DEmail TEXT," +
//            "DPhone TEXT,"+
//            "DAddress TEXT,"+
//            "DCity TEXT,"+
//            "DPincode INTEGER)";
//
//    private static final String CREATE_TABLE_CAMPDETAILS = "CREATE TABLE campdetails (" +
//            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
//            "CampName TEXT," +
//            "CampAddress TEXT," +
//            "PhoneNumber TEXT)";
//
//
//    public DatabaseHelper(Context context) {
//        super(context, DATABASE_NAME, null, DATABASE_VERSION);
//    }
//
//    @Override
//    public void onCreate(SQLiteDatabase db) {
////        db.execSQL("CREATE TABLE IF NOT EXISTS users (" +
////                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
////                "full_name TEXT," +
////                "email TEXT," +
////                "username TEXT," +
////                "password TEXT," +
////                "phone TEXT," +
////                "blood TEXT)"
////        );
////        db.execSQL("CREATE TABLE blood_camps (" +
////                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
////                "name TEXT," +
////                "address TEXT," +
////                "phoneNumber TEXT)");
//
//        db.execSQL(CREATE_TABLE_USERS);
//        db.execSQL(CREATE_TABLE_CAMPDETAILS);
//        db.execSQL(CREATE_TABLE_DONOR);
//    }
//
//
//    public Cursor read_data(){
//        SQLiteDatabase db = getReadableDatabase();
//        Cursor cursor = db.rawQuery("select * from campdetails", null);
//        return cursor;
//    }
//
//    public Cursor read_data_donor(){
//        SQLiteDatabase db = getReadableDatabase();
//        Cursor cursor = db.rawQuery("select * from donor", null);
//        return cursor;
//    }
//    @Override
//    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        db.execSQL("DROP TABLE IF EXISTS users");
//        db.execSQL("DROP TABLE IF EXISTS donor");
//        db.execSQL("DROP TABLE IF EXISTS campdetails");
//        onCreate(db);
//    }
//
////    public Cursor getAllData() {
////
////        SQLiteDatabase db = this.getWritableDatabase();
////
////        Cursor cursor = db.rawQuery("SELECT * FROM campdetails", null);
////
////        return cursor;
////
////    }
//
//
//}
//
