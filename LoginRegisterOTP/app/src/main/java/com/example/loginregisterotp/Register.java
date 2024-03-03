package com.example.loginregisterotp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class Register extends AppCompatActivity {
    private boolean passshowing = false;
    private boolean conpassshowing = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final EditText email = findViewById(R.id.email);
        final EditText name = findViewById(R.id.fullname);
        final EditText phone = findViewById(R.id.phonenumber);
        final EditText password = findViewById(R.id.passEDT);
        final ImageView iconpass = findViewById(R.id.passwordIcon);
        final ImageView coniconpass = findViewById(R.id.conpasswordIcon);
        final EditText conPassword = findViewById(R.id.conpassEDT);
        final AppCompatButton signUp = findViewById(R.id.signUpBtn);
        final TextView signIn = findViewById(R.id.SignInnBtn);

        iconpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(passshowing){
                    passshowing = false;
                    password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    iconpass.setImageResource(R.drawable.eye_off_outline);
                }else {
                    passshowing = true;
                    password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    iconpass.setImageResource(R.drawable.eye_outline);
                }
                password.setSelection(password.length());
            }
        });
        coniconpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(conpassshowing){
                    conpassshowing = false;
                    conPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    coniconpass.setImageResource(R.drawable.eye_outline);
                }else {
                    conpassshowing = true;
                    conPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    coniconpass.setImageResource(R.drawable.eye_off_outline);
                }
                conPassword.setSelection(conPassword.length());
            }
        });
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String getModbileText = phone.getText().toString();
                final String getEmail = email.getText().toString();
                Intent intent = new Intent(Register.this, OTPVetification.class);
                intent.putExtra("mobile", getModbileText);
                intent.putExtra("email", getEmail);
                startActivity(intent);
            }
        });
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Register.this, Login.class);
                startActivity(intent);
            }
        });

    }
}