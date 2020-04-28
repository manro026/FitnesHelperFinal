package com.example.fitneshelperfinal;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.fitneshelperfinal.treningFolder.TreningClass;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    public static SharedPreferences info_persone;
    public static SharedPreferences.Editor info_ed;

private Button ButtonTrening,ButtonFood;//обьявляем ее
    @Override
    protected void onCreate(Bundle savedInstanceState) {//событие при старте получается экрана
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        info_persone=getPreferences(MODE_PRIVATE);
        info_ed=info_persone.edit();

        ButtonTrening = (Button) findViewById(R.id.ButtonTrening);//ищем кнопку на экране
        ButtonFood = (Button) findViewById(R.id.ButtonFood);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void ButtonTerning_Click(View v)
    {
        Intent intent = new Intent(v.getContext(), TreningClass.class);
        startActivity(intent);
    }
    public void ButtonFood_Click(View v)
    {

    }
    public void ButtonSetting_Click(View v)
    {

    }
    public void ButtonReset_Click(View v)
    {

    }


}
