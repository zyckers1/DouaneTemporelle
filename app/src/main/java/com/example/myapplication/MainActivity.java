package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // استرجاع العناصر من الواجهة (Récupération des éléments)
        EditText editNom = findViewById(R.id.nom_abs);
        EditText editNaissance = findViewById(R.id.anne_de_naissance_abs);
        EditText editDestination = findViewById(R.id.anne_de_destination_abs);
        RadioGroup radioGroup = findViewById(R.id.radio_group_abs);
        Button btnInspecter = findViewById(R.id.button_id_abs);

        btnInspecter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // قراءة النصوص المدخلة (Lecture des textes saisis)
                String nomSaisi = editNom.getText().toString();
                String naissanceSaisie = editNaissance.getText().toString();
                String destinationSaisie = editDestination.getText().toString();

                String motif = "Tourisme"; // القيمة الافتراضية (Valeur par défaut)
                int selectedId = radioGroup.getCheckedRadioButtonId();
                if (selectedId == R.id.modification_du_passe_abs) {
                    motif = "Modification du passé";
                } else if (selectedId == R.id.fuite_abs) {
                    motif = "Fuite";
                }

                // الانتقال إلى النشاط الثاني مع تمرير البيانات (Passage à l'activité 2)
                Intent intent = new Intent(MainActivity.this, VerificationActivity.class);

                // استخدام المفاتيح الثابتة (Utilisation des méthodes statiques)
                intent.putExtra(getCleNom("1234"), nomSaisi);
                intent.putExtra(getCleNaissance("1234"), naissanceSaisie);
                intent.putExtra(getCleDestination("1234"), destinationSaisie);
                intent.putExtra(getCleMotif("1234"), motif);

                startActivity(intent);
            }
        });
    }

    // دوال ثابتة لإنشاء المفاتيح (Méthodes statiques selon la contrainte du prof)
    public static String getCleNom(String matricule) { return matricule + "_NOM_VOYAGEUR"; }
    public static String getCleNaissance(String matricule) { return matricule + "_ANNEE_NAISSANCE"; }
    public static String getCleDestination(String matricule) { return matricule + "_ANNEE_DESTINATION"; }
    public static String getCleMotif(String matricule) { return matricule + "_MOTIF"; }
}
