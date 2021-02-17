package com.orozbek.quiz.db.converter;

import androidx.annotation.Nullable;
import androidx.room.TypeConverter;

import java.util.Date;

public class DateConverter {

    @TypeConverter
    public static Long toTimeStump(@Nullable Date date){
        if(date == null){
            return null;
        }
        return date.getTime();
    }

    @TypeConverter
    public static Date fromTimeStump(@Nullable Long timeStump){
        if(timeStump == null){
            return null;
        }
        return new Date(timeStump);

    }

}
