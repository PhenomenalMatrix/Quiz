package com.orozbek.quiz.data;

import com.orozbek.quiz.model.Category;
import com.orozbek.quiz.model.QuizResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface QuizApi {


    @GET("api.php")
    Call<QuizResponse> getQuestions(
            @Query("amount") int amount,
            @Query("category") int category,
            @Query("difficulty") String difficulty
    );

    @GET("api_category.php")
    Call<Category>getCategoryQst();
}
