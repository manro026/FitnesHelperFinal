package com.example.fitneshelperfinal.treningFolder;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import com.example.fitneshelperfinal.MainActivity;
import com.example.fitneshelperfinal.R;
import com.example.fitneshelperfinal.customScrol.SliderPageAdapter;
import com.example.fitneshelperfinal.bd.DBHelper;
import com.example.fitneshelperfinal.customScrol.zoomAnimation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TreningStartPlane extends Activity {

    MainActivity main;//нужно чтобы обращаться к данным из Shared
    DBHelper dbHelper;//База данных подключаем крч

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");//модификатор чтобы по нему делать нужную форму даты
    Date d = new Date();//актуальная дата и время

    private List<View> allEds;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);
        dbHelper = new DBHelper(this);
        allEds = new ArrayList<View>();
        LayoutInflater inflater = LayoutInflater.from(this);
        List<View> pages = new ArrayList<View>();
        checkBD();

        View page = inflater.inflate(R.layout.test2, null);
        test(page);
        pages.add(page);

        page = inflater.inflate(R.layout.test2, null);

        pages.add(page);

        page = inflater.inflate(R.layout.test2, null);

        pages.add(page);

        SliderPageAdapter pagerAdapter = new SliderPageAdapter(pages);
        ViewPager viewPager = new ViewPager(this);
        viewPager.setPageTransformer(true, new zoomAnimation());
        viewPager.setAdapter(pagerAdapter);
        viewPager.setCurrentItem(1);

        setContentView(viewPager);
    }

    private  void test(View page)
    {

        String test = sdf.format(d);
        SQLiteDatabase database = dbHelper.getWritableDatabase();//нужен для управления

        Cursor cursor = database.query(DBHelper.TABLE_TRENING, null, null, null,
                null, null, null);// ТУТ ПРОИСХОДИТ СОРТИРОВКА В ДАЛЬНЕЙШЕМ ПРИГОДИТСЯ ЗАПОМНИ
        if (cursor.moveToFirst()) {
            int dataTime = cursor.getColumnIndex(DBHelper.KEY_DATA);
            do {
                if (test.equals(cursor.getString(dataTime))) {
                   for(int i=0;i<cursor.getInt(cursor.getColumnIndex(DBHelper.KEY_COUNTEXERCISE));i++) {
                       LinearLayout test_lianer = (LinearLayout) page.findViewById(R.id.linearLayout);
                       final View viewExercise = getLayoutInflater().inflate(R.layout.exercise, null);//выбираем слой какой мы будем туда пихать
                       test_lianer.addView(viewExercise);
                       allEds.add(viewExercise);
                   }
                    break;
                }

            } while (cursor.moveToNext());
        }
    }


    private void checkBD() {

        String test = sdf.format(d);
        SQLiteDatabase database = dbHelper.getWritableDatabase();//нужен для управления

        Cursor cursor = database.query(DBHelper.TABLE_TRENING, null, null, null,
                null, null, null);// ТУТ ПРОИСХОДИТ СОРТИРОВКА В ДАЛЬНЕЙШЕМ ПРИГОДИТСЯ ЗАПОМНИ

        if (cursor.moveToFirst()) {
            int idindex = cursor.getColumnIndex(DBHelper.KEY_ID);
            int nameTrening = cursor.getColumnIndex(DBHelper.KEY_NAMETRENING);
            int dataTime = cursor.getColumnIndex(DBHelper.KEY_DATA);
                do {
                    if (test.equals(cursor.getString(dataTime))) {
                        Log.d("mLOg ", "ID=" + cursor.getInt(idindex) +
                                " Имя= " + cursor.getString(nameTrening) +
                                " Дата и время= " + cursor.getString(dataTime));
                        Toast.makeText(getApplicationContext(), "Данные найдены", Toast.LENGTH_LONG).show();//таск это всплывающие окно, выводим в него наш выбор, заданным форматом

                        break;
                    }

                } while (cursor.moveToNext());
            }
        }
    }


