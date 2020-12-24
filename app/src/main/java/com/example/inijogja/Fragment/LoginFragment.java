package com.example.inijogja.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.inijogja.Activity.MainActivity;
import com.example.inijogja.Activity.SignUp;
import com.example.inijogja.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class LoginFragment extends Fragment {
    TextView tvSignUp;
    EditText etEmail, etPassword;
    Button btnLogin;
    FirebaseAuth mAuth;
    FirebaseAuth.AuthStateListener mAuthStateListener;





//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        return inflater.inflate(R.layout.fragment_login, container, false);

        View rootView = inflater.inflate(R.layout.fragment_login, container, false);
        tvSignUp = rootView.findViewById(R.id.tv_signup);
        mAuth = FirebaseAuth.getInstance();
        etEmail = rootView.findViewById(R.id.et_email);
        etPassword = rootView.findViewById(R.id.et_password);
        btnLogin = rootView.findViewById(R.id.btn_login);

        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser mFirebaseUser = mAuth.getCurrentUser();
                if( mFirebaseUser != null ){
                    Toast.makeText(getActivity(),"You are logged in",Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(getActivity(), MainActivity.class);
                    startActivity(i);
                }
                else{
                    Toast.makeText(getActivity(),"Please Login",Toast.LENGTH_SHORT).show();
                }
            }
        };


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etEmail.getText().toString();
                String pw = etPassword.getText().toString();
                if (email.isEmpty()) {
                    etEmail.setError("Please enter email");
                    etEmail.requestFocus();
                }
                else if(pw.isEmpty()) {
                    etPassword.setError("Please enter password");
                    etPassword.requestFocus();
                }
                else if (email.isEmpty() && pw.isEmpty()) {
                    Toast.makeText(getActivity(), "Fields Are Empty!", Toast.LENGTH_SHORT).show();
                }
                else if(!(email.isEmpty() && pw.isEmpty())) {
                    mAuth.signInWithEmailAndPassword(email,pw).addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                Toast.makeText(getActivity(), "Login Unsuccessful, Please Try Again", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Intent intToHome = new Intent(getActivity(),MainActivity.class);
                                startActivity(intToHome);
                            }
                        }
                    });
                }
                else {
                    Toast.makeText(getActivity(), "Error Ocured!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SignUp.class);
                startActivity(intent);
            }
        });

        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthStateListener);
    }
}