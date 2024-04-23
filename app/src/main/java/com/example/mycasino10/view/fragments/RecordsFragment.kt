package com.example.mycasino10.view.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import com.example.mycasino10.R
import com.example.mycasino10.model.constant.MAIN
import kotlinx.android.synthetic.main.fragment_records.*

class RecordsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_records, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //выход в меню
        id_records_button_back.setOnClickListener {
            MAIN.navController?.navigate(R.id.action_recordsFragment_to_menuFragment)
        }

        //обработка нажатия на кнопку НАЗАД(выход в меню)
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner){
            MAIN.navController?.navigate(R.id.action_recordsFragment_to_menuFragment)
        }

        //показ всех рекордов
        id_records_tv_search_easy.text   = "find the card,easy:${MAIN.getRecordSearchEasy()}"
        id_records_tv_search_middle.text = "find the card,middle:${MAIN.getRecordSearchMiddle()}"
        id_records_tv_search_hard.text   = "find the card,hard:${MAIN.getRecordSearchHard()}"
        id_records_tv_time_easy.text     = "answer correctly,easy:${MAIN.getRecordQuestionEasy()}"
        id_records_tv_time_middle.text   = "answer correctly,middle:${MAIN.getRecordQuestionMiddle()}"
        id_records_tv_time_hard.text     = "answer correctly,hard:${MAIN.getRecordQuestionHard()}"

    }

}