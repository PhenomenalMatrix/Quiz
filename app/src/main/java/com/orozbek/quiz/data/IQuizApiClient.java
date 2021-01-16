package com.orozbek.quiz.data;

import com.orozbek.quiz.core.IBaseCallback;
import com.orozbek.quiz.model.Category;
import com.orozbek.quiz.model.Question;

import java.util.List;

public interface IQuizApiClient {

    void getQuestions(QuestionsCallBack callBack);
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
