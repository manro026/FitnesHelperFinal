package com.example.fitneshelperfinal.treningFolder;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.fitneshelperfinal.MainActivity;
import com.example.fitneshelperfinal.R;
import com.example.fitneshelperfinal.bd.DBHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class TreningSetPlane extends Activity {

    MainActivity main;//нужно чтобы обращаться к данным из Shared
    DBHelper dbHelper;//База данных подключаем крч

    private TextView testText, clockText;
    private Button testBut;

    private int mYear, mMonth, mDay, mHour, mMinute;

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");//модификатор чтобы по нему делать нужную форму даты
    Date d = new Date();//актуальная дата и время

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trening_set_plane);

        dbHelper=new DBHelper(this);//оперделим бд
        main = new MainActivity();//определили его

        testBut=findViewById(R.id.button2);
        testText=findViewById(R.id.textView);

    }

public void testBut_Click(View v)
{
    AlertDialog.Builder builder= new AlertDialog.Builder(TreningSetPlane.this);//создаем и обьявляем билдер
    final View view = getLayoutInflater().inflate(R.layout.test, null);//нужен для того чтобы искать элементы на диалоговом окне. ТК через билдер не получается
    builder.setView(view);//загружаем слой который будет отображаться
    final AlertDialog dlg = builder.create();//нужно для уничтожение обьекта
    dlg.show();//показ


    CalendarView calendarView = (CalendarView) view.findViewById(R.id.calendarView);//слушатель для календаря
    calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

        @Override
        public void onSelectedDayChange(CalendarView view, int year,
                                        int month, int dayOfMonth) {
            mYear = year;
            mMonth = month;
            mDay = dayOfMonth;
        }
    });

    Button btSet = (Button) view.findViewById(R.id.btSet);
    btSet.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            /*ain.info_ed.putInt("test",mDay);
            main.info_ed.commit();
*/
            SQLiteDatabase database = dbHelper.getWritableDatabase();//нужен для управления
            ContentValues contentValues = new ContentValues();//нужен для добавления новых строк в таблицу выглядит как массив с именами столбцов и тд

            String selectedDate = new StringBuilder().append(mMonth + 1)
                    .append("-").append(mDay).append("-").append(mYear)
                    .append(" ").toString();//создаем стринг со всеми значениями даты
            selectedDate = sdf.format(d); //преобразуем его под нужные нам значения даты

            contentValues.put(DBHelper.KEY_DATA, selectedDate);
            database.insert(DBHelper.TABLE_TRENING, null, contentValues);//записываем в какую таблицу.
            Toast.makeText(getApplicationContext(), selectedDate+"Данные записаны", Toast.LENGTH_LONG).show();//таск это всплывающие окно, выводим в него наш выбор, заданным форматом

            dbHelper.close();//отключаемся от бд
            dlg.dismiss();//дестрой окна
        }
    });


}

public void TimeButton_Click(View v)
{
    AlertDialog.Builder builder= new AlertDialog.Builder(TreningSetPlane.this);//создаем и обьявляем билдер
    final View view = getLayoutInflater().inflate(R.layout.clock, null);//нужен для того чтобы искать элементы на диалоговом окне. ТК через билдер не получается
    builder.setView(view);//загружаем слой который будет отображаться
    final AlertDialog dlg = builder.create();//нужно для уничтожение обьекта

    dlg.show();//показ

    TimePicker timePicker = (TimePicker) view.findViewById(R.id.timePicker);
    timePicker.setIs24HourView(true);

    timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
        @Override
        public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
            mHour=hourOfDay;
            mMinute=minute;
        }
    });


    Button button=(Button) view.findViewById(R.id.button9);
    button.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            SQLiteDatabase database = dbHelper.getWritableDatabase();//нужен для управления
            ContentValues contentValues = new ContentValues();//нужен для добавления новых строк в таблицу выглядит как массив с именами столбцов и тд

            String selectedDate = new StringBuilder().append(mDay)
                    .append("/").append(mMonth+1).append("/").append(mYear)
                    .append(" ").append(mHour).append(":").append(mMinute).toString();//создаем стринг со всеми значениями даты
          //  selectedDate = sdf.format(selectedDate); //преобразуем его под нужные нам значения даты
            try {
                d = sdf.parse(selectedDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            contentValues.put(DBHelper.KEY_DATA, selectedDate);
            database.insert(DBHelper.TABLE_TRENING, null, contentValues);//записываем в какую таблицу.
            Toast.makeText(getApplicationContext(), selectedDate+"Данные записаны", Toast.LENGTH_LONG).show();//таск это всплывающие окно, выводим в него наш выбор, заданным форматом

            dbHelper.close();//отключаемся от бд
            dlg.dismiss();//дестрой окна

        }
    });
}



}
