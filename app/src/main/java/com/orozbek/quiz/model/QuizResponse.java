package com.orozbek.quiz.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;


public class QuizResponse {

    @SerializedName("response_mode")
    @Expose
    private int responseCode;
    @SerializedName("results")
    @Expose
    List<Question> results = null;

    public List<Question> getResults() {
        return results;
    }

    public void setResults(List<Question> results) {
        this.results = results;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }


}
