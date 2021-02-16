package com.orozbek.quiz.interfaces;

public interface OnAnswerBtnClick {
    void onAnswerClick(int position, int selectAnswerPosition);
    void correctAnswer(boolean b);
}
