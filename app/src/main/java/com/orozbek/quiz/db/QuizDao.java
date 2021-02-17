package com.orozbek.quiz.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.orozbek.quiz.model.QuizResult;

import java.util.List;

@Dao
public interface QuizDao {

    @Insert
    long insert(QuizResult quizResult);

    @Query("SELECT * FROM qresult WHERE id =:id")
    QuizResult getById(int id);

    @Delete
    void delete(QuizResult quizResult);

    @Query("SELECT * FROM qresult")
    LiveData<List<QuizResult>> getAll();

    @Query("DELETE FROM qresult")
    void deleteAll();

}
