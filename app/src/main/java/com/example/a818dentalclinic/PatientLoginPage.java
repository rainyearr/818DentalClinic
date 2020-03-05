package com.example.a818dentalclinic;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class PatientLoginPage extends AppCompatActivity {

    EditText email, mPassword;
    Button backButton;
    Button loginButton;
    ImageButton registerButton;
    ProgressBar progressBar;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patient_login_page);

        backButton = findViewById(R.id.patient_back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PatientLoginPage.this, MainActivity.class);
                startActivity(intent);
            }
        });

        registerButton = findViewById(R.id.patient_create);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PatientLoginPage.this, PatientRegister.class);
                startActivity(intent);
            }
        });

        email = findViewById(R.id.patient_name);
        mPassword = findViewById(R.id.patient_pass);
        progressBar = findViewById(R.id.progressBar_patientLogin);
        fAuth = FirebaseAuth.getInstance();
        loginButton = findViewById(R.id.patient_login);

        loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String userName = email.getText().toString().trim();
                String password = mPassword.getText().toString().trim();

                if (TextUtils.isEmpty(userName)) {
                    email.setError("Username is Required!");
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    mPassword.setError("Password is Required!");
                    return;
                }

                if (password.length() < 6) {
                    mPassword.setError("Password must be greater than 5 characters!");
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                fAuth.signInWithEmailAndPassword(userName, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(PatientLoginPage.this, "Logged in Successfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), DoctorAppointments.class));
                        } else {
                            Toast.makeText(PatientLoginPage.this, "Error ! " + Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });
            }
        });
    }

}