package com.example.a818dentalclinic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button button_doctor;
    Button button_patient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button_doctor = findViewById(R.id.welcome_doctor_button);
        button_doctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this , DoctorLoginPage.class);
                startActivity(intent);
            }
        });

        button_patient = findViewById(R.id.welcome_patient_button);
        button_patient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PatientLoginPage.class);
                startActivity(intent);
            }
        });
    }
}