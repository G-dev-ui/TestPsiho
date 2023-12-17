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
import java.util.List;

public class QuizActivity extends AppCompatActivity {

    private TextView questions_number;
    private TextView questions;
    private AppCompatButton Btn_Da;
    private AppCompatButton Btn_Net;
    private AppCompatButton next_Btn;

    private  List<QuestionsList> questionsList;
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

        questions_number.setText((currentQuestionPosition+1)+"/"+questionsList.size() );
        questions.setText(questionsList.get(0).getQuestions());
        Btn_Da.setText(questionsList.get(0).getBtn_Da());
        Btn_Net.setText(questionsList.get(0).getBtn_Net());


        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivities(new Intent[]{new Intent(QuizActivity.this, MainActivity.class)});
                finish();
            }
        });
        Btn_Da.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (selectedOptionByUser.isEmpty()){
                    selectedOptionByUser = Btn_Da.getText().toString();
                    Btn_Da.setBackgroundResource(R.drawable.round_back_white_stroke10);


                }

            }
        });
        Btn_Net.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedOptionByUser.isEmpty()){
                    selectedOptionByUser = Btn_Net.getText().toString();
                    Btn_Net.setBackgroundResource(R.drawable.round_back_white_stroke10);


                }

            }
        });
        next_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedOptionByUser.isEmpty()){
                    Toast.makeText(QuizActivity.this,"Пожалуйста выберите ответ",Toast.LENGTH_SHORT).show();
                } else {
                    changeNextQuestion();

                }

            }
        });


    }



    @Override
    public void onBackPressed (){
        startActivities(new Intent[]{new Intent(QuizActivity.this, MainActivity.class)});
        finish();
    }
    
    private void changeNextQuestion(){
        currentQuestionPosition++;

        if ((currentQuestionPosition+1) == questionsList.size() ){
            next_Btn.setText("Готово");
        }
        if (currentQuestionPosition < questionsList.size()){
            selectedOptionByUser = "";
            Btn_Da.setBackgroundResource(R.drawable.round_back_white_stroke2_10);
            Btn_Da.setTextColor(Color.parseColor("#1F6BB8"));
            Btn_Net.setBackgroundResource(R.drawable.round_back_white_stroke2_10);
            Btn_Net.setTextColor(Color.parseColor("#1F6BB8"));

            questions_number.setText((currentQuestionPosition+1)+"/"+questionsList.size() );
            questions.setText(questionsList.get(currentQuestionPosition).getQuestions());
            Btn_Da.setText(questionsList.get(currentQuestionPosition).getBtn_Da());
            Btn_Net.setText(questionsList.get(currentQuestionPosition).getBtn_Net());

        }
        else {

            Intent intent = new Intent(QuizActivity.this, QuizResults.class);

            intent.putExtra("correct", getCorrectAnswers());
            intent.putExtra("incorrect", getInCorrectAnswers());


            startActivity(intent);
            finish();

        }
    }

    private int getCorrectAnswers (){

        int correctAnswers = 0;

        for (int i = 0; i < questionsList.size(); i++){
            final String getUserSelectedAnswer = questionsList.get(i).getUserSelectedAnswer();
            final String getAnswer = questionsList.get(i).getAnswer();

            if (getUserSelectedAnswer.equals(getAnswer)){
                correctAnswers++;
            }
        }


        return correctAnswers;
    }
    private int getInCorrectAnswers (){

        int correctAnswers = 0;

        for (int i = 0; i < questionsList.size(); i++){
            final String getUserSelectedAnswer = questionsList.get(i).getUserSelectedAnswer();
            final String getAnswer = questionsList.get(i).getAnswer();

            if (!getUserSelectedAnswer.equals(getAnswer)){
                correctAnswers++;
            }
        }


        return correctAnswers;
    }


}