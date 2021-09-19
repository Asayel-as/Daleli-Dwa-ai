package com.example.dalelidwaai;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.example.dalelidwaai.model.Medication;
import com.example.dalelidwaai.model.MyMedication;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cache {

    SharedPreferences sharedPreferences;
    Type listType = new TypeToken<List<MyMedication>>() {
    }.getType();

    public Cache(Context context) {

        sharedPreferences = context.getSharedPreferences("medicationSP", Context.MODE_PRIVATE);
    }


     public void save(MyMedication scheduled) {
        String reminders = sharedPreferences.getString("reminder", "");
        List<MyMedication> allMyMedications = new ArrayList();
        if (!TextUtils.isEmpty(reminders)) {
            List<MyMedication> cachedMyMedications = new Gson().fromJson(reminders, listType);
            allMyMedications.addAll(cachedMyMedications);
        }

        allMyMedications.add(scheduled);

        sharedPreferences.edit().putString("reminder",new Gson().toJson(allMyMedications)).apply();
    }

    public void updateExpiryDate(MyMedication scheduled) {

        List<MyMedication> allMyMedications = getAllMedications();
        for(int i=0;i<allMyMedications.size();i++){
            if(scheduled.getId().toString().equals(allMyMedications.get(i).getId().toString()) || scheduled.getMedication().toString().equals(allMyMedications.get(i).getMedication().toString())){
                allMyMedications.get(i).setExpiryDate(scheduled.getExpiryDate());
            }
        }
        sharedPreferences.edit().putString("reminder",new Gson().toJson(allMyMedications)).apply();
    }

    public void removeMedication(MyMedication scheduled) {
        List<MyMedication> allMyMedications = getAllMedications();
        for(int i=0;i<allMyMedications.size();i++){
            if(scheduled.getId().equals(allMyMedications.get(i).getId()) && scheduled.getMedication().toString().equals(allMyMedications.get(i).getMedication().toString())){
                allMyMedications.remove(i);
               }
        }
        sharedPreferences.edit().putString("reminder",new Gson().toJson(allMyMedications)).apply();
    }

    public List<MyMedication> getAllMedications(){
        String reminders = sharedPreferences.getString("reminder", "");

        if(TextUtils.isEmpty(reminders))
            return Collections.emptyList();

        return new Gson().fromJson(reminders, listType);
    }


}
