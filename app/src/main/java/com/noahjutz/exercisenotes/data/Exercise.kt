package com.noahjutz.exercisenotes.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Exercise(
    var name: String,
    var description: String,

    @PrimaryKey(autoGenerate = true)
    val id: Long = 0
)