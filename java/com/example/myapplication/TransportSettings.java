package com.example.myapplication;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.SystemClock;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import java.time.Duration;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;
import android.widget.Toast;


public class TransportSettings extends AppCompatActivity {
    private Chronometer chronometer;
    private long pauseOffset = 0;
    private boolean running;
    private TextView text;
    private long timeSTART, timeEND;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transport_settings);
        getSupportActionBar().hide();

        chronometer = findViewById(R.id.chronometer);
        chronometer.setFormat("Trip time: %s");
        chronometer.setBase(SystemClock.elapsedRealtime());

    }

    public void startChronometer(View v) {
        timeSTART = System.currentTimeMillis();
        if (!running) {
            chronometer.setBase(SystemClock.elapsedRealtime() - pauseOffset);
            chronometer.start();
            running = true;
        }
    }

    public void pauseChronometer(View v) {

        if (running) {
            chronometer.stop();
            pauseOffset = SystemClock.elapsedRealtime() - chronometer.getBase();
            running = false;
            //long auxTime = SystemClock.elapsedRealtime();
            //text = (TextView) findViewById(R.id.textProva);
            //text.setText(String.valueOf(auxTime));
        }
    }
    Integer punts, puntspo;
    public void resetChronometer(View v) {
        long time = SystemClock.elapsedRealtime();
        timeEND = System.currentTimeMillis();
        final long deltaTime = timeEND - timeSTART;
        punts = (-(int)deltaTime/(1000)/5); //Restem 10 punts per cada hora
        puntspo = (int)deltaTime/(1000)/5;
        chronometer.setBase(time);
        pauseOffset = 0;


        AlertDialog.Builder builder = new AlertDialog.Builder(TransportSettings.this);
        builder.setCancelable(true);
        builder.setTitle("Bad habit...");
        builder.setMessage("You have lost "+ String.valueOf(puntspo) +" points");


        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        final View vi = v;
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent valorMain = new Intent(vi.getContext(), MainActivity.class);
                valorMain.putExtra("punts", punts.toString());
                System.out.println(valorMain);
                startActivityForResult(valorMain,0);

            }
        });
        builder.show();
    }



}

