package com.example.mycasino10.view.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import com.example.mycasino10.R
import com.example.mycasino10.constant.*
import kotlinx.android.synthetic.main.fragment_complexity_game.*

class ComplexityGameFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_complexity_game, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //показ описания уровней
        when(requireArguments().getString(GAME)){
            GAME_SEARCH -> {
                id_complexity_tv_desc_easy.text = "you will have $attempt_search_easy attempts"
                id_complexity_tv_desc_middle.text = "you will have $attempt_search_middle attempts"
                id_complexity_tv_desc_hard.text = "you will have $attempt_search_hard attempts"
            }
            GAME_QUESTION -> {
                id_complexity_tv_desc_easy.text = "you are given $time_question_easy sec"
                id_complexity_tv_desc_middle.text = "you are given $time_question_middle sec"
                id_complexity_tv_desc_hard.text = "you are given $time_question_hard sec"
            }
        }

        //выход в меню
        id_complexity_button_back.setOnClickListener {
            MAIN.navController.navigate(R.id.action_complexityGameFragment_to_menuFragment)
        }

        //обработка нажатия на кнопку НАЗАД(выход из игры)
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner){
            MAIN.navController.navigate(R.id.action_complexityGameFragment_to_menuFragment)
        }


        //выбор легкого уровня
        id_complexity_button_easy.setOnClickListener {
            val bundle = Bundle()
            bundle.putString(COMPLEXITY, COMPLEXITY_EASY)
            goToGame(bundle)
        }

        //выбор среднего уровня
        id_complexity_button_middle.setOnClickListener {
            val bundle = Bundle()
            bundle.putString(COMPLEXITY, COMPLEXITY_MIDDLE)
            goToGame(bundle)
        }

        //выбор сложного уровня
        id_complexity_button_hard.setOnClickListener {
            val bundle = Bundle()
            bundle.putString(COMPLEXITY, COMPLEXITY_HARD)
            goToGame(bundle)
        }

    }

    private fun goToGame(bundle: Bundle){
        when(requireArguments().getString(GAME)){
            GAME_SEARCH -> { MAIN.navController.navigate(R.id.action_complexityGameFragment_to_gameSearchCardFragment,bundle) }
            GAME_QUESTION -> { MAIN.navController.navigate(R.id.action_complexityGameFragment_to_gameQuestionsCardsFragment,bundle) }
        }
    }



}