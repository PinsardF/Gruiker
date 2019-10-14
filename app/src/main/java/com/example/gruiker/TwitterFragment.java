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

public class TwitterFragment extends Fragment {

    private com.example.gruiker.TwitterViewModel twitterViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        twitterViewModel =
                ViewModelProviders.of(this).get(com.example.gruiker.TwitterViewModel.class);
        View root = inflater.inflate(R.layout.fragment_twitter, container, false);
        final TextView textView = root.findViewById(R.id.twitterTextView);
        twitterViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}