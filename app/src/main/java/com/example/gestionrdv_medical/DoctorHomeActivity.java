package com.example.gestionrdv_medical;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class DoctorHomeActivity extends AppCompatActivity {

    private ImageView btnBack;
    private Button btnAvailability, btnMyAppointments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_home);

        // 1. Initialisation
        btnBack = findViewById(R.id.btnBack);
        btnAvailability = findViewById(R.id.btnAvailability);
        btnMyAppointments = findViewById(R.id.btnMyAppointments);

        // 2. Bouton Retour
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Revenir au login
            }
        });

        // 3. Vers "Gérer les disponibilités"
        btnAvailability.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Cette activité existe, on l'ouvre !
                Intent intent = new Intent(DoctorHomeActivity.this, DoctorAvailabilityActivity.class);
                startActivity(intent);
            }
        });

        // 4. Vers "Mes Rendez-vous"
        btnMyAppointments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DoctorHomeActivity.this, "Liste des RDV : Bientôt disponible", Toast.LENGTH_SHORT).show();
            }
        });
    }
}