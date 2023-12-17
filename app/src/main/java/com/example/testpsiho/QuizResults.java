package com.example.testpsiho;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class QuizResults extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_results);

        final AppCompatButton startNewTest = findViewById(R.id.startNewTestBtn);
        final TextView resulttest1 = findViewById(R.id.result_test1);

        final int getCorrentAnswers = getIntent().getIntExtra("correct", 0);
        final int getInCorrentAnswers = getIntent().getIntExtra("incorrect", 0);
        resulttest1.setText(getCorrentAnswers);
        resulttest1.setText(getInCorrentAnswers);


        startNewTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(QuizResults.this,MainActivity.class));
                finish();
            }
        });
    } @Override
    public void onBackPressed(){
        startActivity(new Intent(QuizResults.this,MainActivity.class));
        finish();
    }
}