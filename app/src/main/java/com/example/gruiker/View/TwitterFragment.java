package com.example.gruiker.View;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.gruiker.R;
import com.example.gruiker.ViewModel.TwitterViewModel;

public class TwitterFragment extends Fragment {

    private TwitterViewModel twitterViewModel;
    private Boolean translated;
    private TextView textView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        twitterViewModel =
                ViewModelProviders.of(this).get(TwitterViewModel.class);
        View root = inflater.inflate(R.layout.fragment_twitter, container, false);
        textView = root.findViewById(R.id.twitterTextView);
        twitterViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        Button button = root.findViewById(R.id.trad_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch_lang();
            }
        });

        translated = false;
        switch_lang();
        return root;
    }

    private String language(String text){
        String new_text = "";
        Boolean first_word = false;

        String[] sentences = text.split("\\.|\\?");//     ".|\\?"
        for(String sentence : sentences) {
            first_word = true;
            String new_sentence = "";
            String[] propositions = sentence.split(", ");
            for (String proposition : propositions) {//traitement des propositions (",")
                String new_proposition = "";
                String[] words = proposition.split(" ");
                String new_word = "";
                for (String word : words) {//traitement du mot
                    if (word.length() > 4) {
                        if(first_word){
                            new_word = "Gr";
                        } else {
                            new_word = "gr";
                        }
                        for (int i = 0; i < word.length() - 4; i++) {
                            new_word += "u";
                        }
                        new_word += "ik";
                    } else {
                        new_word = word;
                    }

                    first_word = false;

                    new_word += " ";
                    new_proposition += new_word;
                }
                new_proposition = new_proposition.substring(0, new_proposition.length() - 1) + ", ";
                new_sentence += new_proposition;
            }
            new_sentence = new_sentence.substring(0, new_sentence.length() - 2) + ". ";
            new_text += new_sentence;
        }
        return new_text;
    }

    public void switch_lang(){
        String raw_text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, " +
                "sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. " +
                "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi " +
                "ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit " +
                "in voluptate velit esse cillum dolore eu fugiat nulla pariatur. " +
                "Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia " +
                "deserunt mollit anim id est laborum.";
        String text = "";
        if(translated){
            text = raw_text;
            translated = false;
        } else{
            text = language(raw_text);
            translated = true;
        }
        textView.setText(text);

        /*mText = new MutableLiveData<>();
        mText.setValue(text);*/
    }
}