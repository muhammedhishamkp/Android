package com.example.sjcet.myapplication;
import android.support.v7.app.AppCompatActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private DatabaseHelper db;
    private EditText editTextName, editTextAge, editTextMark, deleteIdEditText,deleteIdUpdateText;
    private TextView textViewData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new DatabaseHelper(this);
        editTextName = findViewById(R.id.editTextName);
        editTextAge = findViewById(R.id.editTextAge);
        editTextMark = findViewById(R.id.editTextMark);
        textViewData = findViewById(R.id.textViewData);
        deleteIdEditText = findViewById(R.id.deleteIdEditText);
        deleteIdUpdateText = findViewById(R.id.deleteIdUpdateText);
        Button buttonInsert = findViewById(R.id.buttonInsert);
        Button buttonSelect = findViewById(R.id.buttonSelect);
        Button buttonDelete = findViewById(R.id.buttonDelete);
        Button buttonUpdate = findViewById(R.id.buttonUpdate);
        Button buttonGetDetailsToUpdate = findViewById(R.id.buttonGetDetailsToUpdate);

        buttonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editTextName.getText().toString();
                String ageString = editTextAge.getText().toString().trim();
                String markString = editTextMark.getText().toString().trim();

                if (name.isEmpty() || ageString.isEmpty() || markString.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                    return; // Stop further execution
                }
                int age = Integer.parseInt(ageString);
                int mark = Integer.parseInt(markString);

                boolean insertData = db.insertUser(name, age, mark);
                if (insertData) {
                    Toast.makeText(MainActivity.this, "User Inserted Successfully", Toast.LENGTH_SHORT).show();
                    editTextName.setText("");
                    editTextAge.setText("");
                    editTextMark.setText("");


                } else {
                    Toast.makeText(MainActivity.this, "Failed to Insert User", Toast.LENGTH_SHORT).show();
                }
            }
        });

        buttonSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayData();
            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteDataById();
            }
        });




        buttonGetDetailsToUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String idString = deleteIdUpdateText.getText().toString();

                if (!idString.isEmpty()) {
                    int idToUpdate = Integer.parseInt(idString);
                    displayDetailsForUpdate(idToUpdate);
                } else {
                    Toast.makeText(MainActivity.this, "Please enter an ID", Toast.LENGTH_SHORT).show();
                }
            }
        });
        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Retrieve values from EditText fields
                String name = editTextName.getText().toString();
                int age = Integer.parseInt(editTextAge.getText().toString());
                int mark = Integer.parseInt(editTextMark.getText().toString());

                // Perform update
                boolean updateData = db.updateUser(name, age, mark);
                if (updateData) {
                    Toast.makeText(MainActivity.this, "User Updated Successfully", Toast.LENGTH_SHORT).show();

                    // Clear fields after update (optional)
                    editTextName.setText("");
                    editTextAge.setText("");
                    editTextMark.setText("");

                    // Refresh displayed data
                    displayData();
                } else {
                    Toast.makeText(MainActivity.this, "Failed to Update User", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


    private void deleteDataById() {
        String idString = deleteIdEditText.getText().toString();

        if (!idString.isEmpty()) {
            int idToDelete = Integer.parseInt(idString);

            boolean deleted = db.deleteUser(idToDelete);
            if (deleted) {
                Toast.makeText(MainActivity.this, "User Deleted Successfully", Toast.LENGTH_SHORT).show();
                displayData(); // Refresh the displayed data after deletion
            } else {
                Toast.makeText(MainActivity.this, "Failed to Delete User", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(MainActivity.this, "Please enter an ID", Toast.LENGTH_SHORT).show();
        }
    }


    private void displayDetailsForUpdate(int idToUpdate) {
        Cursor cursor = db.getUserById(idToUpdate); // Assuming method to retrieve a user by ID exists

        if (cursor != null && cursor.moveToFirst()) {
            String name = cursor.getString(cursor.getColumnIndex("NAME"));
            int age = cursor.getInt(cursor.getColumnIndex("AGE"));
            int mark = cursor.getInt(cursor.getColumnIndex("MARK"));

            // Display details in EditText fields
            editTextName.setText(name);
            editTextAge.setText(String.valueOf(age));
            editTextMark.setText(String.valueOf(mark));

            cursor.close();
        } else {
            // ID not found or cursor is empty
            Toast.makeText(MainActivity.this, "User ID not found", Toast.LENGTH_SHORT).show();
        }
    }

    private void displayData() {
        Cursor cursor = db.getAllUsers();
        if (cursor.getCount() == 0) {
            textViewData.setText("No users found");
        } else {
            StringBuilder data = new StringBuilder();
            while (cursor.moveToNext()) {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                int age = cursor.getInt(2);
                int mark = cursor.getInt(3);
                data.append("ID: ").append(id)
                        .append(", Name: ").append(name)
                        .append(", Age: ").append(age)
                        .append(", Mark: ").append(mark)
                        .append("\n");
            }
            textViewData.setText(data.toString());
        }
    }}
