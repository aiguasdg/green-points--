package com.example.myapplication;

import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.button.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.content.Intent;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    static final int REQUEST_IMAGE_CAPTURE = 1;
    Integer counter, counterVal = 10;
    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;
    TextView textCounter;
    String valorMem;

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        //BottomNavigationView navView = findViewById(R.id.nav_view);
        sharedPref = getSharedPreferences("mypref", 0);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
       // NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        //NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
       // NavigationUI.setupWithNavController(navView, navController);

        // counter = sharedPref.getInt("counter", 0);
        //textCounter = (TextView) findViewById(R.id.counter);
        // textCounter.setText(String.valueOf(counter));
        sharedPref = getSharedPreferences("name", 0);
        String puntuation = "";
        valorMem=sharedPref.getString("puntKey", "");
        if(valorMem == ""){
            System.out.println("ifINI");
            puntuation = "0";
            System.out.println("ifFIN");
        }else {
           try {
               Bundle bundle = getIntent().getExtras();
               System.out.println("tryINI");
               if(bundle.getString("punts") != null) {
                   puntuation = bundle.getString("punts");
               }else{
                   puntuation = sharedPref.getString("puntKey", null);
               }
               System.out.println("tryFIN");

           } catch (Exception e) {
               System.out.println("catchINI");
               puntuation = "0";
               System.out.println("catchFIN");

           }
           Integer puntInt = Integer.parseInt(puntuation);
           if(!valorMem.equals(puntuation)){
               puntInt= Integer.parseInt(valorMem) + Integer.parseInt(puntuation);
               puntuation = puntInt.toString();
           }

        }
            System.out.println(puntuation);
            editor = sharedPref.edit();
            editor.putString("puntKey", puntuation);
            editor.commit();
            System.out.println(sharedPref.getString("puntKey", null));
            puntuation = sharedPref.getString("puntKey", null);
            TextView out = (TextView) findViewById(R.id.textView2);
            out.setText(puntuation);
        //TextView out = (TextView) findViewById(R.id.counter);
        /*try {
            TextView out = (TextView) findViewById(R.id.counter);
            out.setText(puntuation);
        }catch (Exception e){
            System.out.println("aaa");
        }*/


        FloatingActionButton floatingActionButton = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //PER ACCEDIR A UN ALTRE ACTIVITAT
                Intent intent = new Intent (v.getContext(), SecondPanel.class);
                startActivityForResult(intent, 0);
                //PER ACCEDIR A LA CÀMERA
                //dispatchTakePictureIntent();
                  //  counterVal = counterVal + 10;
                  //  editor.putInt("counter", counterVal);
                  //  editor.commit();
                 //   textCounter.setText(String.valueOf(counterVal));
            }
        });
    }

}
