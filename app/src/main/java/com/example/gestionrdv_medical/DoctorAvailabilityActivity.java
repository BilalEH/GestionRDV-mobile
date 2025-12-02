package com.example.gestionrdv_medical;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class DoctorAvailabilityActivity extends AppCompatActivity {

    private ImageView btnBack;
    private Button btnSave;
    private CheckBox cbLundi, cbMardi, cbMercredi, cbJeudi, cbVendredi, cbSamedi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_availability);

        // 1. Initialisation des vues
        btnBack = findViewById(R.id.btnBack);
        btnSave = findViewById(R.id.btnSave);

        cbLundi = findViewById(R.id.cbLundi);
        cbMardi = findViewById(R.id.cbMardi);
        cbMercredi = findViewById(R.id.cbMercredi);
        cbJeudi = findViewById(R.id.cbJeudi);
        cbVendredi = findViewById(R.id.cbVendredi);
        cbSamedi = findViewById(R.id.cbSamedi);

        // 2. Bouton Retour
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Revenir en arrière
            }
        });

        // 3. Bouton Enregistrer
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Hna t9dri tverifiyi chmen nhar cocha tbib
                String message = "Disponibilités mises à jour : ";
                if (cbLundi.isChecked()) message += "Lun ";
                if (cbMardi.isChecked()) message += "Mar ";
                // ... etc

                Toast.makeText(DoctorAvailabilityActivity.this, "Enregistré avec succès !", Toast.LENGTH_SHORT).show();

                // Optionnel : Revenir à l'accueil médecin après sauvegarde
                finish();
            }
        });
    }
}