package com.orozbek.quiz.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Category {

    @SerializedName("trivia_categories")
    @Expose
    List<TriviaCategory> triviaCategoryList = null;

    public List<TriviaCategory> getTriviaCategoryList() {
        return triviaCategoryList;
    }

    public void setTriviaCategoryList(List<TriviaCategory> triviaCategoryList) {
        this.triviaCategoryList = triviaCategoryList;
    }
}
