package com.example.testpsiho;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;

import android.database.sqlite.SQLiteDatabase;

import android.os.Bundle;

import android.widget.TextView;


import java.util.HashMap;
import java.util.Map;

public class QuizResults extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_results);

        final AppCompatButton startNewTest = findViewById(R.id.startNewTestBtn);
        final TextView data_info = findViewById(R.id.data_info);

        SQLiteDatabase db = this.openOrCreateDatabase("QuizResultsDB", MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS results (editText1 VARCHAR, editText2 VARCHAR, editText3 VARCHAR, editText4 VARCHAR, editText5 VARCHAR, testResults VARCHAR);");

        Bundle bundle = getIntent().getBundleExtra("results");
        Map<String, Integer> results = new HashMap<>();
        for (String key : bundle.keySet()) {
            results.put(key, bundle.getInt(key));
        }
        StringBuilder resultsText = new StringBuilder();
        for (Map.Entry<String, Integer> entry : results.entrySet()) {
            resultsText.append(entry.getKey()).append(": ").append(entry.getValue()).append(", ");
        }

        String editText1 = getIntent().getStringExtra("editText1");
        String editText2 = getIntent().getStringExtra("editText2");
        String editText3 = getIntent().getStringExtra("editText3");
        String editText4 = getIntent().getStringExtra("editText4");
        String editText5 = getIntent().getStringExtra("editText5");

        String query = "INSERT INTO results (editText1, editText2, editText3, editText4, editText5, testResults) VALUES ('" + editText1 + "', '" + editText2 + "', '" + editText3 + "', '" + editText4 + "', '" + editText5 + "', '" + resultsText + "');";
        db.execSQL(query);

        String data = "Имя: " + editText1 + "\n" +
                "Фамилия: " + editText2 + "\n" +
                "Отчество: " + editText3 + "\n" +
                "Дата рождения: " + editText4 + "\n" +
                "Класс: " + editText5 + "\n" +
                "Результаты теста: " + resultsText;

        data_info.setText(data);

        startNewTest.setOnClickListener(v -> {
            startActivity(new Intent(QuizResults.this,MainActivity.class));
            finish();
        });
    }
    @Override
    public void onBackPressed(){
        startActivity(new Intent(QuizResults.this,MainActivity.class));
        finish();
    }
}
