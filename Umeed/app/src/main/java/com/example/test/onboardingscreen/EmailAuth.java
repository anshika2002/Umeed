package com.example.test.onboardingscreen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

public class EmailAuth extends AppCompatActivity {

    private TextInputLayout email,pass,merror;
    private Button done;
    private ProgressBar progressBar;
    private FirebaseAuth auth;
    String memail,mpass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_auth);
        email = findViewById(R.id.email_login);
        pass = findViewById(R.id.pass_login);
        done = findViewById(R.id.login_done);
        merror = findViewById(R.id.error_login);
        progressBar = findViewById(R.id.login_progress);
        auth = FirebaseAuth.getInstance();

        done.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                memail = email.getEditText().getText().toString();
                mpass = pass.getEditText().getText().toString();
                email.setErrorEnabled(false);
                pass.setErrorEnabled(false);
                if(memail.isEmpty() && mpass.isEmpty())
                {
                    email.setErrorEnabled(true);
                    pass.setErrorEnabled(true);
                    email.setError("Required Field");
                    pass.setError("Required Field");
                }
                else if(memail.isEmpty())
                {
                    email.setErrorEnabled(true);
                    email.setError("Required Field");
                }
                else if(mpass.isEmpty())
                {
                    pass.setErrorEnabled(true);
                    pass.setError("Required Field");
                }
                else if(mpass.length()<6)
                {
                    pass.setErrorEnabled(true);
                    pass.setError("Pass length must be greater than or equals 6");
                }
                else
                    {
                        auth.createUserWithEmailAndPassword(memail,mpass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful())
                                {
                                   Toast.makeText(getApplicationContext(),"User Created Successfully",Toast.LENGTH_SHORT).show();
                                   startActivity(new Intent(EmailAuth.this,WelcomeActivity.class));
                                    Log.e("uid",auth.getUid());
                                   finish();
                                }
                                else
                                {
                                    try
                                    {
                                        throw task.getException();
                                    }
                                    catch (FirebaseAuthUserCollisionException existemail)
                                    {
                                        SignInUser(memail,mpass);
                                    }
                                    catch (Exception e)
                                    {
                                        merror.setError(e.getMessage());
                                    }
                                }
                            }
                        });
                    }
            }
        });
    }

    private void SignInUser(String email, String pass) {
        auth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(getApplicationContext(),"User Signed In Successfully",Toast.LENGTH_SHORT).show();
                    Log.e("uid",auth.getUid());
                    startActivity(new Intent(EmailAuth.this,WelcomeActivity.class));
                    finish();
                }
            }
        });
    }
}