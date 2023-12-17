package com.example.testpsiho;

public class QuestionsList {
    private String Btn_Da, Btn_Net, questions, answer;
    private String userSelectedAnswer;


    public QuestionsList(String questions ,String btn_Da, String btn_Net,  String answer, String userSelectedAnswer) {
        Btn_Da = btn_Da;
        Btn_Net = btn_Net;
        this.questions = questions;
        this.answer = answer;
        this.userSelectedAnswer = userSelectedAnswer;
    }


    public String getBtn_Da() {
        return Btn_Da;
    }

    public String getBtn_Net() {
        return Btn_Net;
    }

    public String getQuestions() {
        return questions;
    }

    public String getAnswer() {
        return answer;
    }

    public String getUserSelectedAnswer() {
        return userSelectedAnswer;
    }

    public void setBtn_Da(String btn_Da) {
        Btn_Da = btn_Da;
    }

    public void setBtn_Net(String btn_Net) {
        Btn_Net = btn_Net;
    }

    public void setQuestions(String questions) {
        this.questions = questions;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public void setUserSelectedAnswer(String userSelectedAnswer) {
        this.userSelectedAnswer = userSelectedAnswer;
    }
}
