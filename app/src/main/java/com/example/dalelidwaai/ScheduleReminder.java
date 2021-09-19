package com.example.dalelidwaai;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.example.dalelidwaai.model.Dosage;
import com.example.dalelidwaai.model.MyMedication;

import java.util.Calendar;
import java.util.Random;

public class ScheduleReminder {

    public static MyMedication schedule(MyMedication myMedication, Context context) {

        for (Dosage d :
                myMedication.getDosages()) {

            if(d.getTime()!=null&&!d.getTime().isEmpty()){
                String[] hoursMinutes = d.getTime().split(":");
                if (hoursMinutes.length == 2 && d.getWeekDay() > 0) {
                    int id = new Random().nextInt(200000);
                    scheduleAlarm(context, d.getWeekDay(), Integer.parseInt(hoursMinutes[0]), Integer.parseInt(hoursMinutes[1]), id);
                    d.setReminderSysId(id);
                }
            }
        }
        return myMedication;
    }

   private static void scheduleAlarm(Context context, int dayOfWeek, int hours, int minutes, int id) {

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_WEEK, dayOfWeek);
        calendar.set(Calendar.HOUR_OF_DAY, hours);
        calendar.set(Calendar.MINUTE, minutes);
        calendar.set(Calendar.SECOND, 0);

//
//        // Check we aren't setting it in the past which would trigger it to fire instantly
//        if (calendar.getTimeInMillis() < System.currentTimeMillis()) {
//            calendar.add(Calendar.DAY_OF_YEAR, 7);
//        }

        Intent intent = new Intent(context, NotificationMassage.class);
        PendingIntent yourIntent = PendingIntent.getService(context, id, intent, PendingIntent.FLAG_UPDATE_CURRENT);


        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), System.currentTimeMillis() , yourIntent);
    }

    public static void cancel(Context context, int id) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, NotificationMassage.class);
        PendingIntent pendingIntent = PendingIntent.getService(context, id, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.cancel(pendingIntent);
    }
}
