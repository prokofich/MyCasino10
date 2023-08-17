package com.example.mycasino10.view.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.lifecycle.ViewModelProvider
import com.example.mycasino10.R
import com.example.mycasino10.constant.GAME
import com.example.mycasino10.constant.GAME_QUESTION
import com.example.mycasino10.constant.GAME_SEARCH
import com.example.mycasino10.constant.MAIN
import com.example.mycasino10.viewmodel.RulesViewModel
import kotlinx.android.synthetic.main.fragment_rules.*


class RulesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_rules, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //привязка вьюмодели к фрагменту
        val rulesViewModel = ViewModelProvider(this)[RulesViewModel::class.java]

        //показ полученного от сервера ответа
        rulesViewModel.Text.observe(viewLifecycleOwner){ TEXT ->
            id_rules_tv_description.text = TEXT.body()!!.text
        }

        //выход в меню
        id_rules_button_back.setOnClickListener {
            MAIN.navController.navigate(R.id.action_rulesFragment_to_menuFragment)
        }

        //обработка нажатия на кнопку НАЗАД(выход в меню)
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner){
            MAIN.navController.navigate(R.id.action_rulesFragment_to_menuFragment)
        }

        //отпрака запроса на сервер
        when(requireArguments().getString(GAME)){
            GAME_SEARCH -> {
                id_rules_tv_title_game.text = "find the card"
                rulesViewModel.getTextRulesSearch()

            }
            GAME_QUESTION -> {
                id_rules_tv_title_game.text = "answer correctly"
                rulesViewModel.getTextRulesQuestion()
            }
        }

    }
}