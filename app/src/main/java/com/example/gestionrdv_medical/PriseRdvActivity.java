package com.example.gestionrdv_medical;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Spinner;

public class PriseRdvActivity extends AppCompatActivity {

    private DatePicker datePicker;
    private Spinner spMotif;
    private Spinner spCreneau;
    private Button btnConfirmRdv;
    private ImageView btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prise_rdv);

        // 1. Initialisation avec les BONS IDs du XML
        datePicker = findViewById(R.id.datePicker);
        spMotif = findViewById(R.id.spMotif);
        spCreneau = findViewById(R.id.spCreneau);
        btnConfirmRdv = findViewById(R.id.btnConfirmRdv);
        btnBack = findViewById(R.id.btnBack);

        // 2. Remplir les Spinners
        String[] motifs = {"Consultation", "Urgence", "Suivi"};
        ArrayAdapter<String> motifAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, motifs);
        spMotif.setAdapter(motifAdapter);

        String[] creneaux = {"09:00", "10:00", "11:00", "14:00", "15:00"};
        ArrayAdapter<String> creneauAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, creneaux);
        spCreneau.setAdapter(creneauAdapter);

        // 3. Bouton Retour
        if (btnBack != null) {
            btnBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }

        // 4. Clic sur Confirmer
        btnConfirmRdv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Récupérer la date
                int day = datePicker.getDayOfMonth();
                int month = datePicker.getMonth() + 1;
                int year = datePicker.getYear();
                String dateStr = day + "/" + month + "/" + year;

                // Récupérer les choix
                String motifStr = spMotif.getSelectedItem().toString();
                String creneauStr = spCreneau.getSelectedItem().toString();

                // Ouvrir la page de confirmation
                Intent intent = new Intent(PriseRdvActivity.this, RdvConfirmationActivity.class);
                intent.putExtra("date", dateStr);
                intent.putExtra("motif", motifStr);
                intent.putExtra("creneau", creneauStr);

                startActivity(intent);
            }
        });
    }
}