package com.example.exercisenotes.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ExerciseDao {
    @Query("SELECT * FROM exercise")
    fun getExercises(): LiveData<List<Exercise>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(exercise: Exercise): Long
}