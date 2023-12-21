package com.example.testpsiho;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;


import android.widget.ImageView;
import android.widget.TextView;

public class ResultsBank extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results_bank);
        final TextView oprosnik = findViewById(R.id.oprosnik);
        final ImageView delitBtn = findViewById(R.id.delitBtn);

        oprosnik.setOnClickListener(v -> {
            Intent intent = new Intent(ResultsBank.this, ResultsTest.class);
            startActivity(intent);
        });
        delitBtn.setOnClickListener(v -> new AlertDialog.Builder(ResultsBank.this)
                .setTitle("Удаление результатов")
                .setMessage("Вы уверены, что хотите удалить результаты теста?")
                .setPositiveButton("Да", (dialog, which) -> {
                    // Очистка данных
                    SQLiteDatabase db = openOrCreateDatabase("QuizResultsDB", MODE_PRIVATE, null);
                    db.execSQL("DELETE FROM results");
                })
                .setNegativeButton("Нет", null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show());




    }
}