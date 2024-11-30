package com.example.wordskills2023.activities.session2;

import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.wordskills2023.R;

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
        EditText PasswordET = findViewById(R.id.PasswordET);

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
        EditText ConfirmPasswordET = findViewById(R.id.ConfirmPasswordET);

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

}