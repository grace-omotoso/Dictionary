package com.example.languagedictionary;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DictionaryDBHelper extends SQLiteOpenHelper {
    public DictionaryDBHelper(Context context) {
        super(context, "translator.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table dictionary (foreign_txt text, english_translation text, comment_txt text, language_txt text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        if (i != i1){
            sqLiteDatabase.execSQL("drop table if exists dictionary");
            onCreate(sqLiteDatabase);
        }
    }
     public Boolean insertUserData(String foreign_text, String english_translation, String comment_text, String language_text){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
         ContentValues contentValues = new ContentValues();
         contentValues.put("foreign_txt", foreign_text);
         contentValues.put("english_translation", english_translation);
         contentValues.put("comment_txt", comment_text);
         contentValues.put("language_txt", language_text);
         long result = sqLiteDatabase.insert("dictionary",null, contentValues);
         if (result == -1){
             return false;
         }else{
             return true;
         }
     }

     public Cursor getUserData(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from dictionary",null);
        return cursor;
     }




}
