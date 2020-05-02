package com.example.fitneshelperfinal.bd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
   public  static final int DATABASE_VERSION=12;
   public  static final String DATABASE_NAME="app12sad3";

   public  static final String TABLE_TRENING="TreningBD";// дата
   public  static final String TABLE_EXERCISE="ExerciseBD";// бд для хранения упрженений

    public  static final String KEY_ID="_id";//ид
    public  static final String KEY_DATATIME="DataTime";//дата
    public  static final String KEY_NAMETRENING="NameTrening";//Название тренировки
    public  static final String KEY_EXERCISE="NameExercise";//Временно ( нет ) упражнения какие в этот день

    public  static final String KEY_EXERCISEСOUNT="CountExercise";




    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+ TABLE_TRENING+"("+KEY_ID+" integer primary key,"+KEY_NAMETRENING+" text,"+KEY_DATATIME+" text)");//Если таблицы не существует то мы ее создаем
        db.execSQL("create table "+ TABLE_EXERCISE+"("+KEY_ID+" integer primary key,"+KEY_NAMETRENING+" text,"+KEY_DATATIME+" text,"
                                  +KEY_EXERCISE+" text,"+KEY_EXERCISEСOUNT+" text)");//Если таблицы не существует то мы ее создаем
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE_TRENING);
        onCreate(db);//а тут обновляем таблицу
}
}

