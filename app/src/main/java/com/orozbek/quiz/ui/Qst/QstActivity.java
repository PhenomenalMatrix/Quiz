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

import com.orozbek.quiz.QuizApp;
import com.orozbek.quiz.R;
import com.orozbek.quiz.data.local.QstRepo;
import com.orozbek.quiz.data.local.QstModel;
import com.orozbek.quiz.databinding.ActivityQstBinding;
import com.orozbek.quiz.interfaces.OnItemClickListner;
import com.orozbek.quiz.model.Question;
import com.orozbek.quiz.ui.adapter.QstAdapter;
import com.orozbek.quiz.ui.main.MainViewModel;

import java.util.ArrayList;
import java.util.List;

public class QstActivity extends AppCompatActivity implements OnItemClickListner {

    private ActivityQstBinding binding;
    private List<Question> qsts = new ArrayList<>();
    private QstViewModel qstViewModel;
    private QstAdapter adapter;
    private String difficult;
    private int amount;
    private int categoryId;
    private String categoryNameTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        qstViewModel = new ViewModelProvider(this).get(QstViewModel.class);
        difficult = getIntent().getStringExtra("diffKey");
        amount = getIntent().getIntExtra("amountKey",0);
        categoryId = getIntent().getIntExtra("catKey",0);
        categoryNameTitle = getIntent().getStringExtra("catNameKey");
        Log.e("TAG", "onCreate: "+" "+difficult+" "+amount+" "+categoryId+" "+categoryNameTitle);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_qst);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        qstViewModel.getQst(amount,categoryId,difficult);
        qstViewModel.quizResp.observeForever(new Observer<List<Question>>() {
            @Override
            public void onChanged(List<Question> questions) {
                Log.e("TAG", "onChanged: "+ questions );
                qsts.addAll(questions);
                adapter.updateList(qsts);

                Log.d("TAG", "onChanged: qstsAdd"+ qsts);
            }
        });
        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(binding.qstRecycler);
        adapter = new QstAdapter();
        binding.qstRecycler.setAdapter(adapter);
        adapter.setOnItemClickListner(this);
        binding.qstRecycler.setLayoutManager(layoutManager);
        binding.progressBar.setMax(qsts.size());
        binding.progressBar.setProgress(adapter.getItemCount());
        binding.progressTv.setText(adapter.getItemCount()+"/"+qsts.size());
    }

    @Override
    public void onItemClick(int position) {
        qstViewModel.onItemClick(position);
    }

}