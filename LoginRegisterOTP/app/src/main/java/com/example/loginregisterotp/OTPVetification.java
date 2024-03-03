package com.example.loginregisterotp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class OTPVetification extends AppCompatActivity {
    private EditText OTP1, OTP2, OTP3, OTP4;
    //TRUE AFTER 60S
    private boolean resendEnable = false;
    private  int resendTime = 60;
    private TextView resendBtn;
    private  int selectPosition = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otpvetification);
        OTP1 = findViewById(R.id.OTP1);
        OTP2 = findViewById(R.id.OTP2);
        OTP3 = findViewById(R.id.OTP3);
        OTP4 = findViewById(R.id.OTP4);
        resendBtn = findViewById(R.id.ResendOTP);

        final Button buttonVerify = findViewById(R.id.VerifyBtn);
        final TextView otpEmail = findViewById(R.id.getEmail);
        final TextView otpPhone = findViewById(R.id.getPhone);
        final String getE = getIntent().getStringExtra("email");
        final String getP = getIntent().getStringExtra("mobile");
        otpEmail.setText(getE);
        otpPhone.setText(getP);
        OTP1.addTextChangedListener(textWatcher);
        OTP2.addTextChangedListener(textWatcher);
        OTP3.addTextChangedListener(textWatcher);
        OTP4.addTextChangedListener(textWatcher);
        showKeyboard(OTP1);
        startCountDownTime();
        resendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(resendEnable) {
                    startCountDownTime();
                }
            }
        });
        buttonVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String generateOTP = OTP1.getText().toString() + OTP2.getText().toString() + OTP3.getText().toString() + OTP4.getText().toString();
                if (generateOTP.length()==4) {

                }
            }
        });
    }
    private void showKeyboard(EditText otpEt){
        otpEt.requestFocus();
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.showSoftInput(otpEt, InputMethodManager.SHOW_IMPLICIT);
    }
    private void startCountDownTime() {
        resendEnable = false;
        resendBtn.setTextColor(Color.parseColor("#99000000"));
        new CountDownTimer(resendTime * 60, 100) {
            @Override
            public void onTick(long millisUntilFinished) {
                resendBtn.setText("Resend code (" +(millisUntilFinished/60)+ ")");
            }

            @Override
            public void onFinish() {
                resendEnable = true;
                resendBtn.setText("Resend Code");
                resendBtn.setTextColor(getResources().getColor(R.color.primary));
            }
        }.start();
    }
    private final TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if(s.length()>0){
                if(selectPosition == 0) {
                    selectPosition =1;
                    showKeyboard(OTP2);
                } else if (selectPosition==1) {
                    selectPosition=2;
                    showKeyboard(OTP3);
                } else if (selectPosition==2) {
                    selectPosition=3;
                    showKeyboard(OTP4);
                }
            }
        }
    };
    public boolean OnkeyUp (int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_DEL) {
            if(selectPosition ==3) {
                selectPosition = 2;
                showKeyboard(OTP3);
            }else if (selectPosition==2){
                selectPosition=1;
                showKeyboard(OTP2);
            }else if (selectPosition==1){
                selectPosition=0;
                showKeyboard(OTP1);
            }
            return  true;
        }else {
            return super.onKeyUp(keyCode, event);
        }
    }
}