package com.example.fitneshelperfinal.treningFolder;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import com.example.fitneshelperfinal.MainActivity;
import com.example.fitneshelperfinal.R;
import com.example.fitneshelperfinal.SliderPageAdapter;
import com.example.fitneshelperfinal.bd.DBHelper;

import java.util.ArrayList;
import java.util.List;

public class TreningStartPlane extends Activity {

    MainActivity main;//нужно чтобы обращаться к данным из Shared
    DBHelper dbHelper;//База данных подключаем крч


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);

            LayoutInflater inflater = LayoutInflater.from(this);
            List<View> pages = new ArrayList<View>();

            View page = inflater.inflate(R.layout.test2, null);

            pages.add(page);

            page = inflater.inflate(R.layout.test2, null);

            pages.add(page);

            page = inflater.inflate(R.layout.test2, null);

            pages.add(page);

            SliderPageAdapter pagerAdapter = new SliderPageAdapter(pages);
            ViewPager viewPager = new ViewPager(this);
            viewPager.setAdapter(pagerAdapter);
            viewPager.setCurrentItem(1);

            setContentView(viewPager);
        }
 }

