package com.example.guesstheword

import android.os.CountDownTimer
import android.text.format.DateUtils
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {

    private val timer : CountDownTimer

    companion object{
        //Time when the game is over
        private const val DONE = 0L
        //Countdown time interval
        private const val ONE_SECOND = 1000L
        //Total time for the game
        private const val COUNTDOWN_TIME = 60000L

    }

    //The current word
    private val _word = MutableLiveData<String>()
    val word : LiveData<String>
        get() = _word

    //The current score
    private val _score = MutableLiveData<Int>()
    val score: LiveData<Int>
        get() = _score

    //Event which triggers the end of the game
    private val _evenGameFinish = MutableLiveData<Boolean>()
    val eventGameFinish : LiveData<Boolean>
        get() = _evenGameFinish

    //CountDown time
    private val _currentTime = MutableLiveData<Long>()
    val currentTime : LiveData<Long>
        get() = _currentTime

    //The string version of the current time
    val currentTimeString = Transformations.map(currentTime){ time ->
        DateUtils.formatElapsedTime(time)
    }

    //The list of words - the front of the list is the next word to guess
    private lateinit var wordList: MutableList<String>

    init {
        Log.i("GameViewModel", "GameViewModel created!")
        _word.value = ""
        _score.value = 0
        resetList()
        nextWord()

        //Creates a timer which triggers the end of the game when it finishes
        timer = object : CountDownTimer(COUNTDOWN_TIME, ONE_SECOND){
            override fun onTick(p0: Long) {
                _currentTime.value = p0/ ONE_SECOND
            }

            override fun onFinish() {
                onGameFinish()
            }

        }
        timer.start()
    }

    private fun resetList() {
        wordList = mutableListOf(
            "queen",
            "hospital",
            "basketball",
            "cat",
            "change",
            "snail",
            "soup",
            "calendar",
            "sad",
            "desk",
            "guitar",
            "home",
            "railway",
            "zebra",
            "jelly",
            "car",
            "crow",
            "trade",
            "bag",
            "roll",
            "bubble"
        )
        wordList.shuffle()
    }

    private fun nextWord() {
        if (!wordList.isEmpty()) {
            // select and remove a word and from the list
            _word.value = wordList.removeAt(0)
        }else{
//            onGameFinish()
            resetList()
        }
    }

    fun onSkip() {
        _score.value = (score.value)?.minus(1)
        nextWord()
    }

    fun onCorrect() {
        _score.value = (score.value)?.plus(1)
        nextWord()
    }

    fun onGameFinish(){
        _evenGameFinish.value = true
    }

    fun onGameFinishComplete(){
        _evenGameFinish.value = false
    }


    override fun onCleared() {
        super.onCleared()
        Log.i("GameViewModel", "GameViewModel destroyed!")
        timer.cancel()
    }
}