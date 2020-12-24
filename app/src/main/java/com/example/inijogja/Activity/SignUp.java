package com.example.inijogja.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.inijogja.Fragment.LoginFragment;
import com.example.inijogja.R;
import com.example.inijogja.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity{
    private FirebaseAuth mAuth;
    private EditText etName, etEmail, etNohp, etPassword, etRepassword;
    private ProgressBar progressBar;
    private Button btnSignup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mAuth = FirebaseAuth.getInstance();

        etName = findViewById(R.id.et_name);
        etEmail = findViewById(R.id.et_email);
        etNohp = findViewById(R.id.et_nohp);
        etPassword = findViewById(R.id.et_password);
        etRepassword = findViewById(R.id.et_repassword);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        btnSignup = (Button) findViewById(R.id.btn_signup);
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString().trim();
                String email = etEmail.getText().toString().trim();
                String nohp = etNohp.getText().toString().trim();
                String password = etPassword.getText().toString().trim();
                String repassword = etRepassword.getText().toString().trim();

                if (name.isEmpty()) {
                    etName.setError("Name is required!");
                    etName.requestFocus();
                    return;
                }
                if (nohp.isEmpty()) {
                    etNohp.setError("No handphone is required!");
                    etNohp.requestFocus();
                    return;
                }
                if (email.isEmpty()) {
                    etEmail.setError("Email is required!");
                    etEmail.requestFocus();
                    return;
                }
                if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    etEmail.setError("Please provide valid email!");
                    etEmail.requestFocus();
                    return;
                }
                if (password.isEmpty()) {
                    etPassword.setError("Password is required!");
                    etPassword.requestFocus();
                    return;
                }
                if (password.length() < 0) {
                    etPassword.setError("Min password length should be 6 characters!");
                    etPassword.requestFocus();
                    return;
                }
                if (repassword.isEmpty()) {
                    etRepassword.setError("Re-password is required!");
                    etRepassword.requestFocus();
                    return;
                }
                if (!repassword.equals(password))  {
                    etRepassword.setError("Password not match!");
                    etRepassword.requestFocus();
                    return;
                }


                progressBar.setVisibility(View.VISIBLE);
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(SignUp.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    FirebaseUser user = mAuth.getCurrentUser();

                                    Toast.makeText(SignUp.this, "Register successful, ", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(SignUp.this, LoginFragment.class));
                                    progressBar.setVisibility(View.GONE);
                                } else {
                                    Toast.makeText(SignUp.this, "Failed", Toast.LENGTH_SHORT).show();
                                    progressBar.setVisibility(View.GONE);
                                }
                            }
                        });


            }
        });


    }


}