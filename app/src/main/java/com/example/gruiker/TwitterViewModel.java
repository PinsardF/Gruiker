package com.example.gruiker;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class TwitterViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public TwitterViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Here will be displayed tweets");
    }

    public LiveData<String> getText() {
        return mText;
    }
}