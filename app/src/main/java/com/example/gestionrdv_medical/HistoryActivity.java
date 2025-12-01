package com.example.gestionrdv_medical;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

//import com.example.gestionrdv.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class HistoryActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TextView tvItemCount;

    private Spinner spFilterStatus, spFilterDate;
    private Button btnClear, btnPickDate;

    private List<PatientAppointment> appointmentList;
    private List<PatientAppointment> filteredList;

    private AppointmentAdapter adapter;

    private String selectedDate = null; // yyyy-MM-dd

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        // Views
        recyclerView = findViewById(R.id.recyclerView);
        tvItemCount = findViewById(R.id.tvItemCount);
        spFilterStatus = findViewById(R.id.spFilterStatus);
        spFilterDate = findViewById(R.id.spFilterDate);
        btnClear = findViewById(R.id.btnClear);
        btnPickDate = findViewById(R.id.btnPickDate);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Lists
        appointmentList = new ArrayList<>();
        filteredList = new ArrayList<>();

        loadAppointmentData();

        // Filters Adapters
        ArrayAdapter<String> statusAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                new String[]{"All", "Paid", "Pending", "Unpaid"}
        );
        spFilterStatus.setAdapter(statusAdapter);

        ArrayAdapter<String> dateAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                new String[]{"All", "Newest First", "Oldest First"}
        );
        spFilterDate.setAdapter(dateAdapter);

        // RecyclerView Adapter
        adapter = new AppointmentAdapter(filteredList);
        recyclerView.setAdapter(adapter);

        // Initial filter
        applyFilters();

        // Listeners
        spFilterStatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override public void onItemSelected(AdapterView<?> parent, View view, int position, long id) { applyFilters(); }
            @Override public void onNothingSelected(AdapterView<?> parent) {}
        });

        spFilterDate.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override public void onItemSelected(AdapterView<?> parent, View view, int position, long id) { applyFilters(); }
            @Override public void onNothingSelected(AdapterView<?> parent) {}
        });

        btnClear.setOnClickListener(v -> {
            spFilterStatus.setSelection(0);
            spFilterDate.setSelection(0);
            selectedDate = null;
            btnPickDate.setText("Select Date");
            applyFilters();
        });

        btnPickDate.setOnClickListener(v -> showDatePicker());
    }

    private void showDatePicker() {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dpd = new DatePickerDialog(this, (view, y, m, d) -> {
            selectedDate = String.format("%04d-%02d-%02d", y, m + 1, d);
            btnPickDate.setText("Date: " + selectedDate);
            applyFilters();
        }, year, month, day);

        dpd.show();
    }

    private void loadAppointmentData() {
        appointmentList.add(new PatientAppointment("John Doe", "2024-03-22", "General Consultation", 150.00, "Paid"));
        appointmentList.add(new PatientAppointment("Sarah Bell", "2024-02-10", "Dental Checkup", 200.00, "Pending"));
        appointmentList.add(new PatientAppointment("Mark Wilson", "2024-01-15", "Eye Examination", 120.50, "Paid"));
        appointmentList.add(new PatientAppointment("Emma Johnson", "2023-12-05", "Blood Test", 75.00, "Unpaid"));
        appointmentList.add(new PatientAppointment("Michael Brown", "2023-11-20", "Physical Therapy", 180.00, "Paid"));
    }

    private void applyFilters() {
        String selectedStatus = spFilterStatus.getSelectedItem().toString();
        String selectedSort = spFilterDate.getSelectedItem().toString();

        filteredList.clear();

        for (PatientAppointment a : appointmentList) {
            boolean matchStatus = selectedStatus.equals("All") ||
                    a.paymentStatus.equalsIgnoreCase(selectedStatus);

            boolean matchDate = true;
            if (selectedDate != null) {
                matchDate = a.date.equals(selectedDate);
            }

            if (matchStatus && matchDate) {
                filteredList.add(a);
            }
        }

        // Sort
        if (selectedSort.equals("Newest First")) {
            filteredList.sort((a, b) -> b.date.compareTo(a.date));
        } else if (selectedSort.equals("Oldest First")) {
            filteredList.sort((a, b) -> a.date.compareTo(b.date));
        }

        tvItemCount.setText(filteredList.size() + " appointments");
        adapter.notifyDataSetChanged();
    }

    // Model
    public static class PatientAppointment {
        String patientName, date, serviceType, paymentStatus;
        double amount;

        public PatientAppointment(String n, String d, String s, double a, String p) {
            patientName = n;
            date = d;
            serviceType = s;
            amount = a;
            paymentStatus = p;
        }
    }

    // Adapter
    public class AppointmentAdapter extends RecyclerView.Adapter<AppointmentAdapter.ViewHolder> {

        private final List<PatientAppointment> appointments;

        public AppointmentAdapter(List<PatientAppointment> list) {
            this.appointments = list;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull android.view.ViewGroup parent, int viewType) {
            android.view.View view = android.view.LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_history, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder h, int position) {
            PatientAppointment a = appointments.get(position);

            h.name.setText(a.patientName);
            h.date.setText(a.date);
            h.service.setText(a.serviceType);
            h.amount.setText("$" + String.format("%.2f", a.amount));
            h.avatar.setText(a.patientName.substring(0, 1).toUpperCase());
            h.status.setText(a.paymentStatus);

            switch (a.paymentStatus.toLowerCase()) {
                case "paid": h.status.setBackgroundColor(Color.parseColor("#4CAF50")); break;
                case "pending": h.status.setBackgroundColor(Color.parseColor("#FFC107")); break;
                case "unpaid": h.status.setBackgroundColor(Color.parseColor("#F44336")); break;
                default: h.status.setBackgroundColor(Color.GRAY);
            }
        }

        @Override
        public int getItemCount() {
            return appointments.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            TextView name, date, service, amount, status, avatar;

            public ViewHolder(@NonNull android.view.View itemView) {
                super(itemView);
                name = itemView.findViewById(R.id.tvName);
                date = itemView.findViewById(R.id.tvDate);
                service = itemView.findViewById(R.id.tvService);
                amount = itemView.findViewById(R.id.tvAmount);
                status = itemView.findViewById(R.id.tvPaymentStatus);
                avatar = itemView.findViewById(R.id.iconText);
            }
        }
    }
}
