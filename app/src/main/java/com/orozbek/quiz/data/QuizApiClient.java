package com.orozbek.quiz.data;

import android.util.Log;

import com.orozbek.quiz.model.Category;
import com.orozbek.quiz.model.QuizResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class QuizApiClient implements IQuizApiClient {


    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://opentdb.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();


    QuizApi quizApi = retrofit.create(QuizApi.class);


    @Override
    public void getQuestions(QuestionsCallBack callBack) {
        Call<QuizResponse> call = quizApi.getQuestions(
                10,
                10,
                null);

        Log.d("ololo", "Url - "+ call.request().url());

        call.enqueue(new Callback<QuizResponse>() {
            @Override
            public void onResponse(Call<QuizResponse> call, Response<QuizResponse> response) {
                if (response.isSuccessful()){
                    if(response.body() != null){
                        callBack.onSuccess(response.body().getResults());
                    }else {
                        callBack.onFailure(new Exception("Response is Empty" + response.code()));
                    }
                }else {
                    callBack.onFailure(new Exception("Response is Empty" + response.code()));
                }
            }

            @Override
            public void onFailure(Call<QuizResponse> call, Throwable t) {
                callBack.onFailure(new Exception(t));
            }
        });

    }

    @Override
    public void getCategories(CategoryCallBack callBack) {
        Call<Category> call = quizApi.getCategoryQst();
        call.enqueue(new Callback<Category>() {
            @Override
            public void onResponse(Call<Category> call, Response<Category> response) {
                if(response.isSuccessful() && response.body() != null){
                    Log.e("kek", response.body().toString() );
                    callBack.onSuccess(response.body());
                }
            }

            @Override
            public void onFailure(Call<Category> call, Throwable t) {
                callBack.onFailure(new Exception(t));
            }
        });
    }


}
