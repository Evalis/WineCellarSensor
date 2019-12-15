package com.example.winecellarsensor.database;

import com.example.winecellarsensor.model.Warning;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface WarningDao {

    @Insert
    void insert(Warning warning);

    @Update
    void update(Warning warning);

    @Delete
    void delete(Warning warning);

    @Query("DELETE FROM warning_table")
    void deleteAllWarnings();

    @Query("SELECT * FROM warning_table ORDER BY date DESC")
    LiveData<List<Warning>> getAllWarnings();
}
