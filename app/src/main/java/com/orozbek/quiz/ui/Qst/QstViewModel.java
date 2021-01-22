package com.orozbek.quiz.ui.Qst;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.gson.Gson;
import com.orozbek.quiz.QuizApp;
import com.orozbek.quiz.data.IQuizApiClient;
import com.orozbek.quiz.interfaces.OnItemClickListner;
import com.orozbek.quiz.model.Question;
import com.orozbek.quiz.model.QuizResponse;
import com.orozbek.quiz.model.QuizResponse;

import java.util.ArrayList;
import java.util.List;

public class QstViewModel extends ViewModel implements OnItemClickListner, IQuizApiClient.QuestionsCallBack{


    MutableLiveData<List<Question>> quizResp = new MutableLiveData<>();


    MutableLiveData<Integer> answerAmount = new MutableLiveData<>();

    void getQst(int amount, int categId,String diff){
        QuizApp.quizApiClient.getQuestions(this,amount,categId,diff);
    }

    @Override
    public void onItemClick(int position) {
        if (answerAmount.getValue() == null){
            answerAmount.setValue(0);
        }
        answerAmount.setValue(answerAmount.getValue()+1);
    }


    @Override
    public void onSuccess(List<Question> result) {
        if(result !=null){
            quizResp.setValue(result);
            Log.e("fr", new Gson().toJson(result) );
        }
    }

    @Override
    public void onFailure(Exception e) {

    }
}
