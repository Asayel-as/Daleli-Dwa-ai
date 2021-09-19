package com.example.dalelidwaai;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgetPassword extends AppCompatActivity {

    EditText ForgotEmailText;
    Button SendEmailButton;

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forget_password);

        ForgotEmailText = findViewById(R.id.ForgotEmailText);
        SendEmailButton = findViewById(R.id.SendEmailButton);

       auth = FirebaseAuth.getInstance();

        SendEmailButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
              auth.sendPasswordResetEmail(ForgotEmailText.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(ForgetPassword.this, "Reset email was sent.", Toast.LENGTH_LONG).show();
                        }
                        else{
                            Toast.makeText(ForgetPassword.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
              });
            }
        });
    }
}