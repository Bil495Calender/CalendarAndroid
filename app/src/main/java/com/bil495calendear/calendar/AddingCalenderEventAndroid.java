////How to Add an Event to Device Calendar in Android Application
package com.bil495calendear.calendar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddingCalenderEventAndroid extends AppCompatActivity {
    EditText text,text2;
    public String date;
    String comment;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adding_calender_event_in_android);
        Intent intent = getIntent();
        text = (EditText) findViewById(R.id.comment);
        text2 = (EditText) findViewById(R.id.date);
        text2.setText(MainActivity2.date);

        btn = (Button) findViewById(R.id.createButton);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sending(view);
            }
        });
    }

    public void sending(View view) {
        comment=text.getText().toString();
        date=text2.getText().toString();
        String msg = "Your appointment added the database with \"" + comment + "\"\nAnd date : " + date;
        Toast.makeText(AddingCalenderEventAndroid.this, msg, Toast.LENGTH_SHORT).show();
    }

}