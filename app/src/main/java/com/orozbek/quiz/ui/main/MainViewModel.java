package com.orozbek.quiz.ui.main;

import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {

    public MutableLiveData <String> nameData = new MutableLiveData<>();

    public void getName() {
        nameData.setValue("Victory");
    }

    public void getName2(){
        nameData.setValue("Ololo");
    }
}
