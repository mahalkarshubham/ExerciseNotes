package com.example.exercisenotes.ui

import androidx.compose.foundation.Icon
import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
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
            EditExerciseContent(exercise)
        }
    )
}

@Composable
fun EditExerciseContent(
    exercise: Exercise
) {
    val name = remember { mutableStateOf(exercise.name) }
    val desc = remember { mutableStateOf(exercise.description) }
    ScrollableColumn(modifier = Modifier.padding(start = 16.dp, end = 16.dp)) {
        TextField(
            label = { Text("Name") },
            value = name.value,
            onValueChange = { name.value = it },
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp, top = 16.dp)
        )
        TextField(
            label = { Text("Description") },
            value = desc.value,
            onValueChange = { desc.value = it },
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
        )
    }
}

@Composable
@Preview
fun EditExercisePreview() {
    MaterialTheme {
        EditExercise(Exercise("Lunge", ""), {})
    }
}
