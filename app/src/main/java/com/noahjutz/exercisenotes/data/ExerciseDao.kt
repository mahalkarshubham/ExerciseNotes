package com.noahjutz.exercisenotes.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ExerciseDao {
    @Query("SELECT * FROM exercise")
    fun getExercises(): LiveData<List<Exercise>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(exercise: Exercise): Long

    @Query("SELECT * FROM exercise WHERE id == :id")
    fun getExercise(id: Long): Exercise

    @Delete
    fun delete(exercise: Exercise)
}