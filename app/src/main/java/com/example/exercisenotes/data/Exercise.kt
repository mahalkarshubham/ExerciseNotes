package com.example.exercisenotes.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Exercise(
    val name: String,
    val description: String,

    @PrimaryKey(autoGenerate = true)
    val id: Long = 0
)