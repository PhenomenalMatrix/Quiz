package com.orozbek.quiz.ui.hitstory;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;

import com.orozbek.quiz.QuizApp;
import com.orozbek.quiz.R;
import com.orozbek.quiz.databinding.HistoryFragmentBinding;
import com.orozbek.quiz.interfaces.OnClickListnerHist;
import com.orozbek.quiz.model.QuizResult;
import com.orozbek.quiz.ui.adapter.HistoryAdapter;

import java.util.ArrayList;
import java.util.List;

public class HistoryFragment extends Fragment {

    private HistoryViewModel mViewModel;
    private HistoryFragmentBinding binding;
    private ArrayList<QuizResult> modelList = new ArrayList<>();
    private HistoryAdapter histAdapter;

    public static HistoryFragment newInstance() {
        return new HistoryFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = HistoryFragmentBinding.bind(inflater.inflate(R.layout.history_fragment, container, false));

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(HistoryViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.histRecycler.setAdapter(histAdapter);
        binding.histRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        histAdapter = new HistoryAdapter();
        histAdapter.setData(modelList);
        QuizApp.quizDataBase.quizDao().getAll().observe(getViewLifecycleOwner(), new Observer<List<QuizResult>>() {
            @Override
            public void onChanged(List<QuizResult> quizResults) {
                modelList.clear();
                modelList.addAll(quizResults);
            }
        });

        histAdapter.setOnClickListnerHist(new OnClickListnerHist() {
            @Override
            public void onClick(int position) {
                showAlert(modelList.get(position));
            }
            private void showAlert(final QuizResult quizResult){
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setMessage("Detete ?")
                        .setNegativeButton("No", null)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                QuizApp.quizDataBase.quizDao().delete(quizResult);
                            }
                        });
                builder.show();
            }
        });
    }
}