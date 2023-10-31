package com.example.sjcet.registration;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.SharedPreferences;
import android.widget.TextView;

public class DisplayDetailsActivity extends AppCompatActivity {

    TextView textViewDetails;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_details);

        textViewDetails = findViewById(R.id.textViewDetails);

        // Retrieve registration details from SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        String username = sharedPreferences.getString("username", "");
        String email = sharedPreferences.getString("email", "");

        // Display registration details
        String details = "Username: " + username + "\nEmail: " + email;
        textViewDetails.setText(details);
    }
}
