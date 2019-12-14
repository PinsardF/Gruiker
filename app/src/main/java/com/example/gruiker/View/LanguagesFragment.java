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
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.gruiker.Model.Animal;
import com.example.gruiker.Model.DelAnimalController;
import com.example.gruiker.Model.GetDataController;
import com.example.gruiker.Model.ListAnimal;
import com.example.gruiker.Model.SendAnimalController;
import com.example.gruiker.R;
import com.example.gruiker.ViewModel.LanguagesViewModel;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;


public class LanguagesFragment extends Fragment implements View.OnClickListener{

    String colorPrimary;
    String colorPrimaryDark;
    String animal_name;
    String begin;
    String middle;
    String ending;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    public EditText et_name;
    public EditText et_beginning;
    public EditText et_middle;
    public EditText et_ending;
    public EditText et_color;

    private NavigationView nv;
    private GetDataController getDataController;
    private SendAnimalController sendAnimalController;
    private DelAnimalController delAnimalController;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewModelProviders.of(this).get(LanguagesViewModel.class);
        final View root = inflater.inflate(R.layout.fragment_languages, container, false);

        sharedPreferences = getContext().getSharedPreferences("", Context.MODE_PRIVATE);

        getDataController = new GetDataController(this);//Appel du Controller pour récupérer les données
        getDataController.onCreate();

        final Button add_button = root.findViewById(R.id.add_button);//Définition des boutons et champs
        Button del_button = root.findViewById(R.id.del_button);
        add_button.setOnClickListener(this);
        del_button.setOnClickListener(this);
        et_name = root.findViewById(R.id.editText_name);
        et_beginning = root.findViewById(R.id.editText_beginning);
        et_middle = root.findViewById(R.id.editText_middle);
        et_ending = root.findViewById(R.id.editText_ending);
        et_color = root.findViewById(R.id.editText_color);

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
        add_button.setBackgroundColor(Color.parseColor(colorPrimary));


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
                add_button.setBackgroundColor(Color.parseColor(colorPrimary));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // TODO Auto-generated method stub
            }
        });

        return root;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.add_button) {
            String newname = et_name.getText().toString();//Création d'un animal via les champs de texte
            String newcolor_primarydark = et_color.getText().toString();
            String newcolor_primary = et_color.getText().toString();
            String newbeginning = et_beginning.getText().toString();
            String newmiddle = et_middle.getText().toString();
            String newending = et_ending.getText().toString();
            Animal newanimal = new Animal(newname, newcolor_primary, newcolor_primarydark, newbeginning, newmiddle, newending);
            sendAnimalController = new SendAnimalController(this, newanimal);
            sendAnimalController.onCreate();
        } else if(view.getId() == R.id.del_button){
            String newname = et_name.getText().toString();//Création d'un animal via les champs de texte
            String newcolor_primarydark = et_color.getText().toString();
            String newcolor_primary = et_color.getText().toString();
            String newbeginning = et_beginning.getText().toString();
            String newmiddle = et_middle.getText().toString();
            String newending = et_ending.getText().toString();
            Animal delanimal = new Animal(newname, newcolor_primary, newcolor_primarydark, newbeginning, newmiddle, newending);
            delAnimalController = new DelAnimalController(this, delanimal);
            delAnimalController.onCreate();
        } else{
            System.out.println("Error onClick (buttons)");
        }
    }
}