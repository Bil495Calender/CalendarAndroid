package com.bil495calendear.calendar;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.CalendarView;
import android.widget.Toast;
import java.util.Calendar;
import android.content.Intent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;
import java.util.Locale;

public class MainActivity2 extends AppCompatActivity {
    AddingCalenderEventAndroid addingCal = new AddingCalenderEventAndroid();
    public int counter = 0;
    public String date = "";
    public String dom = "";
    public String m = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CalendarView calendarView = findViewById(R.id.calendarView);

        if (calendarView != null) {
            calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
                @Override
                public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                    // Note that months are indexed from 0. So, 0 means January, 1 means february, 2 means march etc.
                    if(dayOfMonth < 10){
                        dom = "0" + dayOfMonth;
                    }
                    else{
                        dom = dayOfMonth + "";
                    }
                    if(month+1 < 10){
                        m = "0" + (month + 1);
                    }
                    else{
                        m = (month + 1) + "";
                    }
                    String msg1 = dom + "/" + m + "/" + year;
                    String msg = "Selected date is " + dom + "/" + m + "/" + year;
                    Toast.makeText(MainActivity2.this, msg, Toast.LENGTH_SHORT).show();
                    if(date.equals(msg1)){
                        counter = 1;
                    }
                    else{
                        counter = 0;
                        date = msg1;
                    }
                    if(counter == 1){
                        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
                        try {
                            Date dt = format.parse(date);
                            Calendar calendarEvent = Calendar.getInstance();
                            calendarEvent.setTime(dt);
                            Intent i = new Intent(Intent.ACTION_EDIT);
                            i.setType("vnd.android.cursor.item/event");
                            i.putExtra("beginTime", calendarEvent.getTimeInMillis());
                            i.putExtra("allDay", true);
                            i.putExtra("rule", "FREQ=YEARLY");
                            i.putExtra("endTime", calendarEvent.getTimeInMillis() + 60 * 60 * 1000);
                            i.putExtra("title", "");
                            startActivity(i);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }

                }
            });


        }


    }
}
