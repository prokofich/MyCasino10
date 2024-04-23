package com.example.mycasino10.view.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import com.example.mycasino10.R
import com.example.mycasino10.model.constant.CHECK_RECORD
import com.example.mycasino10.model.constant.COMPLEXITY
import com.example.mycasino10.model.constant.COMPLEXITY_EASY
import com.example.mycasino10.model.constant.COMPLEXITY_HARD
import com.example.mycasino10.model.constant.COMPLEXITY_MIDDLE
import com.example.mycasino10.model.constant.CURRENT_RESULT
import com.example.mycasino10.model.constant.GAME
import com.example.mycasino10.model.constant.GAME_QUESTION
import com.example.mycasino10.model.constant.GAME_SEARCH
import com.example.mycasino10.model.constant.MAIN
import kotlinx.android.synthetic.main.fragment_game_over.*

class GameOverFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_game_over, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //показ текущего результата
        id_gameover_tv_current_result.text = "current result: "+requireArguments().getInt(
            CURRENT_RESULT
        ).toString()+" lvl"

        //показ рекорда
        showRecord()

        //показ побит рекорд или нет
        when(requireArguments().getBoolean(CHECK_RECORD)){
            true -> { id_gameover_tv_result_desc.text = "you have broken the record!" }
            false -> { id_gameover_tv_result_desc.text = "you haven't broken your record..." }
        }

        //выход в меню
        id_gameover_button_menu.setOnClickListener {
            MAIN.navController?.navigate(R.id.action_gameOverFragment_to_menuFragment)
        }

        //рестарт
        id_gameover_button_restart.setOnClickListener {
            when(requireArguments().getString(GAME)){
                GAME_SEARCH -> {
                    val bundle = Bundle()
                    bundle.putString(GAME, GAME_SEARCH)
                    MAIN.navController?.navigate(R.id.action_gameOverFragment_to_complexityGameFragment,bundle)
                }
                GAME_QUESTION -> {
                    val bundle = Bundle()
                    bundle.putString(GAME, GAME_QUESTION)
                    MAIN.navController?.navigate(R.id.action_gameOverFragment_to_complexityGameFragment,bundle)
                }
            }
        }

        //обработка нажатия на кнопку НАЗАД(выход в меню)
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner){
            MAIN.navController?.navigate(R.id.action_gameOverFragment_to_menuFragment)
        }

    }

    //функция показа рекорда
    @SuppressLint("SetTextI18n")
    private fun showRecord(){
        when(requireArguments().getString(GAME)){
            GAME_SEARCH -> {
                when(requireArguments().getString(COMPLEXITY)){
                    COMPLEXITY_EASY -> {id_gameover_tv_record.text   = "record:${MAIN.getRecordSearchEasy()}"}
                    COMPLEXITY_MIDDLE -> {id_gameover_tv_record.text = "record:${MAIN.getRecordSearchMiddle()}"}
                    COMPLEXITY_HARD -> {id_gameover_tv_record.text   = "record:${MAIN.getRecordSearchHard()}"}
                }
            }
            GAME_QUESTION -> {
                when(requireArguments().getString(COMPLEXITY)){
                    COMPLEXITY_EASY -> {id_gameover_tv_record.text   = "record:${MAIN.getRecordQuestionEasy()}"}
                    COMPLEXITY_MIDDLE -> {id_gameover_tv_record.text = "record:${MAIN.getRecordQuestionMiddle()}"}
                    COMPLEXITY_HARD -> {id_gameover_tv_record.text   = "record:${MAIN.getRecordQuestionHard()}"}
                }
            }
        }
    }


}