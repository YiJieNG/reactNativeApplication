package com.example.reactnativeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    Button login, signUp;
    EditText userName,passWord;
    FirebaseAuth authenticator = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userName = findViewById(R.id.emailLogin);
        passWord = findViewById(R.id.passwordLogin);
        login = findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (userName.getText().toString().length() == 0 ||  passWord.getText().toString().length() == 0){
                    Toast.makeText(MainActivity.this, "Please fill in all necessary information", Toast.LENGTH_SHORT).show();
                }
                else {
                    String email = userName.getText().toString().trim();
                    String password = passWord.getText().toString().trim();
                    authenticator.signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "Login Complete!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(MainActivity.this, HomePage.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(MainActivity.this, "Login Failed! Please try again", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
        signUp = findViewById(R.id.signUp);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Register.class);
                startActivity(intent);
            }
        });
    }
}