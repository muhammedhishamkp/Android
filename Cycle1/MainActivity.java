package com.example.sjcet.student;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    private EditText editTextName, editTextEmail, editTextPhone;
    private Button buttonRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextName = findViewById(R.id.editTextName);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPhone = findViewById(R.id.editTextPhone);
        buttonRegister = findViewById(R.id.buttonRegister);

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validate()) {
                    // Registration logic here (e.g., save data to a database)
                    Toast.makeText(MainActivity.this, "Registration successful", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean validate() {
        String name = editTextName.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();
        String phone = editTextPhone.getText().toString().trim();

        if (name.isEmpty()) {
            editTextName.setError("Name is required");
            return false;
        }

        if (email.isEmpty() || !isValidEmail(email)) {
            editTextEmail.setError("Enter a valid email address");
            return false;
        }

        if (phone.isEmpty() || !isValidPhone(phone)) {
            editTextPhone.setError("Enter a valid phone number");
            return false;
        }

        return true;
    }

    private boolean isValidEmail(String email) {
        // Implement email validation logic (e.g., using regex)
        return Pattern.compile("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+").matcher(email).matches();
    }

    private boolean isValidPhone(String phone) {
        // Implement phone number validation logic (e.g., using regex)
        return Pattern.compile("\\d{10}").matcher(phone).matches();
    }
}