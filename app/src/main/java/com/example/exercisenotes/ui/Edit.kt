package com.example.exercisenotes.ui

import androidx.compose.foundation.Icon
import androidx.compose.foundation.Text
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.ui.tooling.preview.Preview
import com.example.exercisenotes.data.Exercise

@Composable
fun EditExercise(
    exercise: Exercise,
    navTo: (Screens) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Edit Exercise") },
                navigationIcon = {
                    IconButton(
                        onClick = { navTo(Screens.Exercises) },
                        icon = { Icon(Icons.Filled.ArrowBack) }
                    )
                }
            )
        },
        bodyContent = {
            Text(exercise.name)
        }
    )
}

@Composable
@Preview
fun EditExercisePreview() {
    MaterialTheme {
        EditExercise(Exercise("Lunge", ""), {})
    }
}
