package com.bil495calendear.calendar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

public class CalendarViewExample extends AppCompatActivity {

    CalendarView calendarView;
    TextView dateDisplay;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        System.out.println("111");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.android_calendar_view_example);

        calendarView = (CalendarView) findViewById(R.id.calendarView);
        dateDisplay = (TextView) findViewById(R.id.date_display);
        dateDisplay.setText("Date: ");


        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView calendarView, int i, int i1, int i2) {
                System.out.println("0");
                dateDisplay.setText("Date: " + i2 + " / " + i1 + " / " + i);
                System.out.println("1");
                Toast.makeText(getApplicationContext(), "Selected Date:\n" + "Day = " + i2 + "\n" + "Month = " + i1 + "\n" + "Year = " + i, Toast.LENGTH_LONG).show();
                System.out.println("2");
            }
        });
    }
}