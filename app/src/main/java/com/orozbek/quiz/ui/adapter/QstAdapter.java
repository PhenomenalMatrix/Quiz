package com.orozbek.quiz.ui.adapter;

import android.graphics.LightingColorFilter;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.orozbek.quiz.R;
import com.orozbek.quiz.data.local.QstModel;
import com.orozbek.quiz.databinding.QstItemsBinding;
import com.orozbek.quiz.interfaces.OnAnswerBtnClick;
import com.orozbek.quiz.interfaces.OnItemClickListner;
import com.orozbek.quiz.model.Question;
import com.orozbek.quiz.ui.Qst.QstActivity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class QstAdapter extends RecyclerView.Adapter<QstAdapter.QstVH> {

    private OnItemClickListner onItemClickListner;
    private List<Question> questions = new ArrayList<>();
    private Question question = new Question();
    private OnAnswerBtnClick onAnswerBtnClick;

    public QstAdapter() {
    }

    public void updateList(List<Question> questions){
        this.questions = questions;
        notifyDataSetChanged();
        Log.e("TAG", "QstAdapter: "+ questions);
    }
    public void initListner(OnAnswerBtnClick onAnswerBtnClick){
        this.onAnswerBtnClick = onAnswerBtnClick;
    }
    public void setOnItemClickListner(OnItemClickListner onItemClickListner){
        this.onItemClickListner = onItemClickListner;
    }

    @NonNull
    @Override
    public QstVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        QstItemsBinding binding = DataBindingUtil.bind(LayoutInflater.from(parent.getContext()).inflate(R.layout.qst_items,parent,false));
        return new QstVH(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull QstVH holder, int position) {
        holder.OnBind(questions.get(position));
    }

    @Override
    public int getItemCount() {
        return questions.size();
    }

    public class QstVH extends RecyclerView.ViewHolder {

        private List<String> incorrectAnswer = new ArrayList<>();
        private  QstItemsBinding mbinding;
        public QstVH(@NonNull QstItemsBinding binding) {
            super(binding.getRoot());
            mbinding = binding;
            onClick();
        }

        private void onAnswerButtonClick(Button button,Integer selectedAnswerPosition) {
//            if (question.isAnswerClick() == false) {
                if (questions.get(getAdapterPosition()).getCorrectAnswer().equals(incorrectAnswer.get(selectedAnswerPosition))) {
                    button.setBackgroundResource(R.drawable.correct_answer_btn_style);
                } else {
                    button.setBackgroundResource(R.drawable.false_answer_btn_style);
                }
                onAnswerBtnClick.onAnswerClick(getAdapterPosition());
//            }
        }

        private void onClick() {
            mbinding.firstBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListner.onItemClick(getAdapterPosition());
                    onAnswerButtonClick(mbinding.firstBtn,0);
                    question.setAnswerClick(true);
                }
            });
            mbinding.secondBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListner.onItemClick(getAdapterPosition());
                    onAnswerButtonClick(mbinding.secondBtn,1);
                    question.setAnswerClick(true);
                }
            });
            mbinding.thirdBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListner.onItemClick(getAdapterPosition());
                    onAnswerButtonClick(mbinding.thirdBtn,2);
                    question.setAnswerClick(true);
                }
            });
            mbinding.fourBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListner.onItemClick(getAdapterPosition());
                    onAnswerButtonClick(mbinding.fourBtn,3);
                    question.setAnswerClick(true);
                }
            });
            mbinding.yesBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListner.onItemClick(getAdapterPosition());
                    onAnswerButtonClick(mbinding.yesBtn,0);
                    question.setAnswerClick(true);
                }
            });
            mbinding.noBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListner.onItemClick(getAdapterPosition());
                    onAnswerButtonClick(mbinding.noBtn,1);
                    question.setAnswerClick(true);
                }
            });
        }

        public void OnBind(Question question) {
            mbinding.setQstModel(question);
            incorrectAnswer.add(question.getCorrectAnswer());
            incorrectAnswer.addAll(question.getIncorrectAnswers());
            Collections.shuffle(incorrectAnswer);
            mbinding.qstTv.setText(Html.fromHtml(question.getQuestion()));
            if(question.getType().equals("boolean") ){
                mbinding.multiplyBtnsConstraint.setVisibility(View.INVISIBLE);
                mbinding.btnsConstraint.setVisibility(View.VISIBLE);
                mbinding.yesBtn.setText("true");
                mbinding.noBtn.setText("false");
            }
            if(question.getType().equals("multiple")) {
                mbinding.btnsConstraint.setVisibility(View.GONE);
                mbinding.multiplyBtnsConstraint.setVisibility(View.VISIBLE);
                mbinding.firstBtn.setText(incorrectAnswer.get(0));
                mbinding.secondBtn.setText(incorrectAnswer.get(1));
                mbinding.thirdBtn.setText(incorrectAnswer.get(2));
                mbinding.fourBtn.setText(incorrectAnswer.get(3));
            }
        }
    }
}
