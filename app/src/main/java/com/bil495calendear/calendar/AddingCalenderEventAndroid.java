////How to Add an Event to Device Calendar in Android Application
package com.bil495calendear.calendar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class AddingCalenderEventAndroid extends AppCompatActivity {
    EditText text,text2;
    public String date;
    String comment;
    Button btn;
    TextView myDate;
    Appointment appointment;
    Appointment appointment2;
    RequestQueue requestQueue;

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

        Cache cache = new DiskBasedCache(getCacheDir(), 1024 * 1024); // 1MB cap
        Network network = new BasicNetwork(new HurlStack());
        RequestQueue queue = Volley.newRequestQueue(this);  // this = context
        requestQueue = new RequestQueue(cache, network);
        queue.start();
        requestQueue.start();

        final String URL = "https://serene-shelf-61040.herokuapp.com/app_appointments.json";
        final Appointment postAppointment = new Appointment();

        StringRequest postRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // response
                Toast.makeText(AddingCalenderEventAndroid.this, "response", Toast.LENGTH_SHORT).show();

                Log.d("Response", response);
            }
        },
                new Response.ErrorListener()
                {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Toast.makeText(AddingCalenderEventAndroid.this, error.toString(), Toast.LENGTH_SHORT).show();

                        Log.d("Error.Response", error.toString());
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError
            {
                Map<String, String>  params = new HashMap<String, String>();
                System.out.println(text2.toString());
                params.put("AppointmentBody", "deneme");
                params.put("DatenTime",text2.toString()+"T15:00:00.000Z");

                return params;
            }
        };
        requestQueue.add(postRequest);
    }

}