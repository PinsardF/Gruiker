package com.example.gruiker.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SettingsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public SettingsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Change your settings");
    }

    public LiveData<String> getText() {
        return mText;
    }
}