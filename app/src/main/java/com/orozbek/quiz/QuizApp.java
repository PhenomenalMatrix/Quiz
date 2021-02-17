package com.orozbek.quiz;

import android.app.Application;

import androidx.room.Room;

import com.orozbek.quiz.data.IQuizApiClient;
import com.orozbek.quiz.data.QuizApiClient;
import com.orozbek.quiz.data.QuizRepository;
import com.orozbek.quiz.db.HistoryStorage;
import com.orozbek.quiz.db.IHistoryStorage;
import com.orozbek.quiz.db.QuizDataBase;

public class QuizApp extends Application {

    public static IQuizApiClient quizApiClient;
    public static QuizRepository repository;
    public static QuizDataBase quizDataBase;
    public static IHistoryStorage historyStorage;


    @Override
    public void onCreate() {
        super.onCreate();
        quizApiClient = new QuizApiClient();
        repository = new QuizRepository(quizApiClient);

        historyStorage = new HistoryStorage();

        quizDataBase = Room.databaseBuilder(
                this,
                QuizDataBase.class,
                "quiz.db"
        ).fallbackToDestructiveMigration()
        .allowMainThreadQueries()
        .build()
        ;
    }

}
