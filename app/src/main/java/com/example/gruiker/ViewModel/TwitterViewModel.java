package com.example.gruiker.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class TwitterViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    private String language(String text){
        String new_text = "";
        String[] sentences = text.split(".");
        //propositions
        for(String sentence : sentences){
            String[] propositions = sentence.split(",");
            //mots
            String new_proposition = "";
            String new_sentence = "";
            for (String proposition : propositions) {
                String[] words = proposition.split(" ");
                for (String word : words) {
                    String new_word = "";
                    if (word.length() > 4) {
                        new_word = "Gr";
                        for (int i = 0; i < word.length() - 4; i++) {
                            new_word += " ";
                        }
                        new_word += "ik ";
                    } else {
                        new_word = word + " ";
                    }
                    new_proposition += new_word;
                }
                new_proposition = new_proposition.substring(0,new_proposition.length()-1)+", ";
                new_sentence += new_proposition;
            }
            new_sentence = new_sentence.substring(0,new_sentence.length()-2) + ". ";
            new_text += new_sentence;
        }
        return new_text;
    }

    private String test_language(String text){
        String new_text = "";

        String[] sentences = text.split("\\.|\\?");//     ".|\\?"
        for(String sentence : sentences) {
            String new_sentence = "";
            String[] propositions = sentence.split(", ");
            for (String proposition : propositions) {//traitement des propositions (",")
                String new_proposition = "";
                String[] words = proposition.split(" ");
                String new_word = "";
                for (String word : words) {//traitement du mot
                    if (word.length() > 4) {
                        new_word = "gr";
                        for (int i = 0; i < word.length() - 4; i++) {
                            new_word += "u";
                        }
                        new_word += "ik";
                    } else {
                        new_word = word;
                    }

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

    public TwitterViewModel() {
        String raw_text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, " +
                "sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. " +
                "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi " +
                "ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit " +
                "in voluptate velit esse cillum dolore eu fugiat nulla pariatur. " +
                "Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia " +
                "deserunt mollit anim id est laborum.";
        String treated_text = test_language("Bonjour mon cher monsieur, bonne journée. Ca va ?");
        mText = new MutableLiveData<>();
        mText.setValue(treated_text);
    }

    public LiveData<String> getText() {
        return mText;
    }
}