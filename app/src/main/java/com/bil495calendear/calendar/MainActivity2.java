package com.bil495calendear.calendar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.CalendarView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MainActivity2 extends AppCompatActivity {
    public int counter = 0;
    public static String date = "";
    public String dom = "";
    public String m = "";

    CalendarView calendarView;
    Context context;


    List<Appointment> appointments;
    List<Appointment> searchs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CalendarView cv = findViewById(R.id.calendarView);

        context = getApplicationContext();
        calendarView = findViewById(R.id.calendarView);
        //myDate = findViewById(R.id.myDate);
        //postData = (Button) findViewById(R.id.data);
         appointments = new ArrayList<Appointment>();
        searchs = new ArrayList<Appointment>();

        if (cv != null) {
            cv.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
                @Override
                public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                    // Note that months are indexed from 0. So, 0 means January, 1 means february, 2 means march etc.
                    if (dayOfMonth < 10) {
                        dom = "0" + dayOfMonth;
                    } else {
                        dom = dayOfMonth + "";
                    }
                    if (month + 1 < 10) {
                        m = "0" + (month + 1);
                    } else {
                        m = (month + 1) + "";
                    }
                    String msg1 = year + "-" + m + "-" + dom;
                    String msg = "Selected date is " + year + "-" + m + "-" + dom;
                    Toast.makeText(MainActivity2.this, msg, Toast.LENGTH_SHORT).show();
                    if (date.equals(msg1)) {
                        counter = 1;
                    } else {
                        counter = 0;
                        date = msg1;
                    }

                    if (counter >= 1) {
                        try {
                            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
                            Intent i = new Intent(MainActivity2.this,AddingCalenderEventAndroid.class);
                            Date dt = format.parse(date);
                            Calendar calendarEvent = Calendar.getInstance();
                            calendarEvent.setTime(dt);
                            i.putExtra("date",calendarEvent.getTimeInMillis());

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
