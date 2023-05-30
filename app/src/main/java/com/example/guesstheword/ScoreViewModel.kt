package com.example.guesstheword

import android.util.Log
import androidx.lifecycle.ViewModel

class ScoreViewModel(finalScore: Int): ViewModel() {
    //the final score
    var score = finalScore
    init {
        Log.i("ScoreViewModel", "Final score is $finalScore")
    }
}