package com.orozbek.quiz.ui.adapter;

import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.orozbek.quiz.R;
import com.orozbek.quiz.databinding.QstItemsBinding;
import com.orozbek.quiz.interfaces.OnAnswerBtnClick;
import com.orozbek.quiz.model.Question;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QstAdapter extends RecyclerView.Adapter<QstAdapter.QstVH> {

    private List<Question> questions = new ArrayList<>();
    private Question question = new Question();
    private OnAnswerBtnClick onAnswerBtnClick;

    public QstAdapter(OnAnswerBtnClick onAnswerBtnClick) {
        this.onAnswerBtnClick = onAnswerBtnClick;
    }

    public void updateList(List<Question> questions){
        this.questions = questions;
        notifyDataSetChanged();
        Log.e("TAG", "QstAdapter: "+ questions);
    }
@NonNull
    @Override
    public QstVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        QstItemsBinding binding = DataBindingUtil.bind(LayoutInflater.from(parent.getContext()).inflate(R.layout.qst_items,parent,false));
        return new QstVH(binding,onAnswerBtnClick);
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


        private OnAnswerBtnClick onAnswerBtnClick;
        private List<String> incorrectAnswer = new ArrayList<>();
        private  QstItemsBinding mbinding;
        public QstVH(@NonNull QstItemsBinding binding, OnAnswerBtnClick onAnswerBtnClick) {
            super(binding.getRoot());
            mbinding = binding;
            this.onAnswerBtnClick = onAnswerBtnClick;
            initListner();
        }

        private void initListner() {
            mbinding.firstBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onAnswerBtnClick.onAnswerClick(getAdapterPosition(),0);
                }
            });
            mbinding.secondBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onAnswerBtnClick.onAnswerClick(getAdapterPosition(),1);
                }
            });
            mbinding.thirdBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onAnswerBtnClick.onAnswerClick(getAdapterPosition(),2);
                }
            });
            mbinding.fourBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onAnswerBtnClick.onAnswerClick(getAdapterPosition(),3);
                }
            });
            mbinding.yesBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onAnswerBtnClick.onAnswerClick(getAdapterPosition(),0);
                }
            });
            mbinding.noBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onAnswerBtnClick.onAnswerClick(getAdapterPosition(),1);
                }
            });
        }


        public void OnBind(Question question) {
            clearHolder();
            if(question.isAnswerClick()){
                setButtonsEnabled(false);
            }else {
                setButtonsEnabled(true);
            }
            mbinding.setQstModel(question);
            incorrectAnswer.add(question.getCorrectAnswer());
            incorrectAnswer.addAll(question.getIncorrectAnswers());
            Collections.shuffle(incorrectAnswer);
            question.setAnswers(incorrectAnswer);

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
                mbinding.firstBtn.setText(question.getAnswers().get(0));
                mbinding.secondBtn.setText(question.getAnswers().get(1));
                mbinding.thirdBtn.setText(question.getAnswers().get(2));
                mbinding.fourBtn.setText(question.getAnswers().get(3));
            }
            if(question.isAnswerClick()){
                btnState(question);
            }
            incorrectAnswer.clear();
        }

        private void setButtonsEnabled(boolean enabled){
            mbinding.yesBtn.setEnabled(enabled);
            mbinding.noBtn.setEnabled(enabled);
            mbinding.firstBtn.setEnabled(enabled);
            mbinding.secondBtn.setEnabled(enabled);
            mbinding.thirdBtn.setEnabled(enabled);
            mbinding.fourBtn.setEnabled(enabled);
        }

        private void clearHolder() {
            mbinding.firstBtn.setBackgroundResource(R.drawable.qst_var_style);
            mbinding.secondBtn.setBackgroundResource(R.drawable.qst_var_style);
            mbinding.thirdBtn.setBackgroundResource(R.drawable.qst_var_style);
            mbinding.fourBtn.setBackgroundResource(R.drawable.qst_var_style);
            mbinding.yesBtn.setBackgroundResource(R.drawable.qst_var_style);
            mbinding.noBtn.setBackgroundResource(R.drawable.qst_var_style);
        }

        private void btnState(Question question) {
//            if (question.getSelectAnswerPosition() != 0){
                switch (question.getSelectAnswerPosition()){
                    case 0:
                        if (question.getCorrectAnswer().equals(question.getAnswers().get(0))){
                            mbinding.firstBtn.setBackgroundResource(R.drawable.correct_answer_btn_style);
                            mbinding.yesBtn.setBackgroundResource(R.drawable.correct_answer_btn_style);
                            Log.e("TAG", "btnState: correct" );
                        }else {
                            mbinding.firstBtn.setBackgroundResource(R.drawable.false_answer_btn_style);
                            mbinding.yesBtn.setBackgroundResource(R.drawable.false_answer_btn_style);
                            Log.e("TAG", "btnState: incorrect");
                        }
                        break;
                    case 1:
                        if (question.getCorrectAnswer().equals(question.getAnswers().get(0))){
                            mbinding.secondBtn.setBackgroundResource(R.drawable.correct_answer_btn_style);
                            mbinding.noBtn.setBackgroundResource(R.drawable.correct_answer_btn_style);
                            Log.e("TAG", "btnState: correct" );
                        }else {
                            mbinding.secondBtn.setBackgroundResource(R.drawable.false_answer_btn_style);
                            mbinding.noBtn.setBackgroundResource(R.drawable.false_answer_btn_style);
                            Log.e("TAG", "btnState: incorrect");
                        }
                        break;
                    case 2:
                        if (question.getCorrectAnswer().equals(question.getAnswers().get(0))){
                            mbinding.thirdBtn.setBackgroundResource(R.drawable.correct_answer_btn_style);
                            Log.e("TAG", "btnState: correct" );
                        }else {
                            mbinding.thirdBtn.setBackgroundResource(R.drawable.false_answer_btn_style);
                            Log.e("TAG", "btnState: incorrect");
                        }
                        break;
                    case 3:
                        if (question.getCorrectAnswer().equals(question.getAnswers().get(0))){
                            mbinding.fourBtn.setBackgroundResource(R.drawable.correct_answer_btn_style);
                            Log.e("TAG", "btnState: correct" );
                        }else {
                            mbinding.fourBtn.setBackgroundResource(R.drawable.false_answer_btn_style);
                            Log.e("TAG", "btnState: incorrect");
                        }
                        break;
//                }
            }
        }

    }
}
