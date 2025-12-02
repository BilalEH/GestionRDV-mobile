package com.example.gestionrdv_medical;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView; // Pour le bouton retour
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class PatientHomeActivity extends AppCompatActivity {

    private TextView tvWelcome;
    private Button btnTakeRdv, btnMyRdv, btnProfile;
    private ImageView btnBack; // Déclaration du bouton retour

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_home);

        tvWelcome = findViewById(R.id.tvWelcome);
        btnTakeRdv = findViewById(R.id.btnTakeRdv);
        btnMyRdv = findViewById(R.id.btnMyRdv);
        btnProfile = findViewById(R.id.btnProfile);
        btnBack = findViewById(R.id.btnBack);

        // Récupérer le nom du patient (s'il vient du Login ou Inscription)
        String name = getIntent().getStringExtra("patientName");
        if (name != null && !name.isEmpty()) {
            tvWelcome.setText("Bienvenue, " + name);
        }

        // 2. Gestion du Bouton Retour
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // 3. Navigation vers "Prendre RDV"
        btnTakeRdv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PatientHomeActivity.this, PriseRdvActivity.class);
                startActivity(intent);
            }
        });

        // 4. Navigation vers "Mes RDV" (Historique)
        btnMyRdv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PatientHomeActivity.this, HistoryActivity.class);
                startActivity(intent);
            }
        });

        // 5. Navigation vers "Profil"
        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: Open UserManagementActivity
                Toast.makeText(PatientHomeActivity.this, "Profil : Bientôt disponible", Toast.LENGTH_SHORT).show();
            }
        });
    }
}