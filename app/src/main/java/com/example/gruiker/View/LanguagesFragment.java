package com.example.gruiker.View;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.gruiker.R;
import com.example.gruiker.ViewModel.LanguagesViewModel;

import java.util.ArrayList;


public class LanguagesFragment extends Fragment {

    String color;

    private LanguagesViewModel languagesViewModel;

    public LanguagesFragment(String color){
        this.color = color;
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        languagesViewModel = ViewModelProviders.of(this).get(LanguagesViewModel.class);
        View root = inflater.inflate(R.layout.fragment_languages, container, false);

        ArrayList<String> languages_list = new ArrayList<String>();//Remplissage de la liste de langages
        languages_list.add("Cochon");
        languages_list.add("Chat");
        languages_list.add("Chien");

        Spinner spinner = (Spinner) root.findViewById(R.id.languages_selector);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(),R.layout.support_simple_spinner_dropdown_item,languages_list);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setBackgroundColor(Color.parseColor(color));

        return root;
    }
}