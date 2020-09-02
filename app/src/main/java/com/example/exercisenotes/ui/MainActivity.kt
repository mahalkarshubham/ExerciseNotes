package com.example.exercisenotes.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.setContent
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import androidx.ui.tooling.preview.Preview
import com.example.exercisenotes.data.AppDatabase
import com.example.exercisenotes.data.Exercise
import com.example.exercisenotes.data.ExerciseDao
import com.example.exercisenotes.util.Ads
import com.example.exercisenotes.util.ArgsUtils
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.InterstitialAd
import com.google.android.gms.ads.MobileAds

class MainActivity : AppCompatActivity() {

    private val db by lazy {
        Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "database-name"
        ).build()
    }

    private lateinit var interstitialAd: InterstitialAd

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MobileAds.initialize(this)
        interstitialAd = InterstitialAd(this).apply { adUnitId = Ads.myInterstitialId }
        setContent {
            MaterialTheme(colors = if (isSystemInDarkTheme()) darkColors() else lightColors()) {
                MainScreen(
                    dao = db.dao,
                    loadInterstitial = ::loadInterstitial
                )
            }
        }
    }

    private fun loadInterstitial() {
        interstitialAd.loadAd(AdRequest.Builder().build())
    }
}

enum class Screens {
    Exercises, Edit
}

@Composable
fun MainScreen(
    dao: ExerciseDao,
    loadInterstitial: () -> Unit
) {
    val currentScreen = remember { mutableStateOf(Screens.Exercises) }

    /** Navigate to screen and load interstitial ad */
    val navTo = { s: Screens ->
        currentScreen.value = s
        loadInterstitial()
    }
    when (currentScreen.value) {
        Screens.Exercises -> Exercises(dao, navTo)
        Screens.Edit -> EditExercise(
            exercise = ArgsUtils.args["exercise"] as Exercise,
            dao = dao,
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
            override fun getExercise(id: Long) = Exercise("Hello", "World")
            override fun delete(exercise: Exercise) {}
        }, {})
    }
}