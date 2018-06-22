package com.example.chatme.calendarreminder;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.provider.CalendarContract;
import android.widget.Toast;

import java.util.Calendar;
import java.util.TimeZone;

public class EventReminderService {

    ContentResolver cr;
    Context appContext;
    public EventReminderService(ContentResolver cr, Context appContext)
    {
           this.cr = cr;
           this.appContext = appContext;
    }

    public void addReminderInCalendar(EventReminder evRem) {
        Calendar cal = Calendar.getInstance();
        Uri EVENTS_URI = Uri.parse(getCalendarUriBase(true) + "events");
        TimeZone timeZone = TimeZone.getDefault();

        /** Inserting an event in calendar. */
        ContentValues values = new ContentValues();
        values.put(CalendarContract.Events.CALENDAR_ID, 1);
        values.put(CalendarContract.Events.TITLE, evRem.getTitle());
        values.put(CalendarContract.Events.DESCRIPTION, evRem.getDescription());
        values.put(CalendarContract.Events.ALL_DAY, 0);

        // Dealing with date and time
        Long dateStart = evRem.getDateStart().getTime() + evRem.getTimeStart().getTime();
        Long dateEnd = evRem.getDateEnd().getTime() + evRem.getTimeEnd().getTime();
        values.put(CalendarContract.Events.DTSTART, dateStart);
        values.put(CalendarContract.Events.DTEND, dateEnd);

        values.put(CalendarContract.Events.EVENT_TIMEZONE, timeZone.getID());
        values.put(CalendarContract.Events.HAS_ALARM, 1);
        Uri event = this.cr.insert(EVENTS_URI, values);

        // Display event id.
        Toast.makeText(this.appContext, "Event added :: ID :: " + event.getLastPathSegment(), Toast.LENGTH_SHORT).show();

        /** Adding reminder for event added. */
        Uri REMINDERS_URI = Uri.parse(getCalendarUriBase(true) + "reminders");
        values = new ContentValues();
        values.put(CalendarContract.Reminders.EVENT_ID, Long.parseLong(event.getLastPathSegment()));
        values.put(CalendarContract.Reminders.METHOD, CalendarContract.Reminders.METHOD_ALERT);
        values.put(CalendarContract.Reminders.MINUTES, 10);
        this.cr.insert(REMINDERS_URI, values);
    }

    /** Returns Calendar Base URI, supports both new and old OS. */
    private String getCalendarUriBase(boolean eventUri) {
        Uri calendarURI = null;
        try {
            if (android.os.Build.VERSION.SDK_INT <= 7) {
                calendarURI = (eventUri) ? Uri.parse("content://calendar/") : Uri.parse("content://calendar/calendars");
            } else {
                calendarURI = (eventUri) ? Uri.parse("content://com.android.calendar/") : Uri
                        .parse("content://com.android.calendar/calendars");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return calendarURI.toString();
    }
}
