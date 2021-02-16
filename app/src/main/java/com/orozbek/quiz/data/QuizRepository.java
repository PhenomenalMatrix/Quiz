package com.orozbek.quiz.data;

import com.orozbek.quiz.model.Question;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QuizRepository implements IQuizApiClient  {

    private IQuizApiClient quizApiClient;

    public QuizRepository(IQuizApiClient quizApiClient) {
        this.quizApiClient = quizApiClient;
    }

    public void getQuestionsRepo(IQuizApiClient.QuestionsCallBack callBack,int amount,int categoryId,String difficulty){
        quizApiClient.getQuestions(new IQuizApiClient.QuestionsCallBack(){
            @Override
            public void onSuccess(List<Question> result) {
                for (int i = 0; i < result.size(); i++) {
                    result.set(i,shuffleAnswer(result.get(i)));
                }
                callBack.onSuccess(result);
            }

            @Override
            public void onFailure(Exception e) {

            }
        },amount,categoryId,difficulty);
    }

    @Override
    public void getQuestions(QuestionsCallBack callBack, int amount, int categoryId, String difficulty) {

    }

    @Override
    public void getCategories(CategoryCallBack callBack) {

    }

    private Question shuffleAnswer(Question question){
        ArrayList<String> answers = new ArrayList<>();
        answers.add(question.getCorrectAnswer());
        answers.addAll(question.getIncorrectAnswers());
        Collections.shuffle(answers);
        question.setAnswers(answers);
        return question;
    }
}
