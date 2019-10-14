package com.example.gruiker;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.gruiker.R;


public class LanguagesFragment extends Fragment {

    private com.example.gruiker.LanguagesViewModel languagesViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        languagesViewModel = ViewModelProviders.of(this).get(com.example.gruiker.LanguagesViewModel.class);
        View root = inflater.inflate(R.layout.fragment_languages, container, false);
        final TextView textView = root.findViewById(R.id.text_languages);
        languagesViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}