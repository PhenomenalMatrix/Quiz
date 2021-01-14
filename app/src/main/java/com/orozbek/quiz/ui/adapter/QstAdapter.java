package com.orozbek.quiz.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.orozbek.quiz.R;
import com.orozbek.quiz.data.local.QstModel;
import com.orozbek.quiz.databinding.QstItemsBinding;

import java.util.ArrayList;

public class QstAdapter extends RecyclerView.Adapter<QstAdapter.QstVH> {

    private ArrayList<QstModel> questions;

    public QstAdapter(ArrayList<QstModel> questions) {
        this.questions = questions;
    }

    @NonNull
    @Override
    public QstVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        QstItemsBinding binding = DataBindingUtil.bind(LayoutInflater.from(parent.getContext()).inflate(R.layout.qst_items,parent,false));
        return new QstVH(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull QstVH holder, int position) {
//        holder.mbinding.setQst(questions.get(position));
        holder.OnBind(questions.get(position));
    }

    @Override
    public int getItemCount() {
        return questions.size();
    }

    public class QstVH extends RecyclerView.ViewHolder {

        private  QstItemsBinding mbinding;
        public QstVH(@NonNull QstItemsBinding binding) {
            super(binding.getRoot());
            mbinding = binding;
        }


        public void OnBind(QstModel qstModel) {
            mbinding.setQst(qstModel);
            if (questions.get(getAdapterPosition()).isMulty() == false){
                mbinding.multiplyBtnsConstraint.setVisibility(View.INVISIBLE);
                mbinding.btnsConstraint.setVisibility(View.VISIBLE);
                mbinding.yesBtn.setText(qstModel.getVariants()[0]);
                mbinding.noBtn.setText(qstModel.getVariants()[1]);
            } else {
                mbinding.firstBtn.setText(qstModel.getVariants()[0]);
                mbinding.secondBtn.setText(qstModel.getVariants()[1]);
                mbinding.thirdBtn.setText(qstModel.getVariants()[2]);
                mbinding.fourBtn.setText(qstModel.getVariants()[3]);
            }
        }

    }
}
