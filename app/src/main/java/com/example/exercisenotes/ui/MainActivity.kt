package com.example.exercisenotes.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Icon
import androidx.compose.foundation.Text
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Description
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.setContent
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import androidx.ui.tooling.preview.Preview
import com.example.exercisenotes.data.AppDatabase
import com.example.exercisenotes.data.Exercise
import com.example.exercisenotes.data.ExerciseDao
import com.example.exercisenotes.util.ArgsUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val db by lazy {
        Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "database-name"
        ).build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme(colors = if (isSystemInDarkTheme()) darkColors() else lightColors()) {
                MainScreen(
                    dao = db.dao
                )
            }
        }
    }
}

enum class Screens {
    Exercises, Edit
}

@Composable
fun MainScreen(
    dao: ExerciseDao
) {
    val currentScreen = remember { mutableStateOf(Screens.Exercises) }
    val navTo = { s: Screens ->
        currentScreen.value = s
    }
    when (currentScreen.value) {
        Screens.Exercises -> Exercises(dao, navTo)
        Screens.Edit -> EditExercise(
            exercise = ArgsUtils.args["exercise"] as Exercise,
            navTo = navTo
        )
    }
}

@Composable
@Preview
fun MainScreenPreview() {
    MaterialTheme {
        MainScreen(object : ExerciseDao {
            override fun getExercises(): LiveData<List<Exercise>> =
                MutableLiveData(listOf(Exercise("Hello", "World"), Exercise("Lunges", "Cool")))

            override fun insert(exercise: Exercise): Long = -1
        })
    }
}