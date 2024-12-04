package com.example.wordskills2023.activities.session2;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.wordskills2023.R;

public class NewPasswordActivity extends AppCompatActivity {

    boolean isPasswordVisible = false;
    boolean isConfirmPasswordVisible = false;

    boolean isFieldsFull = true;

    EditText[] PasswordEditTexts;
    Button setNewPasswordB;
    TextWatcher textWatcher;
    Toast toast;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_new_password);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

         setNewPasswordB = findViewById(R.id.setNewPassBNewPass);

        //Массив EditText паролей
        PasswordEditTexts = new EditText[] {findViewById(R.id.PasswordETNewPass),
                findViewById(R.id.ConfirmPasswordETNewPass)};

        textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                for (int i = 0; i < PasswordEditTexts.length; i++) {
                    setNewPasswordB.setEnabled(isFieldsFulled(PasswordEditTexts));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };

        for (int i = 0; i < PasswordEditTexts.length; i++) {
            PasswordEditTexts[i].addTextChangedListener(textWatcher);
        }
    }

    public void HideShowPassBClick(View view) {
        EditText PasswordET = findViewById(R.id.PasswordETNewPass);

        //Если пароль не виден - показываем, иначе - прячем
        if (!(isPasswordVisible)) {
            PasswordET.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        }
        else {
            PasswordET.setTransformationMethod(PasswordTransformationMethod.getInstance());
        }

        isPasswordVisible = !isPasswordVisible;
    }

    public void HideShowConfirmPassBClick(View view) {
        EditText ConfirmPasswordET = findViewById(R.id.ConfirmPasswordETNewPass);

        //Если пароль не виден - показываем, иначе - прячем
        if (!(isConfirmPasswordVisible)) {
            ConfirmPasswordET.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        }
        else {
            ConfirmPasswordET.setTransformationMethod(PasswordTransformationMethod.getInstance());
        }

        isConfirmPasswordVisible = !isConfirmPasswordVisible;
    }

    public boolean isFieldsFulled(EditText[] editTexts) {
        isFieldsFull = true;

        //Проходимся по массиву и проверяем все ли поля заполнены
        for (int i = 0; i < editTexts.length; i++) {
            if (editTexts[i].getText().toString().isEmpty()) {
                isFieldsFull = false;
                break;
            }
        }

        return  isFieldsFull;
    }

    public void SetNewPassBClick(View view) {

        EditText PasswordET = findViewById(R.id.PasswordETNewPass);
        EditText ConfirmPasswordET = findViewById(R.id.ConfirmPasswordETNewPass);
        if (PasswordET.getText().toString().equals(ConfirmPasswordET.getText().toString())) {
           toast = Toast.makeText(this, "Пароли совпадают", Toast.LENGTH_LONG);
            toast.show();
        }
        else {
            toast = Toast.makeText(this, "Пароли НЕ совпадают", Toast.LENGTH_LONG);
            toast.show();
        }
    }
}