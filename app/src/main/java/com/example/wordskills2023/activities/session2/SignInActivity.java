package com.example.wordskills2023.activities.session2;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.wordskills2023.R;

public class SignInActivity extends AppCompatActivity {

    boolean isPasswordVisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_in);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        EditText EmailET = findViewById(R.id.EmailET);
        EditText PasswordET = findViewById(R.id.PasswordETNewPass);
        Button LogInB = findViewById(R.id.LogInB);

        //Слушатели изменения текста
        EmailET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                //Если при изменении текста нет пустых полей
                if (!(EmailET.getText().toString().isEmpty())
                && !(PasswordET.getText().toString().isEmpty())) {
                    //Делаем кнопку входа доступной
                    LogInB.setEnabled(true);
                }
                //иначе блокируем
                else {
                    LogInB.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }

        });

        PasswordET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!(EmailET.getText().toString().isEmpty())
                        && !(PasswordET.getText().toString().isEmpty())) {
                    //Делаем кнопку входа доступной
                    LogInB.setEnabled(true);
                }
                //иначе блокируем
                else {
                    LogInB.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public void RememberPasswordCHBXClick(View view) {

    }

    public void SignUpBLogInClick(View view) {
        Intent intent = new Intent(this,SignUpActivity.class);
        startActivity(intent);
        finish();
    }

    public void HideShowPassBClick(View view) {
        ImageButton HideShowPassB = findViewById(R.id.HideShowPassBNewPass);
        EditText PasswordET = findViewById(R.id.PasswordETNewPass);

        //Показываем пароль, если его НЕ видно
        if (!isPasswordVisible) {
            PasswordET.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        }
        //Иначе прячем
        else {
            PasswordET.setTransformationMethod(PasswordTransformationMethod.getInstance());
        }

        isPasswordVisible = !isPasswordVisible;
    }

    public void SignInBClick(View view) {
    }

    public void ForgotPassBClick(View view) {
        Intent intent = new Intent(this, ForgotPassActivity.class);
        startActivity(intent);
        finish();
    }
}