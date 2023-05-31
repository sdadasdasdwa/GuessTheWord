package com.example.guesstheword

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.example.guesstheword.databinding.GameFragmentBinding

class GameFragment : Fragment() {

    private lateinit var viewModel: GameViewModel

    private lateinit var binding: GameFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate view and obtain an instance of the binding class
        binding = DataBindingUtil.inflate(inflater, R.layout.game_fragment, container, false)

        Log.i("GameFragment", "Called ViewModelProvider.get")
        viewModel = ViewModelProvider(this).get(GameViewModel::class.java)


        binding.correctButton.setOnClickListener { onCorrect() }
        binding.skipButton.setOnClickListener { onSkip() }
        binding.endGameButton.setOnClickListener { onEndGame() }

//        updateScoreText()
//        updateWordText()

        viewModel.score.observe(viewLifecycleOwner, Observer { newScore ->
            binding.scoreText.text = newScore.toString()
        })

        viewModel.word.observe(viewLifecycleOwner, Observer{ newWord ->
            binding.wordText.text = newWord
        })

        viewModel.eventGameFinish.observe(viewLifecycleOwner, Observer { hasFinished ->
            if(hasFinished){
                gameFinished()
            }
        })

        return binding.root
    }


    private fun onSkip() {
        viewModel.onSkip()
//        updateWordText()
//        updateScoreText()
    }

    private fun onCorrect() {
        viewModel.onCorrect()
//        updateScoreText()
//        updateWordText()
    }

    private fun onEndGame() {
        gameFinished()
    }

    private fun gameFinished() {
        Toast.makeText(activity, "Game has just finished", Toast.LENGTH_SHORT).show()
        val action = GameFragmentDirections.actionGameToScore()
        action.score = viewModel.score.value ?: 0
        NavHostFragment.findNavController(this).navigate(action)
        viewModel.onGameFinishComplete()
    }


//    private fun updateWordText() {
//        binding.wordText.text = viewModel.word.value
//    }

//    private fun updateScoreText() {
//        binding.scoreText.text = viewModel.score.value.toString()
//    }


}