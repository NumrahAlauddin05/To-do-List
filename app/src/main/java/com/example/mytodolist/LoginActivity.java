package com.example.mytodolist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    EditText emailet;
    EditText passet;
    FirebaseAuth auth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        emailet=findViewById(R.id.userEmail);
        passet=findViewById(R.id.userPass);
        auth=FirebaseAuth.getInstance();
    }

    public void Login(View view) {
        String email=emailet.getText().toString();
        String pass=passet.getText().toString();
        if(email.isEmpty()){
            emailet.setError("Email");
        }else if(pass.isEmpty()){
            passet.setError("Password");
        }else{
            logininfo(email,pass);
        }
    }

    private void logininfo(String email,String pass) {
        auth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Intent intent1=new Intent(LoginActivity.this,HomeActivity.class);
                    startActivity(intent1);
                }else{
                    Toast.makeText(LoginActivity.this,task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void signup(View view) {
        Intent intent=new Intent(LoginActivity.this,RegisterActivity.class);
        startActivity(intent);
    }
}
