package com.example.a818dentalclinic;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class DoctorLoginPage extends AppCompatActivity {

    Button backButton;
    Button loginButton;
    EditText userID;
    EditText pass;
    FirebaseFirestore fStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doctor_login_page);

        backButton = findViewById(R.id.doctor_back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DoctorLoginPage.this , MainActivity.class);
                startActivity(intent);
            }
        });

        loginButton = findViewById(R.id.doctor_login);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DoctorLoginPage.this , DoctorAppointments.class);
                startActivity(intent);
            }
        });

        userID = findViewById(R.id.doctor_name);
        pass = findViewById(R.id.doctor_pass);
        fStore = FirebaseFirestore.getInstance();
    }
}
