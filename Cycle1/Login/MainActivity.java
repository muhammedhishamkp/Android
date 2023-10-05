package com.example.sjcet.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private EditText editName;
    private EditText editPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editName = findViewById(R.id.editName);
        editPass = findViewById(R.id.editPass);

    }


    public void login(View view) {
        String name = editName.getText().toString();
        String password = editPass.getText().toString();

        if (name.equals("hisham") && password.equals("hisham123"))
        {
            Toast.makeText(this,"Login Successful",Toast.LENGTH_SHORT).show();

        }
        else {
            Toast.makeText(this,"Invalied credentials",Toast.LENGTH_SHORT).show();
        }
    }
}
