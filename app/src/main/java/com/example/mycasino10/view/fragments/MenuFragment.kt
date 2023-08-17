package com.example.mycasino10.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import com.bumptech.glide.Glide
import com.example.mycasino10.R
import com.example.mycasino10.constant.*
import kotlinx.android.synthetic.main.fragment_menu.*

class MenuFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_menu, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //загрузка эмблемы игры
        Glide.with(requireContext())
            .load(url_image_emblema)
            .into(id_menu_iv_emblema)

        //переход к рекордам
        id_menu_tv_records.setOnClickListener {
            MAIN.navController.navigate(R.id.action_menuFragment_to_recordsFragment)
        }

        //выход из игры
        id_menu_button_exit.setOnClickListener {
            MAIN.finishAffinity()
        }

        //перейти к выбору уровня игры
        id_menu_button_game_search.setOnClickListener {
            val bundle = Bundle()
            bundle.putString(GAME, GAME_SEARCH)
            MAIN.navController.navigate(R.id.action_menuFragment_to_complexityGameFragment,bundle)
        }

        //перейти к выбору уровня игры
        id_menu_button_game_time.setOnClickListener {
            val bundle = Bundle()
            bundle.putString(GAME, GAME_QUESTION)
            MAIN.navController.navigate(R.id.action_menuFragment_to_complexityGameFragment,bundle)
        }

        //обработка нажатия на кнопку НАЗАД(выход из игры)
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner){
            MAIN.finishAffinity()
        }

        //перейти на экран правил игры
        id_menu_tv_game_tutorial_search.setOnClickListener {
            val bundle = Bundle()
            bundle.putString(GAME, GAME_SEARCH)
            MAIN.navController.navigate(R.id.action_menuFragment_to_rulesFragment,bundle)
        }

        //перейти на экран правил игры
        id_menu_tv_game_tutorial_time.setOnClickListener {
            val bundle = Bundle()
            bundle.putString(GAME, GAME_QUESTION)
            MAIN.navController.navigate(R.id.action_menuFragment_to_rulesFragment,bundle)
        }

    }



}