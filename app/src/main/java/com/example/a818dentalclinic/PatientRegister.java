package com.example.a818dentalclinic;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class PatientRegister extends AppCompatActivity {

    public static final String TAG = "TAG";
    EditText mFirstName, mLastName, mUser, mEmail, mPass;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    Button mRegister;
    ProgressBar progressBar;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.patient_register);

        mFirstName = findViewById(R.id.patient_firstName);
        mLastName = findViewById(R.id.patient_LastName);
        mUser = findViewById(R.id.patient_userName);
        mEmail = findViewById(R.id.patient_email);
        mPass = findViewById(R.id.patient_password);
        mRegister = findViewById(R.id.patient_register);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        progressBar = findViewById(R.id.progressBar);

        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String firstName = mFirstName.getText().toString();
                final String lastName = mLastName.getText().toString();
                final String email = mEmail.getText().toString().trim();
                final String userName = mUser.getText().toString();
                final String passWord = mPass.getText().toString();

                if (TextUtils.isEmpty(firstName)) {
                    mFirstName.setError("First Name is required!");
                    return;
                }

                if (TextUtils.isEmpty(lastName)) {
                    mLastName.setError("Last Name is required!");
                    return;
                }

                if (TextUtils.isEmpty(userName)) {
                    mUser.setError("username is required!");
                    return;
                }

                if (TextUtils.isEmpty(passWord)) {
                    mPass.setError("password is required!");
                    return;
                }

                if (TextUtils.isEmpty(email)) {
                    mEmail.setError("Email is required!");
                    return;
                }

                if (passWord.length() < 6) {
                    mPass.setError("Password must be greater than 5 characters!");
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                fAuth.createUserWithEmailAndPassword(email, passWord).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(PatientRegister.this, "User Created", Toast.LENGTH_SHORT).show();
                            userID = fAuth.getCurrentUser().getUid();
                            DocumentReference documentReference = fStore.collection("users").document(userID);
                            Map<String, Object> user = new HashMap<>();
                            user.put("firstName", firstName);
                            user.put("lastName", lastName);
                            user.put("email", email);
                            user.put("userName", userName);
                            user.put("passWord", passWord);
                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.d(TAG, "onSuccess: user Profile is created for " + userID);
                                    onRegistered();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.d(TAG, "onFailure: " + e.toString());
                                }
                            });


                        } else {
                            Toast.makeText(PatientRegister.this, "Error Occured: " + Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });

            }
        });
    }
    private void onRegistered() {
        Intent intent = new Intent(this, PatientLoginPage.class);
        startActivity(intent);
    }
}