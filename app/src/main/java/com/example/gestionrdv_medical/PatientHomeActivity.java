package com.example.gestionrdv_medical;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class PatientHomeActivity extends AppCompatActivity {

    private TextView tvWelcome;
    private Button btnTakeRdv, btnMyRdv, btnProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_home);

        tvWelcome = findViewById(R.id.tvWelcome);
        btnTakeRdv = findViewById(R.id.btnTakeRdv);
        btnMyRdv = findViewById(R.id.btnMyRdv);
        btnProfile = findViewById(R.id.btnProfile);

        // Récupérer le nom envoyé depuis l'inscription
        String name = getIntent().getStringExtra("patientName");
        if (name != null && !name.isEmpty()) {
            tvWelcome.setText(getString(R.string.welcome_patient) + " " + name);
        }

        btnTakeRdv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Vers Prise de RDV (Bientôt disponible ou PriseRdvActivity si elle existe)
                Toast.makeText(PatientHomeActivity.this, "Prise de RDV : Bientôt", Toast.LENGTH_SHORT).show();
                // Intent intent = new Intent(PatientHomeActivity.this, PriseRdvActivity.class);
                // startActivity(intent);
            }
        });

        btnMyRdv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PatientHomeActivity.this, "Mes RDV : Bientôt", Toast.LENGTH_SHORT).show();
            }
        });

        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PatientHomeActivity.this, "Mon Profil : Bientôt", Toast.LENGTH_SHORT).show();
            }
        });
    }
}