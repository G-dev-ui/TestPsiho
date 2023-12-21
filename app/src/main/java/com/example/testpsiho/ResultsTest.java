package com.example.testpsiho;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ResultsTest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results_test);
        final TextView oprosnik_results = findViewById(R.id.oprosnik_results);

        try (SQLiteDatabase db = this.openOrCreateDatabase("QuizResultsDB", MODE_PRIVATE, null); Cursor c = db.rawQuery("SELECT * FROM results", null)) {

            StringBuilder resultsText = new StringBuilder();
            if (c != null) {
                if (c.moveToFirst()) {
                    do {
                        String editText1 = c.getString(c.getColumnIndex("editText1"));
                        String editText2 = c.getString(c.getColumnIndex("editText2"));
                        String editText3 = c.getString(c.getColumnIndex("editText3"));
                        String editText4 = c.getString(c.getColumnIndex("editText4"));
                        String editText5 = c.getString(c.getColumnIndex("editText5"));
                        String testResults = c.getString(c.getColumnIndex("testResults"));

                        String data = "Имя: " + editText1 + "\n" +
                                "Фамилия: " + editText2 + "\n" +
                                "Отчество: " + editText3 + "\n" +
                                "Дата рождения: " + editText4 + "\n" +
                                "Класс: " + editText5 + "\n" +
                                "Результаты теста: " + testResults;

                        resultsText.append(data).append("\n");
                    } while (c.moveToNext());
                }
            }

            oprosnik_results.setText(resultsText.toString());
        } catch (Exception e) {
            // Обработка исключения
            e.printStackTrace();
        }
        // Закрытие курсора и базы данных
    }
}