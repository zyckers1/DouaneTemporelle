package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        EditText nom=findViewById(R.id.nom_abs);
        EditText anne_naissance=findViewById(R.id.anne_de_naissance_abs);
        EditText anne_destination=findViewById(R.id.anne_de_destination_abs);


        RadioGroup radiogroup=findViewById(R.id.radio_group_abs);
        Button btnInspecter = findViewById(R.id.button_id);


        btnInspecter.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                String motif="";
                RadioButton tourisme=findViewById(R.id.tourisme_abs);
                RadioButton modification_du_passe=findViewById(R.id.modification_du_passe_abs);
                RadioButton fuite=findViewById(R.id.fuite_abs);

                String no = nom.getText().toString();
                String anne_nais = anne_naissance.getText().toString();
                String anne_dest = anne_destination.getText().toString();


                if (radiogroup.getCheckedRadioButtonId() != -1) {

                    int checked_id = radiogroup.getCheckedRadioButtonId();

                    if (checked_id == R.id.tourisme_abs) {
                        motif="tourisme";
                    }
                    if (checked_id == R.id.modification_du_passe_abs) {
                        motif="modification de passe";
                    }
                    if (checked_id == R.id.fuite_abs) {
                        motif="fuite";
                    }

                }
            }



        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);


            return insets;
        }
        );

    }

}