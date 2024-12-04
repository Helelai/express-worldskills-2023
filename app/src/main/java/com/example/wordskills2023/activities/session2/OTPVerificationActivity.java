package com.example.wordskills2023.activities.session2;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.wordskills2023.R;

public class OTPVerificationActivity extends AppCompatActivity {
    boolean isFieldsFull = true;
    private CountDownTimer countDownTimer;
    private TextWatcher textWatcher;

    EditText[] editTexts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_otpverification);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //Делаем массив EdtText'ов
        editTexts = new EditText[] {findViewById(R.id.inputNumberET1), findViewById(R.id.inputNumberET2),
                findViewById(R.id.inputNumberET3), findViewById(R.id.inputNumberET4),
                findViewById(R.id.inputNumberET5), findViewById(R.id.inputNumberET6)};

        Button setNewPassB = findViewById(R.id.setNewPassB);
        //Слушатели изменения текста, чтобы делать edittext нужного цвета

        textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //Проходимся циклом по каждому edittext
                for (int i = 0; i < editTexts.length; i++) {

                    //Иеняем стиль, если пользователь что-то ввел или убрал
                    setEditTextStyle(editTexts[i]);

                }
                //Проверяем, не заполнил ли пользователь все поля
                setNewPassB.setEnabled(isFullFields(editTexts));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };

        //Присваиваем каждому edittext слушатель нажатий, который ранее создали
        for (EditText editText : editTexts) {
            editText.addTextChangedListener(textWatcher);
        }

        CountDownTimerForResend();
    }



    //Методы и кнопки
    //Метод отсчета для кнопки resend
    public void CountDownTimerForResend() {
        TextView ResendB = findViewById(R.id.ResendB);
         countDownTimer = new CountDownTimer(61000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                //Конвертируем миллисекунды в минуты и секунды
                int minutes = (int) (millisUntilFinished / (1000*60));
                int seconds = (int) ((millisUntilFinished / 1000) % 60);

                //Делаем отформатированную для времени строку

                //"%d:%02d"
                // %d - целое число; : - разделитель (как на таймерах);
                //%02d: - написать 0, если количество символов меньше 2; %d - число
                String formattedTime = String.format("%d:%02d", minutes, seconds);
                ResendB.setText("resend after " + formattedTime);
            }

            @Override
            public void onFinish() {
            ResendB.setText("resend");
            ResendB.setTextColor(getColor(R.color.titleBlue));

            }
        };

        countDownTimer.start();
    }

    public void resendBClick(View view) {
        TextView ResendB = findViewById(R.id.ResendB);
        ResendB.setTextColor(getColor(R.color.grayHint));

        //Очищаем предыдущий таймер, если он есть.
        if (countDownTimer != null) countDownTimer.cancel();
        CountDownTimerForResend();
    }

    //Проверка, что все поля заполнены
    public boolean isFullFields(EditText[] editTexts) {
        isFieldsFull = true;

        //Проходимся по каждому элементу массива
        for (int i = 0; i < editTexts.length; i++) {
            if (editTexts[i].getText().toString().isEmpty()) {
                isFieldsFull = false;
                break;
            }
        }

        return isFieldsFull;
    }

    public void setEditTextStyle(EditText inputNumber) {
        //Проверка, что если поле не пустое - сделать его голубым, пустое - серым
        if (!(inputNumber.getText().toString().isEmpty())) {
            inputNumber.setBackgroundResource(R.drawable.blue_input_number_otp);
        }
        else {
            inputNumber.setBackgroundResource(R.drawable.gray_input_number_otp);
        }
    }

    public void SetNewPassBClick(View view) {
        Intent intent = new Intent(this, NewPasswordActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        //Убираем все textwatcher после закрытия приложения и обнуляем ссылки на view
        for (int i = 0; i < editTexts.length; i++) {
            editTexts[i].removeTextChangedListener(textWatcher);
            editTexts[i] = null;
        }
    }
}