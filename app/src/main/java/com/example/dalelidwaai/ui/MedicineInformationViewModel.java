package com.example.dalelidwaai.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MedicineInformationViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public MedicineInformationViewModel() {
        mText = new MutableLiveData<>();
    }

    public LiveData<String> getText() {
        return mText;
    }
}