package com.example.chatme.calendarreminder;

import android.Manifest;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.CalendarContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if ( ContextCompat.checkSelfPermission( this, Manifest.permission.READ_CALENDAR ) != PackageManager.PERMISSION_GRANTED ) {

            ActivityCompat.requestPermissions( this, new String[] {  Manifest.permission.READ_CALENDAR  },
                    100 );
        }

        if ( ContextCompat.checkSelfPermission( this, Manifest.permission.WRITE_CALENDAR ) != PackageManager.PERMISSION_GRANTED ) {

            ActivityCompat.requestPermissions( this, new String[] {  Manifest.permission.WRITE_CALENDAR  },
                    100 );
        }

        Button btn = (Button) findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                try {
                    DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
                    DateFormat timeFormatter = new SimpleDateFormat("HH:mm");
                    timeFormatter.setTimeZone(TimeZone.getTimeZone("UTC +8"));

                    // Build Event Reminder
                    EventReminder myRem = new EventReminder();
                    myRem.setTitle("Another Reminder");
                    myRem.setDescription("hahahaha");
                    myRem.setDateStart(dateFormatter.parse("2018-06-26"));
                    myRem.setTimeStart(timeFormatter.parse("12:00"));
                    myRem.setDateEnd(dateFormatter.parse("2018-06-26"));
                    myRem.setTimeEnd(timeFormatter.parse("15:00"));
                    myRem.setHasAlarm(true);

                    // Event Reminder Service
                    EventReminderService myRemService = new EventReminderService(getContentResolver(),getApplicationContext());
                    myRemService.addReminderInCalendar(myRem);

                } catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });

    }
}
