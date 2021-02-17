package com.orozbek.quiz.ui.result;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;

import com.orozbek.quiz.R;
import com.orozbek.quiz.databinding.ActivityResultBinding;
import com.orozbek.quiz.model.QuizResult;
import com.orozbek.quiz.ui.Qst.QstViewModel;

import java.util.ArrayList;
import java.util.Calendar;

public class ResultActivity extends AppCompatActivity {

    private ActivityResultBinding binding;
    private ResultViewModel mViewModel;
    private String difficult;
    private QuizResult quizResult;
    private int amount;
    private int correctCounter;
    private String category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_result);
        mViewModel = new ViewModelProvider(this).get(ResultViewModel.class);

        correctCounter = getIntent().getIntExtra("counter",0);
        difficult = getIntent().getStringExtra("diffRepo");
        amount = getIntent().getIntExtra("amountRepo",0);
        category = getIntent().getStringExtra("catRepo");
        binding.diffInfTv.setText(difficult);
        binding.correctAnswersInfTv.setText(correctCounter + "/" + amount);
        int correctAnswerPercent =(int)((double)correctCounter/amount * 100);
        quizResult = new QuizResult(category,difficult,correctCounter, Calendar.getInstance().getTime(),new ArrayList<>());
        binding.resultsInfTv.setText(correctAnswerPercent + "%");
        binding.catTitleTv.setText(category);
        binding.finishBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewModel.saveResult(quizResult);
                finish();
            }
        });
    }
}