package com.orozbek.quiz.ui.main;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.gson.Gson;
import com.orozbek.quiz.QuizApp;
import com.orozbek.quiz.data.IQuizApiClient;
import com.orozbek.quiz.model.Category;
import com.orozbek.quiz.model.Question;

import java.util.List;

public class MainViewModel extends ViewModel {

    private Question question;

    public MutableLiveData <String> nameData = new MutableLiveData<>();

    public MutableLiveData <Category> category = new MutableLiveData<>();

    public void getName() {
        nameData.setValue("Victory");
    }

    public void getCategory(){
        QuizApp.quizApiClient.getCategories(new IQuizApiClient.CategoryCallBack() {
            @Override
            public void onSuccess(Category categories) {
                Log.e("kekLOL", new Gson().toJson(categories) );
                category.setValue(categories);
            }

            @Override
            public void onFailure(Exception e) {

            }
        });
    }
}
