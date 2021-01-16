package com.orozbek.quiz;

import android.app.Application;

import com.orozbek.quiz.data.IQuizApiClient;
import com.orozbek.quiz.data.QuizApiClient;

public class QuizApp extends Application {

    public static IQuizApiClient quizApiClient;

    @Override
    public void onCreate() {
        super.onCreate();

        quizApiClient = new QuizApiClient();
    }
}
