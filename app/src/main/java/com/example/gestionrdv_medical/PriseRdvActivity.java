// Partie : Prise de rendez-vous (date + motif + créneau)
// TP développement mobile EMSI

package com.example.gestionrdv_medical;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;

public class PriseRdvActivity extends AppCompatActivity {

    // 1) Déclaration des widgets (liés à ton XML)
    private DatePicker datePicker;
    private Spinner spinnerMotif;
    private Spinner spinnerCreneau;
    private Button btnConfirmer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 2) Dire à l’Activity quel XML utiliser comme écran
        setContentView(R.layout.activity_prise_rdv);

        // 3) Relier le Java au XML (findViewById = "je trouve l’objet dans le layout")
        datePicker = findViewById(R.id.datePicker);
        spinnerMotif = findViewById(R.id.spinnerMotif);
        spinnerCreneau = findViewById(R.id.spinnerCreneau);
        btnConfirmer = findViewById(R.id.btnConfirmer);

        // 4) Remplir le Spinner des motifs
        ArrayAdapter<String> motifAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                new String[]{"Consultation", "Urgence", "Suivi"}
        );
        motifAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMotif.setAdapter(motifAdapter);

        // 5) Remplir le Spinner des créneaux
        ArrayAdapter<String> creneauAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                new String[]{"09:00", "10:00", "11:00", "14:00", "15:00"}
        );
        creneauAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCreneau.setAdapter(creneauAdapter);

        // 6) Quand on clique sur "Confirmer", on récupère les choix et on va à l’écran suivant
        btnConfirmer.setOnClickListener(v -> {
            // Récupérer la date choisie
            int day = datePicker.getDayOfMonth();
            int month = datePicker.getMonth() + 1; // mois commence à 0
            int year = datePicker.getYear();
            String date = day + "/" + month + "/" + year;

            // Récupérer les valeurs des spinners
            String motif = spinnerMotif.getSelectedItem().toString();
            String creneau = spinnerCreneau.getSelectedItem().toString();

            // Préparer le passage vers l’Activity de confirmation
            Intent intent = new Intent(PriseRdvActivity.this, RdvConfirmationActivity.class);
            intent.putExtra("date", date);
            intent.putExtra("motif", motif);
            intent.putExtra("creneau", creneau);
            startActivity(intent); // on ouvre l’autre écran
        });
    }
}
