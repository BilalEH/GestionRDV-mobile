package com.example.gestionrdv_medical;


import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class DoctorAvailabilityActivity extends AppCompatActivity {

    private CheckBox[] dayCheckBoxes;
    private LinearLayout[] timeSlotContainers;
    private Button saveButton;
    private Button backButton;
    private TextView statsText;

    // Noms des jours de la semaine
    private String[] days = {"Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi", "Samedi", "Dimanche"};

    // Options de créneaux horaires
    private String[] timeSlots = {
            "08:00-09:00", "09:00-10:00", "10:00-11:00", "11:00-12:00",
            "12:00-13:00", "13:00-14:00", "14:00-15:00", "15:00-16:00",
            "16:00-17:00", "17:00-18:00", "18:00-19:00"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_availability);

        // Initialisation des vues
        dayCheckBoxes = new CheckBox[7];
        timeSlotContainers = new LinearLayout[7];

        saveButton = findViewById(R.id.saveButton);
        backButton = findViewById(R.id.backButton);
        statsText = findViewById(R.id.statsText);

        // Initialisation des cases à cocher pour les jours
        for (int i = 0; i < 7; i++) {
            int checkBoxId = getResources().getIdentifier("day" + i + "CheckBox", "id", getPackageName());
            int containerId = getResources().getIdentifier("timeSlotContainer" + i, "id", getPackageName());

            dayCheckBoxes[i] = findViewById(checkBoxId);
            timeSlotContainers[i] = findViewById(containerId);

            final int index = i;
            dayCheckBoxes[i].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    timeSlotContainers[index].setVisibility(isChecked ? View.VISIBLE : View.GONE);
                    updateStats();
                }
            });
        }

        // Configuration des Spinners pour chaque jour
        setupTimeSlotSpinners();

        // Gestion des boutons
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveAvailability();
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // Configuration initiale
        setupDefaultAvailability();
        updateStats();
    }

    private void setupTimeSlotSpinners() {
        for (int i = 0; i < 7; i++) {
            int spinnerId = getResources().getIdentifier("timeSlotSpinner" + i, "id", getPackageName());
            Spinner spinner = findViewById(spinnerId);

            if (spinner != null) {
                ArrayAdapter<String> adapter = new ArrayAdapter<>(
                        this,
                        android.R.layout.simple_spinner_item,
                        timeSlots
                );
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(adapter);
            }
        }
    }

    private void setupDefaultAvailability() {
        // Configuration par défaut : Lundi au Vendredi
        for (int i = 0; i < 5; i++) {
            dayCheckBoxes[i].setChecked(true);
        }
        // Samedi et Dimanche non cochés
        dayCheckBoxes[5].setChecked(false);
        dayCheckBoxes[6].setChecked(false);
    }

    private void updateStats() {
        int workDays = 0;
        for (CheckBox checkBox : dayCheckBoxes) {
            if (checkBox.isChecked()) {
                workDays++;
            }
        }

        statsText.setText("Jours de travail: " + workDays + "/7");
    }

    private void saveAvailability() {
        StringBuilder availability = new StringBuilder("Disponibilités sauvegardées:\n");

        for (int i = 0; i < 7; i++) {
            if (dayCheckBoxes[i].isChecked()) {
                availability.append(days[i]).append(": Oui\n");

                int spinnerId = getResources().getIdentifier("timeSlotSpinner" + i, "id", getPackageName());
                Spinner spinner = findViewById(spinnerId);
                if (spinner != null) {
                    availability.append("  Créneau: ").append(spinner.getSelectedItem()).append("\n");
                }
            } else {
                availability.append(days[i]).append(": Non\n");
            }
        }

        Toast.makeText(this, "Disponibilités sauvegardées avec succès!", Toast.LENGTH_SHORT).show();

        // Optionnel: afficher les détails dans une boîte de dialogue
        // new AlertDialog.Builder(this).setMessage(availability.toString()).show();
    }
}