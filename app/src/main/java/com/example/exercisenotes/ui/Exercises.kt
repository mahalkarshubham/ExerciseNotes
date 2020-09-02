package com.example.exercisenotes.ui

import android.util.Log
import androidx.compose.foundation.Icon
import androidx.compose.foundation.Text
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Description
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ContextAmbient
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.LiveData
import com.example.exercisenotes.data.Exercise
import com.example.exercisenotes.data.ExerciseDao
import com.example.exercisenotes.util.ArgsUtils
import com.google.android.gms.ads.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
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
                    CoroutineScope(IO).launch {
                        val id = dao.insert(Exercise("", ""))
                        ArgsUtils.args["exercise"] = dao.getExercise(id)
                    }
                    navTo(Screens.Edit)
                },
                icon = { Icon(Icons.Filled.Add) }
            )
        },
        bodyContent = {
            ExercisesContent(dao = dao, navTo = navTo)
        }
    )
}

@Composable
fun ExercisesContent(
    dao: ExerciseDao,
    navTo: (Screens) -> Unit
) {
    Column {
        ExercisesList(
            exercises = dao.getExercises(),
            editExercise = { exercise ->
                ArgsUtils.args["exercise"] = exercise
                navTo(Screens.Edit)
            }
        )
        BannerAd(Ads.exercisesBannerId)
    }
}

@Composable
fun ExercisesList(
    exercises: LiveData<List<Exercise>>,
    editExercise: (Exercise) -> Unit
) {
    val state = exercises.observeAsState()
    LazyColumnFor(items = state.value ?: emptyList()) { exercise ->
        ListItem(
            text = { Text(exercise.name.takeIf { it.isNotBlank() } ?: "Unnamed") },
            secondaryText = {
                Text(exercise.description.takeIf { it.isNotBlank() } ?: "No description")
            },
            modifier = Modifier.clickable(
                onClick = {
                    editExercise(exercise)
                }
            )
        )
    }
}

object Ads {
    const val appId = "ca-app-pub-7450355346929746~1347342351"

    const val exercisesBannerId = "ca-app-pub-7450355346929746/2632503867"
    const val testBannerId = "ca-app-pub-3940256099942544/6300978111"
}