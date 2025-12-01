package com.example.gestionrdv_medical;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

//import com.example.gestionrdv.R;


public class AdminDashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_admin_dashboard);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        /*
        MaterialCardView cardHistory = findViewById(R.id.cardHistory);
        cardHistory.setOnClickListener(v -> startActivity(new Intent(this, HistoryActivity.class)));

        MaterialCardView cardUsers = findViewById(R.id.cardUsers);
        cardUsers.setOnClickListener(v -> startActivity(new Intent(this, UserManagementActivity.class)));

        MaterialCardView cardStats = findViewById(R.id.cardStats);
        cardStats.setOnClickListener(v -> startActivity(new Intent(this, StatsActivity.class)));
        */
    }


}