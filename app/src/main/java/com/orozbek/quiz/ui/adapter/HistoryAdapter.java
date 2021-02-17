package com.orozbek.quiz.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.orozbek.quiz.R;
import com.orozbek.quiz.databinding.DataBaseItemBinding;
import com.orozbek.quiz.databinding.QstItemsBinding;
import com.orozbek.quiz.interfaces.OnClickListnerHist;
import com.orozbek.quiz.model.QuizResult;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistVH> {

    private List<QuizResult> data;

    OnClickListnerHist onClickListnerHist;


    public void setOnClickListnerHist(OnClickListnerHist onClickListnerHist){
        this.onClickListnerHist = onClickListnerHist;
    }


    public void setData(List<QuizResult> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public HistoryAdapter() {
    }

    @NonNull
    @Override
    public HistVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HistVH(DataBaseItemBinding.bind(LayoutInflater.from(parent.getContext()).inflate(R.layout.data_base_item,parent,false)));
    }

    @Override
    public void onBindViewHolder(@NonNull HistVH holder, int position) {
        holder.onBind(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class HistVH extends RecyclerView.ViewHolder {
        DataBaseItemBinding binding;
        public HistVH(@NonNull DataBaseItemBinding binding) {
            super(binding.getRoot());
            binding.deleteHistBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onClickListnerHist.onClick(getAdapterPosition());
                }
            });
        }

        public void onBind(QuizResult quizResult) {
            binding.diffHistTv.setText(quizResult.getDifficulty());
            binding.catHistTv.setText(quizResult.getCategory());
            binding.correctAnswerHistTv.setText(String.valueOf(quizResult.getCorrectAnswerAmount()));
            binding.dateHistTv.setText(String.valueOf(quizResult.getCreatedAt()));
        }
    }
}
