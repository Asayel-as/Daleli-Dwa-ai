package com.example.dalelidwaai.ui.medicationList;

import android.widget.TextView;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MedicationListViewModel extends ViewModel {

    private MutableLiveData<String> mText;


    public MedicationListViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is slideshow fragment");

    }

    public LiveData<String> getText() {
        return mText;
    }


}