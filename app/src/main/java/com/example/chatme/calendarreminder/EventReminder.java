package com.example.chatme.calendarreminder;
import java.util.Date;

public class EventReminder {
    private String title;
    private String description;
    private Date dateStart;
    private Date timeStart;
    private Date dateEnd;
    private Date timeEnd;
    private Boolean hasAlarm;

    public EventReminder()
    {

    }

    public EventReminder(String title, String description, Date dateStart, Date timeStart, Date dateEnd, Date timeEnd, Boolean hasAlarm)
    {
        this.title = title;
        this.description = description;
        this.dateStart = dateStart;
        this.timeStart = timeStart;
        this.dateEnd = dateEnd;
        this.timeEnd = timeEnd;
        this.hasAlarm = hasAlarm;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public Date getTimeStart() {
        return timeStart;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public Date getTimeEnd() {
        return timeEnd;
    }

    public Boolean getHasAlarm() {
        return hasAlarm;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public void setTimeStart(Date timeStart) {
        this.timeStart = timeStart;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public void setTimeEnd(Date timeEnd) {
        this.timeEnd = timeEnd;
    }

    public void setHasAlarm(Boolean hasAlarm) {
        this.hasAlarm = hasAlarm;
    }
}
