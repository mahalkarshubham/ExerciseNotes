package com.example.exercisenotes.ui

import androidx.compose.foundation.Icon
import androidx.compose.foundation.Text
import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Description
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.LiveData
import com.example.exercisenotes.data.Exercise
import com.example.exercisenotes.data.ExerciseDao
import com.example.exercisenotes.util.ArgsUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun Exercises(
    dao: ExerciseDao,
    navTo: (Screens) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("ExerciseNotes") },
                navigationIcon = {
                    IconButton(
                        onClick = {},
                        icon = { Icon(Icons.Filled.Description) }
                    )
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    CoroutineScope(Dispatchers.IO).launch {
                        // TODO: Real implementation; This is just for debugging
                        dao.insert(Exercise("${(0..1000).random()}", "${(0..10000).random()}"))
                    }
                },
                icon = { Icon(Icons.Filled.Add) }
            )
        },
        bodyContent = {
            ExercisesList(
                exercises = dao.getExercises(),
                editExercise = { exercise ->
                    ArgsUtils.args["exercise"] = exercise
                    navTo(Screens.Edit)
                }
            )
        }
    )
}

@Composable
fun ExercisesList(
    exercises: LiveData<List<Exercise>>,
    editExercise: (Exercise) -> Unit
) {
    val state = exercises.observeAsState()
    LazyColumnFor(items = state.value ?: emptyList()) { exercise ->
        ListItem(
            text = { Text(exercise.name) },
            secondaryText = { Text(exercise.description) },
            modifier = Modifier.clickable(
                onClick = {
                    editExercise(exercise)
                },
                onLongClick = {

                }
            )
        )
    }
}