package com.orozbek.quiz.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.SnapHelper;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.orozbek.quiz.R;
import com.orozbek.quiz.data.QstRepo;
import com.orozbek.quiz.data.local.QstModel;
import com.orozbek.quiz.databinding.ActivityQstBinding;
import com.orozbek.quiz.ui.adapter.QstAdapter;

import java.util.ArrayList;

public class QstActivity extends AppCompatActivity {

    private ActivityQstBinding binding;
    private ArrayList<QstModel> qsts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_qst);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        qsts = QstRepo.getQsts();
        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(binding.qstRecycler);
        binding.qstRecycler.setAdapter(new QstAdapter(qsts));
        binding.qstRecycler.setLayoutManager(layoutManager);
        binding.skipBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i=0;
                binding.qstRecycler.scrollToPosition(i);
                i++;
                Log.e("TAG", "onClick: "+ i );
            }
        });
    }
}