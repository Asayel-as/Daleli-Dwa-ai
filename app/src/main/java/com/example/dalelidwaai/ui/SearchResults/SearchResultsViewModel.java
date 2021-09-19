package com.example.dalelidwaai.ui.SearchResults;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SearchResultsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public SearchResultsViewModel() {
        mText = new MutableLiveData<>();
    }

    public LiveData<String> getText() {
        return mText;
    }
}