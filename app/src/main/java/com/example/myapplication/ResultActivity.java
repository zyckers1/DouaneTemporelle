package com.example.myapplication;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        // استقبال النتيجة (Réception du résultat)
        Intent intentRecu = getIntent();
        String nom = intentRecu.getStringExtra(MainActivity.getCleNom("1234"));
        boolean verdict = intentRecu.getBooleanExtra("1234_VERDICT", false);

        TextView tvMessage = findViewById(R.id.tv_message_verdict_abs);
        LinearLayout layout = findViewById(R.id.layout_result_abs); // لجعل الخلفية ملونة (Pour changer la couleur de fond)

        // تغيير لون الخلفية والنص حسب النتيجة (Changement de couleur selon le verdict)
        if (verdict) {
            layout.setBackgroundColor(Color.GREEN); // أخضر للموافقة (Vert)
            tvMessage.setText("Bon voyage " + nom);
        } else {
            layout.setBackgroundColor(Color.RED); // أحمر للرفض (Rouge)
            tvMessage.setText(nom + " est en état d'arrestation temporelle");
        }

        // زر العودة إلى البداية (Bouton nouveau voyageur)
        Button btnNouveau = findViewById(R.id.btn_nouveau_voyageur_abs);
        btnNouveau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // الانتقال إلى النشاط الأول وإغلاق جميع الأنشطة الأخرى (Retour à l'activité 1 en fermant les autres)
                Intent intent = new Intent(ResultActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish(); // إغلاق النشاط الحالي
            }
        });
    }
}
