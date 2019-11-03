package com.example.gruiker.View;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.gruiker.Model.MainActivity;
import com.example.gruiker.R;
import com.example.gruiker.ViewModel.LanguagesViewModel;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;


public class LanguagesFragment extends Fragment {

    String colorPrimary;
    String colorPrimaryDark;
    String animal_name;
    String begin;
    String middle;
    String ending;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    private LanguagesViewModel languagesViewModel;
    private DrawerLayout dl;
    private NavigationView nv;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        languagesViewModel = ViewModelProviders.of(this).get(LanguagesViewModel.class);
        final View root = inflater.inflate(R.layout.fragment_languages, container, false);

        sharedPreferences = getContext().getSharedPreferences("", Context.MODE_PRIVATE);

        dl = (DrawerLayout)getActivity().findViewById(R.id.activity_main);
        nv = (NavigationView)getActivity().findViewById(R.id.nv);

        ArrayList<String> languages_list = new ArrayList<String>();//Remplissage de la liste de langages
        languages_list.add("Cochon");
        languages_list.add("Chat");
        languages_list.add("Chien");

        //sharedPreferences = getContext().getSharedPreferences("", Context.MODE_PRIVATE);//récupération des couleurs
        editor = sharedPreferences.edit();
        colorPrimary = sharedPreferences.getString("current_primarycolor","");
        colorPrimaryDark = sharedPreferences.getString("current_primarycolordark","");

        final Spinner spinner = (Spinner) root.findViewById(R.id.languages_selector);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(),R.layout.support_simple_spinner_dropdown_item,languages_list);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setBackgroundColor(Color.parseColor(colorPrimary));
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                switch(position){
                    case 0:
                        colorPrimary = "#FF9ECE";
                        colorPrimaryDark = "#FF69B4";
                        animal_name = "Cochon";
                        begin = "gr";
                        middle = "u";
                        ending = "ik";
                        break;
                    case 1:
                        colorPrimary = "#9B84FF";
                        colorPrimaryDark = "#8569FF";
                        animal_name = "Chat";
                        begin = "mi";
                        middle = "a";
                        ending = "ou";
                        break;
                    case 2:
                        colorPrimary = "#FE8F7A";
                        colorPrimaryDark = "#FF7D64";
                        animal_name = "Chien";
                        begin = "ou";
                        middle = "a";
                        ending = "f";
                        break;
                }
                editor.putString("current_primarycolor",colorPrimary);
                editor.putString("current_primarycolordark",colorPrimaryDark);
                editor.putString("current_language",animal_name);
                editor.putString("current_beginning",begin);
                editor.putString("current_middle",middle);
                editor.putString("current_ending",ending);
                editor.apply();
                spinner.setBackgroundColor(Color.parseColor(colorPrimary));
                nv.getHeaderView(0).setBackgroundColor(Color.parseColor(colorPrimary));
                TextView textView_animal = nv.getHeaderView(0).findViewById(R.id.currentLanguageView);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // TODO Auto-generated method stub

            }
        });
        return root;
    }

}