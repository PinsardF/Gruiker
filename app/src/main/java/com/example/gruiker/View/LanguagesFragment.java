package com.example.gruiker.View;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.gruiker.Model.Animal;
import com.example.gruiker.Model.GetDataController;
import com.example.gruiker.Model.ListAnimal;
import com.example.gruiker.R;
import com.example.gruiker.ViewModel.LanguagesViewModel;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;

import java.util.ArrayList;
import java.util.List;


public class LanguagesFragment extends Fragment {

    String colorPrimary;
    String colorPrimaryDark;
    String animal_name;
    String begin;
    String middle;
    String ending;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    private NavigationView nv;
    private GetDataController getDataController;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewModelProviders.of(this).get(LanguagesViewModel.class);
        final View root = inflater.inflate(R.layout.fragment_languages, container, false);

        sharedPreferences = getContext().getSharedPreferences("", Context.MODE_PRIVATE);

        getDataController = new GetDataController(this);//Appel du Controller pour récupérer les données
        getDataController.onCreate();

        getActivity().findViewById(R.id.activity_main);
        nv = (NavigationView)getActivity().findViewById(R.id.nv);

        String animals_list_json = sharedPreferences.getString("animals_list","");//Récupération des nouvelles
        Gson gson = new Gson();//données envoyées en cache
        final List<Animal> animals_list = gson.fromJson(animals_list_json,ListAnimal.class).getAnimals_list();

        ArrayList<String> languages_list = new ArrayList<String>();//Remplissage de la liste de langages
        for(int i=0;i<animals_list.size();i++) {
            languages_list.add(animals_list.get(i).getName());
        }

        editor = sharedPreferences.edit();
        colorPrimary = sharedPreferences.getString("current_primarycolor","");
        colorPrimaryDark = sharedPreferences.getString("current_primarycolordark","");


        final Spinner spinner = (Spinner) root.findViewById(R.id.languages_selector);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(),R.layout.support_simple_spinner_dropdown_item,languages_list);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setBackgroundColor(Color.parseColor(colorPrimary));
        spinner.setSelection(sharedPreferences.getInt("current_id",0));
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {

                colorPrimary = animals_list.get(position).getPrimary_color();
                colorPrimaryDark = animals_list.get(position).getPrimary_color_dark();
                animal_name = animals_list.get(position).getName();
                begin = animals_list.get(position).getBeginning();
                middle = animals_list.get(position).getMiddle();
                ending = animals_list.get(position).getEnding();

                editor.putString("current_primarycolor",colorPrimary);
                editor.putString("current_primarycolordark",colorPrimaryDark);
                editor.putString("current_language",animal_name);
                editor.putString("current_beginning",begin);
                editor.putString("current_middle",middle);
                editor.putString("current_ending",ending);
                editor.putInt("current_id",position);
                editor.apply();
                spinner.setBackgroundColor(Color.parseColor(colorPrimary));
                nv.getHeaderView(0).setBackgroundColor(Color.parseColor(colorPrimary));
                TextView textView_animal = nv.getHeaderView(0).findViewById(R.id.currentLanguageView);
                textView_animal.setText(animal_name);
                spinner.setSelection(position);
                com.example.gruiker.Model.MainActivity activity = (com.example.gruiker.Model.MainActivity) getActivity();
                activity.changeColor(colorPrimaryDark);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // TODO Auto-generated method stub
            }
        });

        return root;
    }
}