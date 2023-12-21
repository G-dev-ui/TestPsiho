package com.example.testpsiho;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public String selectedTopic = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final LinearLayout test_psiho1 = findViewById(R.id.test_psiho1);
        final Button startQuizBtn = findViewById(R.id.startQuizBtn);
        final Button bankresultsBtn = findViewById(R.id.bankresultsBtn);


        bankresultsBtn.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, ResultsBank.class)));

        test_psiho1.setOnClickListener(v -> {

            selectedTopic = "Опросник враждебности";
            test_psiho1.setBackgroundResource(R.drawable.round_back_white_stroke10);



        });

        startQuizBtn.setOnClickListener(v -> {

            if (selectedTopic.isEmpty()) {
                Toast.makeText(MainActivity.this, "Выберите викторину", Toast.LENGTH_SHORT).show();
            } else {
                Intent intent = new Intent(MainActivity.this, FormaRegistr.class);
                intent.putExtra("selectedTopic", selectedTopic);
                startActivity(intent);
                finish();
            }

        });


    }
}