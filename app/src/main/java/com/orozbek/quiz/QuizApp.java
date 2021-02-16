package com.orozbek.quiz;

import android.app.Application;

import com.orozbek.quiz.data.IQuizApiClient;
import com.orozbek.quiz.data.QuizApiClient;
import com.orozbek.quiz.data.QuizRepository;

public class QuizApp extends Application {

    public static IQuizApiClient quizApiClient;

    public static QuizRepository repository;

    @Override
    public void onCreate() {
        super.onCreate();

        quizApiClient = new QuizApiClient();
        repository = new QuizRepository(quizApiClient);


    }
}
