package com.example.gruiker.ViewModel;

import android.content.SharedPreferences;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SettingsViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    public SettingsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Change your settings");
    }

    public void clear_cache(){

    }

    public LiveData<String> getText() {
        return mText;
    }
}