// Partie : Écran de confirmation du rendez-vous

package com.example.gestionrdv_medical;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class RdvConfirmationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rdv_confirmation);

        TextView tv = findViewById(R.id.tvResult);

        // Récupérer les données envoyées depuis PriseRdvActivity
        String date = getIntent().getStringExtra("date");
        String motif = getIntent().getStringExtra("motif");
        String creneau = getIntent().getStringExtra("creneau");

        // Construire le message à afficher
        String message = "Votre render-vous est pris :\n"
                + "Date : " + date + "\n"
                + "Heure : " + creneau + "\n"
                + "Motif : " + motif;

        tv.setText(message);
    }
}
