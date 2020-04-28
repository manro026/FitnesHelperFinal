package com.example.fitneshelperfinal.bd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
   public  static final int DATABASE_VERSION=1;
   public  static final String DATABASE_NAME="app";

   public  static final String TABLE_TRENING="TreningBD";// дата

    public  static final String KEY_ID="_id";//ид
    public  static final String KEY_DATA="data";//дата
    public  static final String KEY_TIME="time";//Запланированное время тренировки

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+ TABLE_TRENING+"("+KEY_ID+" integer primary key,"+KEY_DATA+" data"+KEY_TIME+"time)");//Если таблицы не существует то мы ее создаем
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE_TRENING);
        onCreate(db);//а тут обновляем таблицу
}
}

