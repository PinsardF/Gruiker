package com.example.gruiker.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LanguagesViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public LanguagesViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Liste des langages");
    }

    public LiveData<String> getText() {
        return mText;
    }
}