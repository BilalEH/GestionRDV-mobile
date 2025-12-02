package com.example.gestionrdv_medical;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView; // Pour l'image retour
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    private EditText etFirstName, etLastName, etPhone, etEmail, etPassword;
    private Button btnRegister;
    private ImageView btnBack; // Déclaration du bouton retour

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // 1. Initialisation des vues
        etFirstName = findViewById(R.id.etFirstName);
        etLastName = findViewById(R.id.etLastName);
        etPhone = findViewById(R.id.etPhone);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnRegister = findViewById(R.id.btnRegister);
        btnBack = findViewById(R.id.btnBack);

        // 2. Gestion du Bouton Retour
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Revenir à la page de Login
            }
        });

        // 3. Gestion de l'Inscription
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fn = etFirstName.getText().toString().trim();
                String ln = etLastName.getText().toString().trim();
                String phone = etPhone.getText().toString().trim();
                String email = etEmail.getText().toString().trim();
                String pass = etPassword.getText().toString().trim();

                // Vérification des champs vides
                if (TextUtils.isEmpty(fn) || TextUtils.isEmpty(ln) ||
                        TextUtils.isEmpty(phone) || TextUtils.isEmpty(email) ||
                        TextUtils.isEmpty(pass)) {

                    // Note: Assure-toi que ces strings existent dans strings.xml, sinon utilise du texte direct
                    Toast.makeText(RegisterActivity.this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Simulation de sauvegarde
                Toast.makeText(RegisterActivity.this, "Inscription réussie !", Toast.LENGTH_SHORT).show();

                // Redirection vers l'espace patient avec le nom
                Intent intent = new Intent(RegisterActivity.this, PatientHomeActivity.class);
                intent.putExtra("patientName", fn + " " + ln);
                startActivity(intent);
                finish(); // Ferme l'activité d'inscription pour ne pas y revenir avec "Retour"
            }
        });
    }
}