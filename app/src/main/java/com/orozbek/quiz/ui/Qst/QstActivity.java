package com.orozbek.quiz.ui.Qst;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.SnapHelper;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.orozbek.quiz.R;
import com.orozbek.quiz.databinding.ActivityQstBinding;
import com.orozbek.quiz.interfaces.OnAnswerBtnClick;
import com.orozbek.quiz.model.Question;
import com.orozbek.quiz.model.QuizResult;
import com.orozbek.quiz.ui.adapter.QstAdapter;
import com.orozbek.quiz.ui.result.ResultActivity;

import java.util.ArrayList;
import java.util.List;

public class QstActivity extends AppCompatActivity implements OnAnswerBtnClick {

    private ActivityQstBinding binding;
    private List<Question> qsts = new ArrayList<>();
    private QstViewModel qstViewModel;
    private QstAdapter adapter;
    private String difficult;
    private int amount;
    private int categoryId;
    private String categoryNameTitle;
    private QuizResult quizResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        qstViewModel = new ViewModelProvider(this).get(QstViewModel.class);
        difficult = getIntent().getStringExtra("diffKey");
        amount = getIntent().getIntExtra("amountKey",0);
        categoryId = getIntent().getIntExtra("catKey",0);
        categoryNameTitle = getIntent().getStringExtra("catNameKey");
        binding = DataBindingUtil.setContentView(this,R.layout.activity_qst);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        qstViewModel.getQst(amount,categoryId,difficult);
        qstViewModel.quizResp.observeForever(new Observer<List<Question>>() {
            @Override
            public void onChanged(List<Question> questions) {
                Log.e("TAG", "onChanged: "+ questions );
                qsts.addAll(questions);
                adapter.updateList(qsts);
                getPosition();
                Log.d("TAG", "onChanged: qstsAdd"+ qsts);
            }
        });
        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(binding.qstRecycler);
        adapter = new QstAdapter(this);
        binding.qstRecycler.setAdapter(adapter);
        binding.qstRecycler.setLayoutManager(layoutManager);
        binding.progressBar.setMax(qsts.size());
        binding.progressBar.setProgress(adapter.getItemCount());
        binding.progressTv.setText(adapter.getItemCount()+"/"+qsts.size());
        binding.skipBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                qstViewModel.skipClick();

            }
        });
        binding.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                qstViewModel.onBackPressed();
            }
        });
        Strat();
    }

    private void Strat() {
        qstViewModel.startResult.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                Intent intent = new Intent(QstActivity.this,ResultActivity.class);
                intent.putExtra("counter",qstViewModel.correctAnswerCounter);
                intent.putExtra("amountRepo",amount);
                intent.putExtra("catRepo",categoryNameTitle);
                intent.putExtra("diffRepo",difficult);
                binding.skipBtn.setText("finish");
                startActivity(intent);
                finish();
            }
        });
    }

    private void getPosition() {
        qstViewModel.currentQuestionPosition.observeForever(new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                binding.qstRecycler.scrollToPosition(integer);
                binding.progressTv.setText(integer+1+" / "+ amount);
                binding.progressBar.setMax(amount);
                binding.progressBar.setProgress(integer+1);
            }
        });
    }



    @Override
    public void onAnswerClick(int position, int selectAnswerPosition) {
        qstViewModel.onAnswerClick(position,selectAnswerPosition);
    }

    @Override
    public void correctAnswer(boolean b) {
        if (b){
            qstViewModel.correctAnswer();
        }
    }
}