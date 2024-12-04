package com.example.wordskills2023.activities.session2;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.wordskills2023.R;
import com.example.wordskills2023.activities.session1.HolderActivity;

public class SignUpActivity extends AppCompatActivity {

    boolean isPasswordVisible = false; //Переменная для проверки видимости пароля
    boolean isConfirmPasswordVisible = false; //Переменная для проверки видимости повторного пароля



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_up);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    //Если пользователь нажимает на кнопку с картинкой, чтобы показать пароль
    public void HideShowPassBClick(View view) {
        EditText PasswordET = findViewById(R.id.PasswordETNewPass);

        //Проверка пароля на видимость. Если пароль не виден:
        if (!isPasswordVisible) {
            //Делает пароль видимым
            PasswordET.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        }
        else {
            //Делает пароль скрытым
            PasswordET.setTransformationMethod(PasswordTransformationMethod.getInstance());
        }
        //Устанавливаем новое значение
        isPasswordVisible = !isPasswordVisible;
    }

    public void HideShowConfirmPassBClick(View view) {
        EditText ConfirmPasswordET = findViewById(R.id.ConfirmPasswordETNewPass);

        if (!isConfirmPasswordVisible) {
            //Если пароль не виден, то показываем
            ConfirmPasswordET.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        }
        else {
            //Иначе показываем
            ConfirmPasswordET.setTransformationMethod(PasswordTransformationMethod.getInstance());
        }
        //Устанавливаем значения
        isConfirmPasswordVisible = !isConfirmPasswordVisible;
    }

    public void SignUpBCrAccClick(View view) {
        EditText FullNameET = findViewById(R.id.FullNameET);
        EditText PhoneNumberET = findViewById(R.id.PhoneNumberET);
        EditText EmailET = findViewById(R.id.EmailET);
        EditText PasswordET = findViewById(R.id.PasswordETNewPass);
        EditText ConfirmPasswordET = findViewById(R.id.ConfirmPasswordETNewPass);

        //Проверка на заполненность всех полей
        if (FullNameET.getText().toString().isEmpty()
                || PhoneNumberET.getText().toString().isEmpty()
                || EmailET.getText().toString().isEmpty()
                || PasswordET.getText().toString().isEmpty()
                || ConfirmPasswordET.getText().toString().isEmpty()) {

            //Если все поля пусты выводим сообщение
            Toast toastEmptyFields = Toast.makeText(this,"Пожалуйста, проверьте заполненность всех полей.", Toast.LENGTH_LONG);
            toastEmptyFields.show();
        }
        else {
            if (PasswordET.getText().toString().equals(ConfirmPasswordET.getText().toString())) {
                Intent intent = new Intent(this, HolderActivity.class);
                startActivity(intent);
                finish();
            }
            else {
                Toast toastNotConfirmed = Toast.makeText(this, "Повторный пароль не совпадает!", Toast.LENGTH_LONG);
                toastNotConfirmed.show();
            }
        }
    }

    public void TermsCHBXClick(View view) {
        CheckBox TermsCHBX = findViewById(R.id.TermsCHBX);
        Button SignUpB = findViewById(R.id.ResendB);

        if (TermsCHBX.isChecked()) {
            SignUpB.setEnabled(true);
        }
        else {
            SignUpB.setEnabled(false);
        }
    }

    public void SignInBCrAccClick(View view) {
        Intent intent = new Intent(this, SignInActivity.class);
        startActivity(intent);
        finish();
    }
}