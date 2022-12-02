package com.raw.mymoneymanger.Utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.renderscript.Sampler;

import com.raw.mymoneymanger.Model.CategoriesModel;
import com.raw.mymoneymanger.Model.TransactionModel;

import java.util.ArrayList;

public class Database extends SQLiteOpenHelper {

    public Database(Context context) {
        super(context, "Transaction.db", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String que = "CREATE TABLE transactiondb(id INTEGER PRIMARY KEY AUTOINCREMENT,date TEXT,category TEXT,amount INTEGER,extranotes TEXT,type INTEGER)";
        db.execSQL(que);
        String que1 = "CREATE TABLE categoriesdb(id INTEGER PRIMARY KEY AUTOINCREMENT,category TEXT,type INTEGER)";
        db.execSQL(que1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void Insertcategories(String category, int type) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put("category", category);
        value.put("type", type);
        db.insert("categoriesdb", null, value);
    }

    public void InsertData(String date, String category, int amount, String extranotes, int type) {

    }

    public ArrayList<TransactionModel> RetrivData() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursorCourses = db.rawQuery("SELECT * FROM " + "transactiondb", null);
        ArrayList<TransactionModel> transactionModelArrayList = new ArrayList<>();
        if (cursorCourses.moveToFirst()) {
            do {
                String dates = cursorCourses.getString(1);
                String category = cursorCourses.getString(2);
                int amount = cursorCourses.getInt(3);
                String extranotes = cursorCourses.getString(4);
                int type = cursorCourses.getInt(5);
                transactionModelArrayList.add(new TransactionModel(dates, category, amount, extranotes, type));
            } while (cursorCourses.moveToNext());
        }
        return transactionModelArrayList;
    }

    public ArrayList<CategoriesModel> categoriesRetrivData() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursorCourses = db.rawQuery("SELECT * FROM " + "categoriesdb", null);
        ArrayList<CategoriesModel> categoriesModelArrayList = new ArrayList<>();
        if (cursorCourses.moveToFirst()) {
            do {
                String category = cursorCourses.getString(1);
                int type = cursorCourses.getInt(2);
                categoriesModelArrayList.add(new CategoriesModel(category, type));
            } while (cursorCourses.moveToNext());
        }
        return categoriesModelArrayList;
    }
}
