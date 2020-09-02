package com.noahjutz.exercisenotes.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [Exercise::class],
    version = 2
)
abstract class AppDatabase : RoomDatabase() {
    abstract val dao: ExerciseDao
}