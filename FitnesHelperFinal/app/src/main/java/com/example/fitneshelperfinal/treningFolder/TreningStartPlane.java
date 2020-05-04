package com.example.fitneshelperfinal.treningFolder;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import com.example.fitneshelperfinal.MainActivity;
import com.example.fitneshelperfinal.R;
import com.example.fitneshelperfinal.customScrol.SliderPageAdapter;
import com.example.fitneshelperfinal.bd.DBHelper;
import com.example.fitneshelperfinal.customScrol.zoomAnimation;

import java.util.ArrayList;
import java.util.List;

public class TreningStartPlane extends Activity {

    MainActivity main;//нужно чтобы обращаться к данным из Shared
    DBHelper dbHelper;//База данных подключаем крч


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);
        dbHelper=new DBHelper(this);
            LayoutInflater inflater = LayoutInflater.from(this);
            List<View> pages = new ArrayList<View>();
            checkBD();

            View page = inflater.inflate(R.layout.test2, null);

            pages.add(page);

            page = inflater.inflate(R.layout.test2, null);

            pages.add(page);

            page = inflater.inflate(R.layout.test2, null);

            pages.add(page);

            SliderPageAdapter pagerAdapter = new SliderPageAdapter(pages);
            ViewPager viewPager = new ViewPager(this);
            viewPager.setPageTransformer(true,new zoomAnimation());
            viewPager.setAdapter(pagerAdapter);
            viewPager.setCurrentItem(1);

            setContentView(viewPager);
        }

private void checkBD(){
     String test = "13/5/2020 18:2";

    SQLiteDatabase database = dbHelper.getWritableDatabase();//нужен для управления

    Cursor cursor = database.query(DBHelper.TABLE_TRENING, null, null, null,
            null, null, null);// ТУТ ПРОИСХОДИТ СОРТИРОВКА В ДАЛЬНЕЙШЕМ ПРИГОДИТСЯ ЗАПОМНИ
    if (cursor.moveToFirst()) {
        int idindex = cursor.getColumnIndex(DBHelper.KEY_ID);
        int nameTrening = cursor.getColumnIndex(DBHelper.KEY_NAMETRENING);
        int dataTime = cursor.getColumnIndex(DBHelper.KEY_DATATIME);


        do {
            if(test.equals(cursor.getString(dataTime)))
            {
                Log.d("mLOg ", "ID=" + cursor.getInt(idindex) +
                        " Имя= " + cursor.getString(nameTrening)+
                        " Дата и время= " + cursor.getString(dataTime));
                break;
            }

        } while (cursor.moveToNext());
    }


}
}

