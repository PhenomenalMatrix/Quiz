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

    List<QuizResult> data;

    OnClickListnerHist onClickListnerHist;


    public void setOnClickListnerHist(OnClickListnerHist onClickListnerHist){
        this.onClickListnerHist = onClickListnerHist;
    }


    public void setData(List<QuizResult> data) {
        this.data = data;
    }

    public HistoryAdapter() {
    }

    @NonNull
    @Override
    public HistVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HistVH(LayoutInflater.from(parent.getContext()).inflate(R.layout.data_base_item,parent,false));
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
        private TextView diffHistTv;
        private TextView catHistTv;
        private TextView correctAnswerHistTv;
        private TextView dateHistTv;
        private Button deleteHistBtn;
        private DataBaseItemBinding item;
        public HistVH(@NonNull View binding) {
            super(binding);
            initViews(binding);
            deleteHistBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onClickListnerHist.onClick(getAdapterPosition());
                }
            });
        }

        private void initViews(View binding) {
            diffHistTv = binding.findViewById(R.id.diff_hist_tv);
            catHistTv = binding.findViewById(R.id.cat_hist_tv);
            correctAnswerHistTv = binding.findViewById(R.id.correct_answer_hist_tv);
            dateHistTv = binding.findViewById(R.id.date_hist_tv);
        }

        public void onBind(QuizResult quizResult) {
            diffHistTv.setText(quizResult.getDifficulty());
            catHistTv.setText(quizResult.getCategory());
            correctAnswerHistTv.setText(String.valueOf(quizResult.getCorrectAnswerAmount()));
            dateHistTv.setText(String.valueOf(quizResult.getCreatedAt()));
        }
    }
}
