package com.example.wordskills2023.activities.session1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.wordskills2023.R;
import com.example.wordskills2023.activities.session2.SignInActivity;

public class Onboarding3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_onboarding3);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void SignInBClick(View view) {
        Intent intent = new Intent(this, com.example.wordskills2023.activities.session2.SignInActivity.class);
        startActivity(intent);
    }

    public void SignUpBClick(View view) {
        Intent intent = new Intent(this, com.example.wordskills2023.activities.session2.SignUpActivity.class);
        startActivity(intent);
    }
}