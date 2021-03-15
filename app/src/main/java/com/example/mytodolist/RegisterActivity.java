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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {
    EditText emailet;
    EditText nameet;
    EditText passet;
    FirebaseAuth auth;
    FirebaseDatabase database;
    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        nameet=findViewById(R.id.sign_name);
        emailet=findViewById(R.id.sign_email);
        passet=findViewById(R.id.sign_pass);
        auth=FirebaseAuth.getInstance();
        database=FirebaseDatabase.getInstance();
        reference=database.getReference("User");
    }

    public void Sign(View view) {
        String name=nameet.getText().toString();
        String email=emailet.getText().toString();
        String pass=passet.getText().toString();
        if(email.isEmpty()){
            emailet.setError("Email");
        }else if(pass.isEmpty()){
            passet.setError("Password");
        }else{
            signinfo(name,email,pass);
        }
    }

    private void signinfo(final String name, final String email, final String pass) {
        auth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    String key=auth.getCurrentUser().getUid();
                    Userdata user=new Userdata(name,email,pass);
                    reference.child(key).setValue(user);

                    Intent intent1=new Intent(RegisterActivity.this,HomeActivity.class);
                    startActivity(intent1);
                }else{
                    Toast.makeText(RegisterActivity.this,task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
