package com.orozbek.quiz.data;

import com.orozbek.quiz.core.IBaseCallback;
import com.orozbek.quiz.model.Category;
import com.orozbek.quiz.model.Question;
import com.orozbek.quiz.model.QuizResponse;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public interface IQuizApiClient {

    void getQuestions(QuestionsCallBack callBack, int amount, int categoryId, String difficulty);
    void getCategories(CategoryCallBack callBack);

    interface QuestionsCallBack extends IBaseCallback<List<Question>> {
        @Override
        void onSuccess(List<Question> result);

        @Override
        void onFailure(Exception e);
    }

    interface CategoryCallBack extends IBaseCallback<Category>{
        @Override
        void onSuccess(Category categories);

        @Override
        void onFailure(Exception e);
    }
}
