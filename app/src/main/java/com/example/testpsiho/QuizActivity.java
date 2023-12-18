package com.example.testpsiho;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuizActivity extends AppCompatActivity {
    private TextView questions_number;
    private TextView questions;
    private AppCompatButton Btn_Da;
    private AppCompatButton Btn_Net;
    private AppCompatButton next_Btn;

    private List<QuestionsList> questionsList;
    private int currentQuestionPosition = 0;
    private String selectedOptionByUser = "";

    public QuizActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        questions_number = findViewById(R.id.questions_number);
        questions = findViewById(R.id.questions);
        Btn_Da = findViewById(R.id.Btn_Da);
        Btn_Net = findViewById(R.id.Btn_Net);
        next_Btn = findViewById(R.id.next_Btn);
        final TextView selectedTopicName = findViewById(R.id.text_topicName);
        final ImageView backBtn = findViewById(R.id.back_image);
        final String getSelectedTopic = getIntent().getStringExtra("selectedTopic");
        selectedTopicName.setText(getSelectedTopic);

        questionsList = OuestionsBank.getQuestions(getSelectedTopic);

        questions_number.setText((currentQuestionPosition + 1) + "/" + questionsList.size());
        questions.setText(questionsList.get(0).getQuestions());
        Btn_Da.setText(questionsList.get(0).getBtn_Da());
        Btn_Net.setText(questionsList.get(0).getBtn_Net());
        String editText1 = getIntent().getStringExtra("editText1");
        String editText2 = getIntent().getStringExtra("editText2");
        String editText3 = getIntent().getStringExtra("editText3");
        String editText4 = getIntent().getStringExtra("editText4");
        String editText5 = getIntent().getStringExtra("editText5");


        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivities(new Intent[]{new Intent(QuizActivity.this, FormaRegistr.class)});
                finish();
            }
        });
        Btn_Da.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Btn_Da.setBackgroundResource(R.drawable.round_back_white_stroke10);
                if (selectedOptionByUser.isEmpty()) {
                    selectedOptionByUser = Btn_Da.getText().toString();
                    questionsList.get(currentQuestionPosition).setUserSelectedAnswer("да");


                }else {
                    changeNextQuestion();
                }

            }
        });
        Btn_Net.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Btn_Net.setBackgroundResource(R.drawable.round_back_white_stroke10);
                if (selectedOptionByUser.isEmpty()) {
                    selectedOptionByUser = Btn_Net.getText().toString();
                    questionsList.get(currentQuestionPosition).setUserSelectedAnswer("нет");


                }else {
                    changeNextQuestion();
                }

            }
        });
        next_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedOptionByUser.isEmpty()) {
                    Toast.makeText(QuizActivity.this, "Пожалуйста выберите ответ", Toast.LENGTH_SHORT).show();
                } else {
                    changeNextQuestion();

                }

            }
        });


    }


    @Override
    public void onBackPressed() {
        startActivities(new Intent[]{new Intent(QuizActivity.this, FormaRegistr.class)});
        finish();
    }

    private void changeNextQuestion() {
        currentQuestionPosition++;

        if ((currentQuestionPosition + 1) == questionsList.size()) {
            next_Btn.setText("Готово");
        }
        if (currentQuestionPosition < questionsList.size()) {
            selectedOptionByUser = "";
            Btn_Da.setBackgroundResource(R.drawable.round_back_white_stroke2_10);
            Btn_Da.setTextColor(Color.parseColor("#1F6BB8"));
            Btn_Net.setBackgroundResource(R.drawable.round_back_white_stroke2_10);
            Btn_Net.setTextColor(Color.parseColor("#1F6BB8"));

            questions_number.setText((currentQuestionPosition + 1) + "/" + questionsList.size());
            questions.setText(questionsList.get(currentQuestionPosition).getQuestions());
            Btn_Da.setText(questionsList.get(currentQuestionPosition).getBtn_Da());
            Btn_Net.setText(questionsList.get(currentQuestionPosition).getBtn_Net());

        } else {
            Map<String, Integer> results = evaluateTest();

            Intent intent = new Intent(QuizActivity.this, QuizResults.class);
            Bundle bundle = new Bundle();
            for (Map.Entry<String, Integer> entry : results.entrySet()) {
                bundle.putInt(entry.getKey(), entry.getValue());
            }
            intent.putExtra("results", bundle);
            intent.putExtra("editText1", getIntent().getStringExtra("editText1"));
            intent.putExtra("editText2", getIntent().getStringExtra("editText2"));
            intent.putExtra("editText3", getIntent().getStringExtra("editText3"));
            intent.putExtra("editText4", getIntent().getStringExtra("editText4"));
            intent.putExtra("editText5", getIntent().getStringExtra("editText5"));
            startActivity(intent);
            startActivity(intent);
            finish();
        }
    }

    private static final Map<String, List<Integer>> scales = new HashMap<>();
    static {
        scales.put("Физическая агрессия", Arrays.asList(1, 25, 31, 41, 48, 55, 62, 68, -9, -7));
        scales.put("Косвенная агрессия", Arrays.asList(2, 10, 18, 34, 42, 56, 63, -26, -49));
        scales.put("Раздражение", Arrays.asList(3, 19, 27, 43, 50, 57, 64, 72, -11, -35, -69));
        scales.put("Негативизм", Arrays.asList(4, 12, 20, 28, -36));
        scales.put("Обида", Arrays.asList(5, 13, 21, 29, 37, 44, 51, 58));
        scales.put("Подозрительность", Arrays.asList(6, 14, 22, 30, 38, 45, 52, 59, -33, -66, -74, -75));
        scales.put("Вербальная агрессия", Arrays.asList(7, 15, 23, 31, 46, 53, 60, 71, 73, -33, -66, -74, -75));
        scales.put("Чувство вины", Arrays.asList(8, 16, 24, 32, 40, 47, 54, 61, 67));
    }
    private Map<String, Integer> evaluateTest() {
        Map<String, Integer> results = new HashMap<>();
        for (Map.Entry<String, List<Integer>> entry : scales.entrySet()) {
            int score = 0;
            for (Integer question : entry.getValue()) {
                String answer = questionsList.get(Math.abs(question) - 1).getUserSelectedAnswer();
                if (question > 0 && "да".equals(answer) || question < 0 && "нет".equals(answer)) {
                    score++;
                }
            }
            results.put(entry.getKey(), score);
        }
        return results;
    }


}