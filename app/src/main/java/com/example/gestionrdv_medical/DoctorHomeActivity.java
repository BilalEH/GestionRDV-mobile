package com.example.gestionrdv_medical;


import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class DoctorHomeActivity extends AppCompatActivity {

    private TextView welcomeText;
    private ListView patientsListView;
    private TextView todayDateText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_home);

        // Initialisation des vues
        welcomeText = findViewById(R.id.welcomeText);
        patientsListView = findViewById(R.id.patientsListView);
        todayDateText = findViewById(R.id.todayDateText);

        // Message de bienvenue
        welcomeText.setText("Bonjour Dr.");

        // Date du jour
        todayDateText.setText("Patients du jour: " + java.text.DateFormat.getDateInstance().format(new java.util.Date()));

        // Données simulées des patients
        String[] patients = {
                "10:00 - Patient: Dupont Jean - Consultation générale",
                "11:00 - Patient: Marie Claire - Suivi médical",
                "14:00 - Patient: Ahmed Mohamed - Consultation urgente",
                "15:30 - Patient: Sophie Martin - Contrôle annuel",
                "16:00 - Patient: Robert Bernard - Prise de sang"
        };

        // Adapter pour la ListView
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                patients
        );

        patientsListView.setAdapter(adapter);

        // Gestion du clic sur un patient
        patientsListView.setOnItemClickListener((parent, view, position, id) -> {
            String selectedPatient = patients[position];
            Toast.makeText(DoctorHomeActivity.this,
                    "Patient sélectionné: " + selectedPatient,
                    Toast.LENGTH_SHORT).show();
        });
    }
}