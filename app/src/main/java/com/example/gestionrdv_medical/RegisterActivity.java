package com.example.gestionrdv_medical;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    private EditText etFirstName, etLastName, etPhone, etEmail, etPassword;
    private Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Initialisation des vues
        etFirstName = findViewById(R.id.etFirstName);
        etLastName = findViewById(R.id.etLastName);
        etPhone = findViewById(R.id.etPhone);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnRegister = findViewById(R.id.btnRegister);

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

                    Toast.makeText(RegisterActivity.this, getString(R.string.error_fill_all), Toast.LENGTH_SHORT).show();
                    return;
                }

                // TODO: Sauvegarde en base de données (Backend)
                Toast.makeText(RegisterActivity.this, getString(R.string.register_success), Toast.LENGTH_SHORT).show();

                // Redirection vers l'espace patient avec le nom
                Intent intent = new Intent(RegisterActivity.this, PatientHomeActivity.class);
                intent.putExtra("patientName", fn + " " + ln);
                startActivity(intent);
                finish();
            }
        });
    }
}