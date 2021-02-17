package com.orozbek.quiz.db;

import androidx.lifecycle.LiveData;

import com.orozbek.quiz.model.QuizResult;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public interface IHistoryStorage {
    LiveData<ArrayList<QuizResult>>getAll();
    QuizResult getQuizResult(int id);

    int saveQuizResult(QuizResult quizResult);
    void delete(int id);
    void deleteAll();
}
