package com.example.gestionrdv_medical;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

//import com.example.gestionrdv.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class UserManagementActivity extends AppCompatActivity {

    private RecyclerView rvUsers;
    private TextView tvUserCount;
    private EditText etSearch;
    private Button btnPickDate, btnClearFilters;

    private List<User> userList;
    private List<User> filteredList;
    private UserAdapter adapter;
    private String selectedDate = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_management);

        rvUsers = findViewById(R.id.rvUsers);
        tvUserCount = findViewById(R.id.tvUserCount);
        etSearch = findViewById(R.id.etSearch);
        btnPickDate = findViewById(R.id.btnPickDate);
        btnClearFilters = findViewById(R.id.btnClearFilters);

        rvUsers.setLayoutManager(new LinearLayoutManager(this));

        userList = new ArrayList<>();
        loadDummyUsers();

        filteredList = new ArrayList<>(userList);
        adapter = new UserAdapter(filteredList);
        rvUsers.setAdapter(adapter);

        updateUserCount();

        // Search functionality
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                applyFilters();
            }

            @Override
            public void afterTextChanged(Editable s) { }
        });

        // Date picker
        btnPickDate.setOnClickListener(v -> {
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(UserManagementActivity.this,
                    (view, y, m, d) -> {
                        selectedDate = String.format("%04d-%02d-%02d", y, m + 1, d);
                        applyFilters();
                    }, year, month, day);
            datePickerDialog.show();
        });

        // Clear filters
        btnClearFilters.setOnClickListener(v -> {
            etSearch.setText("");
            selectedDate = "";
            applyFilters();
        });
    }

    private void applyFilters() {
        String query = etSearch.getText().toString().toLowerCase();
        filteredList.clear();

        for (User u : userList) {
            boolean matchesSearch = u.name.toLowerCase().contains(query) ||
                    u.email.toLowerCase().contains(query) ||
                    u.phone.toLowerCase().contains(query);

            boolean matchesDate = selectedDate.isEmpty() || u.signupDate.equals(selectedDate);

            if (matchesSearch && matchesDate) {
                filteredList.add(u);
            }
        }

        adapter.notifyDataSetChanged();
        updateUserCount();
    }

    private void updateUserCount() {
        tvUserCount.setText(filteredList.size() + " users");
    }

    // Load some dummy users
    private void loadDummyUsers() {
        userList.add(new User("John Doe", "johndoe@example.com", "+212600000000", "2024-01-01", 5));
        userList.add(new User("Sarah Bell", "sarahbell@example.com", "+212611111111", "2024-02-05", 3));
        userList.add(new User("Mark Wilson", "markwilson@example.com", "+212622222222", "2024-03-10", 7));
        userList.add(new User("Emma Johnson", "emmajohnson@example.com", "+212633333333", "2024-04-12", 2));
        userList.add(new User("Michael Brown", "michaelbrown@example.com", "+212644444444", "2024-05-20", 4));
    }

    // User model class
    public static class User {
        String name, email, phone, signupDate;
        int appointmentsCount;

        public User(String n, String e, String ph, String date, int count) {
            name = n;
            email = e;
            phone = ph;
            signupDate = date;
            appointmentsCount = count;
        }
    }

    // RecyclerView Adapter (same as before, handles expand/collapse)
    public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
        private final List<User> users;

        public UserAdapter(List<User> list) { this.users = list; }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull android.view.ViewGroup parent, int viewType) {
            android.view.View view = android.view.LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_user, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder h, int position) {
            User u = users.get(position);
            h.name.setText(u.name);
            h.email.setText(u.email);
            h.avatar.setText(u.name.substring(0, 1).toUpperCase());

            // Fill expandable info
            h.phone.setText("Phone: " + u.phone);
            h.signupDate.setText("Signed up: " + u.signupDate);
            h.appointmentsCount.setText("Appointments: " + u.appointmentsCount);

            // Expand/collapse logic
            h.btnExpand.setOnClickListener(v -> {
                if (h.expandableLayout.getVisibility() == View.GONE) {
                    h.expandableLayout.setVisibility(View.VISIBLE);
                    h.btnExpand.setRotation(45); // plus → minus
                } else {
                    h.expandableLayout.setVisibility(View.GONE);
                    h.btnExpand.setRotation(0); // reset
                }
            });
        }

        @Override
        public int getItemCount() { return users.size(); }

        class ViewHolder extends RecyclerView.ViewHolder {
            TextView name, email, avatar, phone, signupDate, appointmentsCount;
            ImageView btnExpand;
            LinearLayout expandableLayout;

            public ViewHolder(@NonNull android.view.View itemView) {
                super(itemView);
                name = itemView.findViewById(R.id.tvUserName);
                email = itemView.findViewById(R.id.tvUserEmail);
                avatar = itemView.findViewById(R.id.tvUserAvatar);
                phone = itemView.findViewById(R.id.tvPhone);
                signupDate = itemView.findViewById(R.id.tvSignupDate);
                appointmentsCount = itemView.findViewById(R.id.tvAppointmentsCount);
                btnExpand = itemView.findViewById(R.id.btnExpand);
                expandableLayout = itemView.findViewById(R.id.expandableLayout);
            }
        }
    }
}