package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class VerificationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);

        Intent intentRecu = getIntent();
        String nom = intentRecu.getStringExtra(MainActivity.getCleNom("1234"));
        String naissanceStr = intentRecu.getStringExtra(MainActivity.getCleNaissance("1234"));
        String destinationStr = intentRecu.getStringExtra(MainActivity.getCleDestination("1234"));
        String motif = intentRecu.getStringExtra(MainActivity.getCleMotif("1234"));

        // حساب العمر الزمني (مع التحقق من أن الحقل ليس فارغاً لتجنب الانهيار)
        int ageTemporel = 0;
        if(naissanceStr != null && destinationStr != null && !naissanceStr.isEmpty() && !destinationStr.isEmpty()){
            int anneeNaissance = Integer.parseInt(naissanceStr);
            int anneeDestination = Integer.parseInt(destinationStr);
            ageTemporel = anneeDestination - anneeNaissance;
        }

        // عرض البيانات
        TextView tvNom = findViewById(R.id.tv_nom_abs);
        TextView tvAge = findViewById(R.id.tv_age_abs);
        TextView tvMotif = findViewById(R.id.tv_motif_abs);

        tvNom.setText("Nom: " + nom);
        tvAge.setText("Âge temporel: " + ageTemporel + " ans");
        tvMotif.setText("Motif: " + motif);

        Button btnApprouver = findViewById(R.id.btn_approuver_abs);
        Button btnRefuser = findViewById(R.id.btn_refuser_abs);

        // الشرط الخاص: تعديل الماضي + عدد أولي = تعطيل الزر
        if (motif != null && motif.equals("Modification du passé") && estPremier(ageTemporel)) {
            btnApprouver.setEnabled(false);
        }

        btnApprouver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lancerVerdict(true, nom);
            }
        });

        btnRefuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lancerVerdict(false, nom);
            }
        });
    }

    // دالة للتحقق مما إذا كان الرقم أولياً
    private boolean estPremier(int n) {
        if (n <= 1) return false;
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) return false;
        }
        return true;
    }

    private void lancerVerdict(boolean verdict, String nom) {
        Intent intent = new Intent(VerificationActivity.this, ResultActivity.class);
        intent.putExtra(MainActivity.getCleNom("1234"), nom);
        intent.putExtra("1234_VERDICT", verdict);
        startActivity(intent);
    }
}