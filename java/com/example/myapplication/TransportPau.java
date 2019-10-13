package com.example.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class TransportPau extends AppCompatActivity {


    TextView textCounter;
    Integer counterVal;
    Integer counter;
    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;
    private static final Integer CAR_VAL = -4, BIKE_VAL=+5, INIT_VAL=100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transport_pau);

        sharedPref = getSharedPreferences("mypref", 0);
        editor = sharedPref.edit();
        if((counterVal=sharedPref.getInt("counter", 0)) == null){
            counterVal = INIT_VAL;
        }
        editor.putInt("counter", counterVal);
        editor.commit();
        counter = sharedPref.getInt("counter", 0);
        textCounter = (TextView) findViewById(R.id.counter);
        textCounter.setText(String.valueOf(counter));


        ImageButton bikeImage = (ImageButton)findViewById(R.id.ibbike);
        bikeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(TransportPau.this);
                builder.setCancelable(true);
                builder.setTitle("Congratulations!");
                builder.setMessage("You have earned "+String.valueOf(counterVal)+" points");


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
                        counterVal = BIKE_VAL;
                        editor.putInt("counter", counterVal);
                        editor.commit();
                        textCounter.setText(String.valueOf(counterVal));
                        Intent valorMain = new Intent(vi.getContext(), MainActivity.class);
                        valorMain.putExtra("punts", counterVal.toString());
                        startActivityForResult(valorMain,0);

                    }
                });
                builder.show();
            }
        });


        ImageButton carImage = (ImageButton)findViewById(R.id.ibcar);
        carImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), TransportSettings.class);
                startActivityForResult(intent, 0);

            }
        });



    }


}
