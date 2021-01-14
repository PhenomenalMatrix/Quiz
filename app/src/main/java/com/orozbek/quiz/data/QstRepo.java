package com.orozbek.quiz.data;

import com.orozbek.quiz.data.local.QstModel;

import java.util.ArrayList;
import java.util.List;

public class QstRepo {
    public static ArrayList<QstModel> qsts;
    public static ArrayList<QstModel> getQsts(){
        qsts = new ArrayList<>();
        qsts.add(new QstModel("parts of the train -1 ",new String[]{"compartment","conductor","aisle","timetable"},true));
        qsts.add(new QstModel("parts of the train - 2",new String[]{"yes","no"},false));
        qsts.add(new QstModel("parts of the train - 3",new String[]{"compartment","conductor","aisle","timetable"},true));
        qsts.add(new QstModel("parts of the train - 4",new String[]{"yes","no"},false));
        return qsts;
    }
}
