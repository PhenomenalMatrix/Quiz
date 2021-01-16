package com.orozbek.quiz.ui.main;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SeekBar;

import com.orozbek.quiz.R;
import com.orozbek.quiz.databinding.MainFragmentBinding;
import com.orozbek.quiz.model.Category;
import com.orozbek.quiz.model.TriviaCategory;
import com.orozbek.quiz.ui.QstActivity;

import java.util.ArrayList;
import java.util.List;

public class MainFragment extends Fragment {

    private MainViewModel mViewModel;
    private MainFragmentBinding binding;
    private Integer categoryCount;
    private String categoryNameTitle;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
          binding = DataBindingUtil.inflate(inflater,R.layout.main_fragment, container, false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        mViewModel.getCategory();
        binding.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(),QstActivity.class));
            }
        });
        binding.seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                binding.tvQstAmountNumbers.setText(String.valueOf(i));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        mViewModel.category.observeForever(new Observer<Category>() {
            @Override
            public void onChanged(Category category) {
                List<TriviaCategory> categoryList = category.getTriviaCategoryList();
                List<String> nameCategory = new ArrayList<>();
                for (TriviaCategory triviaCategory:
                        categoryList) {
                    nameCategory.add(triviaCategory.getName());
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(), R.layout.support_simple_spinner_dropdown_item,nameCategory);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                binding.spinnerCategory.setAdapter(adapter);
                binding.spinnerCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        categoryCount = category.getTriviaCategoryList().get(i).getId();
                        categoryNameTitle = category.getTriviaCategoryList().get(i).getName();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
            }
        });
    }
}