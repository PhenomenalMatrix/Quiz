package com.orozbek.quiz.ui.Qst;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.orozbek.quiz.QuizApp;
import com.orozbek.quiz.data.IQuizApiClient;
import com.orozbek.quiz.model.Question;

import java.util.List;

public class QstViewModel extends ViewModel implements  IQuizApiClient.QuestionsCallBack{

     MutableLiveData<List<Question>> quizResp = new MutableLiveData<>();
     MutableLiveData<Integer> currentQuestionPosition = new MutableLiveData<>();
     List<Question> mQuestion;
    private Integer count;

    public QstViewModel (){
        currentQuestionPosition.setValue(0);
        count=0;
    }

    void getQst(int amount, int categId,String diff){
        QuizApp.quizApiClient.getQuestions(this,amount,categId,diff);
    }

    void skipClick(){
        if(mQuestion.size() >= currentQuestionPosition.getValue()){
            mQuestion.get(currentQuestionPosition.getValue()).setAnswerClick(true);
            quizResp.setValue(mQuestion);
            currentQuestionPosition.setValue(++count);
            if (currentQuestionPosition.getValue() + 1 == mQuestion.size()){
                Log.e("TAG", "SkipClick: finish");
            }
        }
    }

    void onBackPressed() {
        if (currentQuestionPosition.getValue() !=0){
            currentQuestionPosition.setValue(--count);
        }else {
            Log.e("TAG", "onBackPressed: finish");
        }
    }


    public void onAnswerClick(int position, int selectedAnswerPosition){
        if(mQuestion.size() > position && position >= 0){
            mQuestion.get(position).setSelectAnswerPosition(selectedAnswerPosition);
            mQuestion.get(position).setAnswerClick(true);
            quizResp.setValue(mQuestion);
            if (position + 1 == mQuestion.size()){
                Log.e("TAG", "onAnswer: finish");
            } else {
                currentQuestionPosition.setValue(++count);
            }
        }
    }


    @Override
    public void onSuccess(List<Question> result) {
        if(result !=null){
            mQuestion = result;
            quizResp.setValue(result);
//            Log.e("fr", new Gson().toJson(result) );
        }
    }

    @Override
    public void onFailure(Exception e) {

    }
}
