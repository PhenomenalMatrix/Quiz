package com.orozbek.quiz.ui.result;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.orozbek.quiz.QuizApp;
import com.orozbek.quiz.model.QuizResult;

import java.util.List;

public class ResultViewModel extends ViewModel {

    public void saveResult(QuizResult quizResult) {
        QuizApp.quizDataBase.quizDao().insert(quizResult);
    }
}
