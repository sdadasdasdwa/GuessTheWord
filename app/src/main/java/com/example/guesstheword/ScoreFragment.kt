package com.example.guesstheword

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.guesstheword.databinding.ScoreFragmentBinding

class ScoreFragment : Fragment() {

    private lateinit var viewModel: ScoreViewModel
    private lateinit var viewModelFactory: ScoreViewModelFactory
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding:ScoreFragmentBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.score_fragment,
            container,
            false
        )

        val score = ScoreFragmentArgs.fromBundle(requireArguments()).score
        viewModelFactory = ScoreViewModelFactory(score)

        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(ScoreViewModel::class.java)

        binding.scoreViewModel = viewModel


//        viewModel.score.observe(viewLifecycleOwner, Observer { newScore ->
//            binding.scoreText.text = newScore.toString()
//        })

//        binding.playAgainButton.setOnClickListener { viewModel.onPlayAgain() }

        viewModel.eventPlayAgain.observe(viewLifecycleOwner, Observer { playAgain ->
            if(playAgain){
                findNavController().navigate(ScoreFragmentDirections.actionRestart())
                viewModel.onPlayAgainComplete()
            }
        })

        binding.lifecycleOwner=viewLifecycleOwner

        return binding.root
    }
}