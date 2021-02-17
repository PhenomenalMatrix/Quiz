package com.orozbek.quiz.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.orozbek.quiz.db.converter.DateConverter;
import com.orozbek.quiz.db.converter.QuestionConverter;

import java.util.Date;
import java.util.List;

@Entity(tableName = "QResult")
public class QuizResult {

    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "category")
    private String category;
    @ColumnInfo(name = "difficulty")
    private String difficulty;
    @ColumnInfo(name = "correct_answer_amount")
    private int correctAnswerAmount;
    @TypeConverters({DateConverter.class})
    private Date createdAt;
    @TypeConverters({QuestionConverter.class})
    private List<Question> questions;

    public QuizResult(String category, String difficulty, int correctAnswerAmount, Date createdAt, List<Question> questions) {
        this.category = category;
        this.difficulty = difficulty;
        this.correctAnswerAmount = correctAnswerAmount;
        this.createdAt = createdAt;
        this.questions = questions;
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

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
}
