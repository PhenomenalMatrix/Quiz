package com.orozbek.quiz.model;

import java.util.Date;
import java.util.List;

public class QuizResult {
    private int id;
    private String category;
    private String difficulty;
    private int questions;
    private int correctAnswerAmount;
    private Date createdAt;

    public QuizResult(int id, String category, String difficulty, int questions, int correctAnswerAmount, Date createdAt) {
        this.id = id;
        this.category = category;
        this.difficulty = difficulty;
        this.questions = questions;
        this.correctAnswerAmount = correctAnswerAmount;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public int getQuestions() {
        return questions;
    }

    public void setQuestions(int questions) {
        this.questions = questions;
    }

    public int getCorrectAnswerAmount() {
        return correctAnswerAmount;
    }

    public void setCorrectAnswerAmount(int correctAnswerAmount) {
        this.correctAnswerAmount = correctAnswerAmount;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
