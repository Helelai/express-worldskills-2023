package com.example.wordskills2023.activities.session2;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.wordskills2023.R;

public class ForgotPassActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_forgot_pass);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        EditText EmailET = findViewById(R.id.emailET);
        Button SendOTPB = findViewById(R.id.SendOTPB);
        EmailET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {

                //Если поле заполнено, то делаем кнопку отправки сообщения доступной, иначе блокируем
                if (!(EmailET.getText().toString().isEmpty())) {
                    SendOTPB.setEnabled(true);
                }
                else {
                    SendOTPB.setEnabled(false);
                }
            }
        });
    }

    public void SendOTPBClick(View view) {
        Intent intent = new Intent(this, OTPVerificationActivity.class);
        startActivity(intent);
    }

    public void SignInBPassClick(View view) {
        Intent intent = new Intent(this, SignInActivity.class);
        startActivity(intent);
        finish();
    }
}