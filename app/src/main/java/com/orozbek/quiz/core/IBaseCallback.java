package com.orozbek.quiz.core;

public interface IBaseCallback<T> {

    void onSuccess(T result);

    void onFailure(Exception e);
}
