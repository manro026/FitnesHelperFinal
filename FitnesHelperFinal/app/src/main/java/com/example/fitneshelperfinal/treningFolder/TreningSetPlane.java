package com.example.fitneshelperfinal.treningFolder;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.fitneshelperfinal.MainActivity;
import com.example.fitneshelperfinal.R;
import com.example.fitneshelperfinal.bd.DBHelper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class TreningSetPlane extends Activity {

    MainActivity main;//нужно чтобы обращаться к данным из Shared
    DBHelper dbHelper;//База данных подключаем крч

    private TextView testText, clockText;

    private int mYear, mMonth, mDay, mHour, mMinute, mSet=1;

    private String selectedDate;
    private String selectedTime;

    private List<View> allEds;//лист для слоев который мы создаем в лайнере

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");//модификатор чтобы по нему делать нужную форму даты

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trening_set_plane);

        dbHelper=new DBHelper(this);//оперделим бд
        main = new MainActivity();//определили его
        allEds = new ArrayList<View>();
    }


public void addTableExercise_Click(View v)
{
    final LinearLayout lianer = (LinearLayout) findViewById(R.id.lianer); // обьявили лайнер который находиться у нас в скроле
    final View viewExercise = getLayoutInflater().inflate(R.layout.exercise, null);//выбираем слой какой мы будем туда пихать
    TextView text = viewExercise.findViewById(R.id.countExercise); text.setText(Integer.toString(mSet).toString());mSet++;
    lianer.addView(viewExercise);
    allEds.add(viewExercise);
}

public void DataTimeButton_Click(View v)//суда необходимо добавить проверку на то что если пользователь не выбрал ни дату ни время!
{
    AlertDialog.Builder builder= new AlertDialog.Builder(TreningSetPlane.this);//создаем и обьявляем билдер
    final View view = getLayoutInflater().inflate(R.layout.clock, null);//нужен для того чтобы искать элементы на диалоговом окне. ТК через билдер не получается
    builder.setView(view);//загружаем слой который будет отображаться
    final AlertDialog dlg = builder.create();//нужно для уничтожение обьекта

    dlg.show();//показ

    TimePicker timePicker = (TimePicker) view.findViewById(R.id.timePicker);//cлушатель для времени
    timePicker.setIs24HourView(true);

    timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
        @Override
        public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
            mHour=hourOfDay;  mMinute=minute;
        }    });

    final CalendarView calendarView = (CalendarView) view.findViewById(R.id.calendarView);//слушатель для календаря
    calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

        @Override
        public void onSelectedDayChange(CalendarView view, int year,
                                        int month, int dayOfMonth) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(year, month, dayOfMonth);
            selectedDate=sdf.format(calendar.getTime());
        }
    });

    Button button=(Button) view.findViewById(R.id.button9);
    button.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            selectedTime = new StringBuilder().append(mHour).append(":").append(mMinute).toString();

            dlg.dismiss();//дестрой окна
            Button bt=findViewById(R.id.buttonDateTime);
            bt.setText(selectedDate);
        }
    });
}

public void checkBD_Click(View v)//тут проходит проверка бд выводи в логи крч
{
    SQLiteDatabase database = dbHelper.getWritableDatabase();//нужен для управления

    Cursor cursor = database.query(DBHelper.TABLE_TRENING, null, null, null,
            null, null, null);// ТУТ ПРОИСХОДИТ СОРТИРОВКА В ДАЛЬНЕЙШЕМ ПРИГОДИТСЯ ЗАПОМНИ
    if (cursor.moveToFirst()) {
        int idindex = cursor.getColumnIndex(DBHelper.KEY_ID);
        int nameTrening = cursor.getColumnIndex(DBHelper.KEY_NAMETRENING);
        int dataTime = cursor.getColumnIndex(DBHelper.KEY_DATA);
        int Time = cursor.getColumnIndex(DBHelper.KEY_TIME);
        int count = cursor.getColumnIndex(DBHelper.KEY_EXERCISEСOUNT);

        do {
            Log.d("mLOg ", "ID=" + cursor.getInt(idindex) +
                    " Имя= " + cursor.getString(nameTrening)+
                    " время= " + cursor.getString(Time)+
                    " кол-во упражнений= " + cursor.getInt(count)+
                    " Дата= " + cursor.getString(dataTime));
        } while (cursor.moveToNext());
    }

    Cursor cursorTWO = database.query(DBHelper.TABLE_EXERCISE, null, null, null,
            null, null, null);// ТУТ ПРОИСХОДИТ СОРТИРОВКА В ДАЛЬНЕЙШЕМ ПРИГОДИТСЯ ЗАПОМНИ
    if (cursorTWO.moveToFirst()) {
        int idindex = cursorTWO.getColumnIndex(DBHelper.KEY_ID);
        int nameTrening = cursorTWO.getColumnIndex(DBHelper.KEY_NAMETRENING);
        int dataTime = cursorTWO.getColumnIndex(DBHelper.KEY_DATA);
        int exercise = cursorTWO.getColumnIndex(DBHelper.KEY_EXERCISE);
        int exerciseCount = cursorTWO.getColumnIndex(DBHelper.KEY_EXERCISEСOUNT);

        do {
            Log.d("mLOg ", "ID=" + cursorTWO.getInt(idindex) +
                    " Имя= " + cursorTWO.getString(nameTrening)+
                    " дата= " + cursorTWO.getString(dataTime)+
                    " упражнения= " + cursorTWO.getString(exercise)+
                    " подходы= " + cursorTWO.getString(exerciseCount));
        } while (cursorTWO.moveToNext());
    }
}

public void ButtonSavePlane_Click(View v)
{
    SQLiteDatabase database = dbHelper.getWritableDatabase();//нужен для управления
    ContentValues contentValues = new ContentValues();//нужен для добавления новых строк в таблицу выглядит как массив с именами столбцов и тд

    contentValues.put(DBHelper.KEY_NAMETRENING, ((EditText) findViewById(R.id.NameTreningEditText)).getText().toString());
    contentValues.put(DBHelper.KEY_DATA, selectedDate);
    contentValues.put(DBHelper.KEY_TIME, selectedTime);
    contentValues.put(DBHelper.KEY_COUNTEXERCISE, allEds.size());


    database.insert(DBHelper.TABLE_TRENING, null, contentValues);//записываем в какую таблицу.
    contentValues.clear();

    for (int i = 0; i < allEds.size(); i++) {//сохраняем все позициии
        contentValues.put(DBHelper.KEY_NAMETRENING, ((EditText) findViewById(R.id.NameTreningEditText)).getText().toString());
        contentValues.put(DBHelper.KEY_DATA, selectedDate);
        contentValues.put(DBHelper.KEY_EXERCISE, ((EditText) allEds.get(i).findViewById(R.id.exercise)).getText().toString());
        contentValues.put(DBHelper.KEY_EXERCISEСOUNT, ((EditText) allEds.get(i).findViewById(R.id.exerciseCount)).getText().toString());

        database.insert(DBHelper.TABLE_EXERCISE, null, contentValues);//записываем в какую таблицу.
        contentValues.clear();
    }

    Toast.makeText(getApplicationContext(), "Данные записаны", Toast.LENGTH_LONG).show();//таск это всплывающие окно, выводим в него наш выбор, заданным форматом

    dbHelper.close();//отключаемся от бд
    Intent intent = new Intent(v.getContext(), TreningClass.class);
    startActivity(intent);
}



}



