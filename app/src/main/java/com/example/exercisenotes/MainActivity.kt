package com.example.exercisenotes

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Icon
import androidx.compose.foundation.Text
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Description
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.setContent
import androidx.ui.tooling.preview.Preview

class MainActivity : AppCompatActivity() {
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