package com.orozbek.quiz.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.orozbek.quiz.model.QuizResult;

@Database(entities = {QuizResult.class}, version = 1)
public abstract class QuizDataBase extends RoomDatabase {
    public abstract QuizDao quizDao();
}
