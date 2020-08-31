package com.example.exercisenotes

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
import androidx.compose.material.icons.filled.Description
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.setContent
import androidx.room.Room
import androidx.ui.tooling.preview.Preview
import com.example.exercisenotes.data.AppDatabase

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
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {
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
                onClick = {},
                icon = { Icon(Icons.Filled.Add) }
            )
        },
        bodyContent = {
            ExercisesList()
        }
    )
}

@Composable
@Preview
fun MainScreenPreview() {
    MaterialTheme {
        MainScreen()
    }
}

@Composable
fun ExercisesList() {
    val mockExercises = listOf("Push Up", "Lunges", "Chest Press")
    LazyColumnFor(items = mockExercises) { exercise ->
        ListItem(
            text = { Text(exercise) },
            modifier = Modifier.clickable(onClick = {})
        )
    }
}