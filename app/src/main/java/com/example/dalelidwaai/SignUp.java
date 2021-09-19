package com.example.dalelidwaai;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SignUp extends AppCompatActivity {

    public static final String TAG = "TAG";

    EditText NameText, EmailText, PasswordText;
    Button SignUpButton;
    Spinner AgeSpinner, GenderSpinner;

    String userID;

    FirebaseAuth auth;
    FirebaseFirestore store;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);

        NameText = findViewById(R.id.NameText);
        EmailText = findViewById(R.id.EmailText);
        PasswordText = findViewById(R.id.PasswordText);
        AgeSpinner = findViewById(R.id.AgeSpinner);
        GenderSpinner = findViewById(R.id.GenderSpinner);
        SignUpButton = findViewById(R.id.SignUpButton);

        auth = FirebaseAuth.getInstance();
        store = FirebaseFirestore.getInstance();

        SignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String name = NameText.getText().toString().trim();
                final String email = EmailText.getText().toString().trim();
                final String password = PasswordText.getText().toString().trim();
                final String age = AgeSpinner.getSelectedItem().toString().trim();
                final String gender = GenderSpinner.getSelectedItem().toString().trim();

                if (TextUtils.isEmpty(name)) {
                    NameText.setError("Name is required.");
                    return;
                }
                if (TextUtils.isEmpty(email)) {
                    EmailText.setError("Email is required.");
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    PasswordText.setError("Password is required.");
                    return;
                }
                if (password.length() < 6) {
                    PasswordText.setError("Password must be >= 6 characters.");
                    return;
                }

                // register the user in firebase
                auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            userID = auth.getCurrentUser().getUid();
                            DocumentReference documentReference = store.collection("Members").document(userID);
                            Map<String, Object> member = new HashMap<>();
                            member.put("name", name);
                            member.put("email", email);
                            member.put("password", password);
                            member.put("age", age);
                            member.put("gender", gender);

                            documentReference.set(member).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(SignUp.this, "User created.", Toast.LENGTH_LONG).show();

                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.d(TAG, "onFailure: " + e.toString());
                                }
                            });

                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        } else {
                            Toast.makeText(SignUp.this, "Error!" + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });

        Spinner AgeSpinner = findViewById(R.id.AgeSpinner);
        List AgeList = new ArrayList<Integer>();
        for (int i = 1; i <= 100; i++) {
            AgeList.add(Integer.toString(i));
        }
        ArrayAdapter<String> AgeArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, AgeList);
        AgeArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        AgeSpinner.setAdapter(AgeArrayAdapter);

        Spinner GenderSpinner = findViewById(R.id.GenderSpinner);
        ArrayList<String> GenderArrayList = new ArrayList<>();
        GenderArrayList.add("Male");
        GenderArrayList.add("Female");
        ArrayAdapter<String> GenderArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, GenderArrayList);
        GenderArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        GenderSpinner.setAdapter(GenderArrayAdapter);
    }
}