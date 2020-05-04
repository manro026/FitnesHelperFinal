package com.example.fitneshelperfinal.treningFolder;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import androidx.annotation.Nullable;
import com.example.fitneshelperfinal.MainActivity;
import com.example.fitneshelperfinal.R;
import com.example.fitneshelperfinal.bd.DBHelper;

import java.util.ArrayList;

public class TreningStartPlane extends Activity {

    MainActivity main;//нужно чтобы обращаться к данным из Shared
    DBHelper dbHelper;//База данных подключаем крч


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);

        dbHelper=new DBHelper(this);//оперделим бд
        main = new MainActivity();//определили его
     /*   for(int i=0;i<5;i++)
        {
            start();
        }*/

    }

    private void start()
    {
        final LinearLayout testLianer = (LinearLayout) findViewById(R.id.testLianer); // обьявили лайнер который находиться у нас в скроле
        final View view = getLayoutInflater().inflate(R.layout.test2, null);//выбираем слой какой мы будем туда пихать
        testLianer.addView(view);
    }




}
