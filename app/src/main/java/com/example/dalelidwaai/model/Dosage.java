package com.example.dalelidwaai.model;


public class Dosage {
    int weekDay;
    String dosageValue;
    String time;
    int reminderSysId;
    public Dosage(int weekDay, String dosageValue, String time) {
        this.weekDay = weekDay;
        this.dosageValue = dosageValue;
        this.time = time;
    }

    public int getReminderSysId() {
        return reminderSysId;
    }

    public void setReminderSysId(int reminderSysId) {
        this.reminderSysId = reminderSysId;
    }

    public int getWeekDay() {
        return weekDay;
    }

    public String getDosageValue() {
        return dosageValue;
    }

    public String getTime() {
        return time;
    }
}
