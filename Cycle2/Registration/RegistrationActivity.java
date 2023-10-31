package com.example.sjcet.registration;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.EditText;

public class RegistrationActivity extends AppCompatActivity {

    EditText editTextUsername, editTextEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        editTextUsername = findViewById(R.id.editTextUsername);
        editTextEmail = findViewById(R.id.editTextEmail);

    }


    public void register(View view) {
        // Get user input
        String username = editTextUsername.getText().toString();
        String email = editTextEmail.getText().toString();

        // Store registration details in SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("username", username);
        editor.putString("email", email);
        editor.apply();

        // Launch another activity using an Intent (for demonstration purposes)
        Intent intent = new Intent(this, DisplayDetailsActivity.class);
        startActivity(intent);
    }
}
