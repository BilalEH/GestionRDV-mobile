package com.example.gestionrdv_medical;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.gestionrdv_medical.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Configuration du ViewBinding
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Clic sur le bouton "Se connecter"
        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifierLogin();
            }
        });

        // Clic sur le lien "Créer un compte"
        binding.tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirection vers la page d'inscription
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    private void verifierLogin() {
        // Récupérer ce que l'utilisateur a écrit
        String email = binding.etEmail.getText().toString().trim();
        String password = binding.etPassword.getText().toString().trim();

        // 1. Vérifier si les champs sont vides
        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
            return;
        }

        // 2. Simulation des rôles (Login Statique)

        // SCÉNARIO 1 : PATIENT
        if (email.equals("pat@test.com") && password.equals("1234")) {
            Toast.makeText(this, "Bienvenue Patient !", Toast.LENGTH_SHORT).show();
          Intent intent = new Intent(MainActivity.this, PatientHomeActivity.class);
          startActivity(intent);
          finish();
        }

        // SCÉNARIO 2 : MÉDECIN
        else if (email.equals("doc@test.com") && password.equals("1234")) {
            Toast.makeText(this, "Bienvenue Docteur !", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this, DoctorHomeActivity.class);
            startActivity(intent);
            finish();
        }

        // SCÉNARIO 3 : ADMIN
        else if (email.equals("admin@test.com") && password.equals("1234")) {
            Toast.makeText(this, "Bienvenue Administrateur !", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this, AdminDashboardActivity.class);
            startActivity(intent);
            finish();
        }

        // SCÉNARIO 4 : FAUX COMPTE
        else {
            binding.etPassword.setError("Email ou mot de passe incorrect");
            Toast.makeText(this, "Erreur de connexion", Toast.LENGTH_SHORT).show();
        }
    }
}