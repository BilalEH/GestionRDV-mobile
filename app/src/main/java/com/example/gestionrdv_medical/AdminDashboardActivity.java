package com.example.gestionrdv_medical;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class AdminDashboardActivity extends AppCompatActivity {

    private ImageView btnBack;
    private Button btnManageUsers, btnManageDoctors, btnAllRdv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);

        // 1. Initialisation des vues
        btnBack = findViewById(R.id.btnBack);
        btnManageUsers = findViewById(R.id.btnManageUsers);
        btnManageDoctors = findViewById(R.id.btnManageDoctors);
        btnAllRdv = findViewById(R.id.btnAllRdv);

        // 2. Bouton Retour (Revient au Login)
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // 3. Navigation vers "Gérer les Utilisateurs"
        btnManageUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Ce fichier existe déjà dans ton projet, on peut l'ouvrir !
                Intent intent = new Intent(AdminDashboardActivity.this, UserManagementActivity.class);
                startActivity(intent);
            }
        });

        // 4. Navigation vers "Gérer les Médecins" (Placeholder)
        btnManageDoctors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AdminDashboardActivity.this, "Gestion Médecins : Bientôt disponible", Toast.LENGTH_SHORT).show();
            }
        });

        // 5. Navigation vers "Tous les RDV" (Placeholder)
        btnAllRdv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AdminDashboardActivity.this, "Liste globale des RDV : Bientôt disponible", Toast.LENGTH_SHORT).show();
            }
        });
    }
}