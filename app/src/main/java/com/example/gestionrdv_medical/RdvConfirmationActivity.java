package com.example.gestionrdv_medical;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class RdvConfirmationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Hna 7yyedna try-catch. Ila kan ghalat f XML, l'app ghat-crashé db.
        setContentView(R.layout.activity_rdv_confirmation);

        ImageView btnBack = findViewById(R.id.btnBack);
        Button btnHome = findViewById(R.id.btnHome);
        TextView tvResult = findViewById(R.id.tvResult);

        // Récupérer les données
        String date = getIntent().getStringExtra("date");
        String motif = getIntent().getStringExtra("motif");
        String creneau = getIntent().getStringExtra("creneau");

        if (date != null && motif != null && creneau != null) {
            String message = "Rendez-vous confirmé le " + date + "\n" +
                    "à " + creneau + "\n" +
                    "Motif : " + motif;
            tvResult.setText(message);
        }

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RdvConfirmationActivity.this, PatientHomeActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });
    }
}