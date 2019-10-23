package com.example.gruiker.Model;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.gruiker.View.LanguagesFragment;
import com.example.gruiker.R;
import com.example.gruiker.View.SettingsFragment;
import com.example.gruiker.View.TwitterFragment;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;
    String colorPrimary = "#008577";
    String colorPrimaryDark = "#00574B";
    String animal_name = "";

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences("", Context.MODE_PRIVATE);//Création du langage de base si besoin
        editor = sharedPreferences.edit();//Sinon, mise en place du langage choisi à la dernière utilisation de l'app
        String current_language = sharedPreferences.getString("current_language","");
        if(current_language.equals("")){
            editor.putString("current_language","Cochon");
            editor.putString("current_primarycolor","#FF9ECE");
            editor.putString("current_primarycolordark","#FF69B4");
            editor.putString("current_beginning","Gr");
            editor.putString("current_middle","u");
            editor.putString("current_ending","ik");
            editor.apply();
        }

        colorPrimary = sharedPreferences.getString("current_primarycolor","");//Détermination des couleurs
        colorPrimaryDark = sharedPreferences.getString("current_primarycolordark","");
        animal_name = sharedPreferences.getString("current_language","");

        dl = (DrawerLayout)findViewById(R.id.activity_main);
        t = new ActionBarDrawerToggle(this, dl,R.string.Open, R.string.Close);

        dl.addDrawerListener(t);//Création du Drawer et du DrawerListener
        t.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Couleur de la barre d'action
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor(colorPrimaryDark)));

        Fragment fragment = new TwitterFragment();//Affichage du Fragment par défaut (le Fragment TwitterFragment)
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.activity_main_frame_layout, fragment);
        ft.commit();

        nv = (NavigationView)findViewById(R.id.nv);//Mise en place du NavigationDrawer
        nv.getHeaderView(0).setBackgroundColor(Color.parseColor(colorPrimary));
        TextView textView = findViewById(R.id.currentLanguageView);
        textView.setText(animal_name);
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                Fragment fragment = null;
                switch(id)
                {
                    case R.id.language:
                        fragment = new LanguagesFragment();
                        break;
                    case R.id.settings:
                        fragment = new SettingsFragment();
                        break;
                    case R.id.twitter:
                        fragment = new TwitterFragment();
                        break;
                    default:
                        return true;
                }

                if (fragment != null) {//Remplacement du fragment actuel par le fragment choisi
                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.activity_main_frame_layout, fragment);
                    ft.commit();
                }else{//Message d'erreur si aucun fragment n'a correctement été sélectionné
                    Toast toast = Toast.makeText(getApplicationContext(),"ERROR: No Fragment Selected",Toast.LENGTH_SHORT);
                    toast.show();
                }

                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.activity_main);//Fin de la mise en place du Drawer, et
                drawer.closeDrawer(GravityCompat.START);//activation du Drawer

                return true;

            }
        });


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {//Nécessaire pour que, lorsqu'on appuie sur les 3 traits

        if(t.onOptionsItemSelected(item))// en haut à gauche, le Drawer s'affiche
            return true;

        return super.onOptionsItemSelected(item);
    }
}