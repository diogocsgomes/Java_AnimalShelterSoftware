package com.example.gps_g21.Modelos;

import com.calendarfx.model.Entry;

public class Calendario {
    private int nAttendances;

    public String id;
    public String title;
    public String startDate;
    public String endDate;
    public String startTime;
    public String endTime;
    public String zoneId;
    public String recurrenceRule;
    public boolean fullDay;
    public boolean recurring;
    public boolean recurrence;
    public String idsVoluntiers;
    public int maxVoluntiers;
    public String calendarName;

    public Calendario() {
    }

    public Calendario(String id, String title, String startDate, String endDate, String startTime, String endTime, String zoneId, String recurrenceRule, boolean fullDay, boolean recurring, boolean recurrence, String calendarName, int nAttendances) {
        this.id = id;
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.zoneId = zoneId;
        this.recurrenceRule = recurrenceRule;
        this.fullDay = fullDay;
        this.recurring = recurring;
        this.recurrence = recurrence;
        this.calendarName = calendarName;
        this.nAttendances = nAttendances;
    }

    public Calendario(String title, String startTime, String endTime, String startDate, String endDate){
        this.title = title;
        this.startTime = startTime;
        this.endTime = endTime;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Calendario(Entry c){
        this.title = c.getTitle();
        this.id = c.getId();
        this.fullDay = c.isFullDay();
        this.startDate = c.getStartDate().toString();
        this.endDate = c.getEndDate().toString();
        this.startTime = c.getStartTime().toString();
        this.endTime = c.getEndTime().toString();
        this.zoneId = c.getZoneId().toString();
        this.recurring = c.isRecurring();
        this.recurrenceRule = c.getRecurrenceRule();
        this.recurrence = c.isRecurrence();
        this.calendarName = c.getCalendar().getName();
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStartDate() {return startDate;}

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getZoneId() {
        return zoneId;
    }

    public void setZoneId(String zoneId) {
        this.zoneId = zoneId;
    }

    public String getRecurrenceRule() {
        return recurrenceRule;
    }

    public void setRecurrenceRule(String recurrenceRule) {
        this.recurrenceRule = recurrenceRule;
    }

    public boolean isFullDay() {
        return fullDay;
    }

    public void setFullDay(boolean fullDay) {
        this.fullDay = fullDay;
    }

    public boolean isRecurring() {
        return recurring;
    }

    public void setRecurring(boolean recurring) {
        this.recurring = recurring;
    }

    public boolean isRecurrence() {
        return recurrence;
    }

    public void setRecurrence(boolean recurrence) {
        this.recurrence = recurrence;
    }

    public String getIdsVoluntiers() {
        return idsVoluntiers;
    }

    public void setIdsVoluntiers(String idsVoluntiers) {
        this.idsVoluntiers = idsVoluntiers;
    }

    public int getMaxVoluntiers() {
        return maxVoluntiers;
    }

    public void setMaxVoluntiers(Integer maxVoluntiers) {
        this.maxVoluntiers = maxVoluntiers;
    }

    public String getCalendarName() {return calendarName;}
    public void setCalendarName(String calendarName) {this.calendarName = calendarName;}

    public void setNAttendances(int nAttendances){
        this.nAttendances = nAttendances;
    }

    public int getNAttendances(){
        return nAttendances;
    }
}
