package com.example.a818dentalclinic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class PatientLoginPage extends AppCompatActivity {

    Button backButton;
    Button loginButton;
    ImageButton registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patient_login_page);

        backButton = findViewById(R.id.patient_back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PatientLoginPage.this , MainActivity.class);
                startActivity(intent);
            }
        });

        loginButton = findViewById(R.id.patient_login);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PatientLoginPage.this , DoctorAppointments.class);
                startActivity(intent);
            }
        });

        registerButton = findViewById(R.id.patient_create);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PatientLoginPage.this , PatientRegister.class);
                startActivity(intent);
            }
        });
    }

}