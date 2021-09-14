package com.example.reactnativeapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;



public class Register extends AppCompatActivity {
    Button register;
    EditText userName,passWord,passWordConfirm;
    FirebaseAuth authenticator = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        userName = findViewById(R.id.Username);
        passWord = findViewById(R.id.password);
        passWordConfirm = findViewById(R.id.password_confirm);
        register = findViewById(R.id.login);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (userName.getText().length() == 0 ||  passWord.getText().length() == 0 || passWordConfirm.getText().length() == 0){
                    Toast.makeText(Register.this, "Please fill in all necessary information", Toast.LENGTH_SHORT).show();
                }
                else{
                    String email = userName.getText().toString().trim();
                    String password = passWord.getText().toString().trim();
                    String password_confirm = passWordConfirm.getText().toString().trim();
                    if (!password.equals(password_confirm)) {
                        Toast.makeText(Register.this, "Password does not match. Please re-enter", Toast.LENGTH_SHORT).show();
                        passWord.setText("");
                        passWordConfirm.setText("");
                        return;
                    }
                    authenticator.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(Register.this, "Registration complete!", Toast.LENGTH_SHORT).show();
                            } else {
                                System.out.println(email);
                                Toast.makeText(Register.this, "Registration failed! Please choose a better password (Combination of numeric and alphabets) or a different email.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }
}