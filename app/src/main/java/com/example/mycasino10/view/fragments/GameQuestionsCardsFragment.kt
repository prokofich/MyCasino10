package com.example.mycasino10.view.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.addCallback
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.example.mycasino10.R
import com.example.mycasino10.model.constant.CHECK_RECORD
import com.example.mycasino10.model.constant.COMPLEXITY
import com.example.mycasino10.model.constant.COMPLEXITY_EASY
import com.example.mycasino10.model.constant.COMPLEXITY_HARD
import com.example.mycasino10.model.constant.COMPLEXITY_MIDDLE
import com.example.mycasino10.model.constant.CURRENT_RESULT
import com.example.mycasino10.model.constant.GAME
import com.example.mycasino10.model.constant.GAME_QUESTION
import com.example.mycasino10.model.constant.MAIN
import com.example.mycasino10.model.constant.NAME_MY_SERVER
import com.example.mycasino10.model.constant.listAllCard
import com.example.mycasino10.model.constant.mapAllCard
import com.example.mycasino10.model.constant.time_question_easy
import com.example.mycasino10.model.constant.time_question_hard
import com.example.mycasino10.model.constant.time_question_middle
import com.example.mycasino10.model.constant.url_image_background
import kotlinx.android.synthetic.main.fragment_game_questions_cards.*
import kotlinx.coroutines.*

class GameQuestionsCardsFragment : Fragment() {

    private var list4Cards = emptyList<String>()
    private var timeJob:Job = Job()
    private var timeSeconds = 0
    private var currentLevel = 1
    private var flagJob = false
    private var timeJobOnPause = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_game_questions_cards, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ////////////////////////////////////////////////////////////////////////////////////////////
        //загрузка фоновой картинки
        loadImage(url_image_background,id_game_questions_img)

        //загрузка времени раунда
        loadTimeSeconds()

        //поставить на паузу
        id_game_questions_button_pause.setOnClickListener {
            if(timeJob.isActive){
                timeJob.cancel()
                flagJob = true
            }
            id_game_questions_cs_game.isVisible = false
            id_game_questions_iv_timer.isVisible = false
            id_game_questions_tv_time.isVisible = false
            id_game_questions_cs_pause.isVisible = true
            //отмена корутин
        }

        //обработка нажатия на кнопку НАЗАД(пауза)
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner){
            if(timeJob.isActive){
                timeJob.cancel()
                flagJob = true
            }
            id_game_questions_cs_game.isVisible = false
            id_game_questions_tv_time.isVisible = false
            id_game_questions_iv_timer.isVisible = false
            id_game_questions_cs_pause.isVisible = true
            //отмена корутин
        }

        //продолжить после паузы
        id_game_question_button_continue.setOnClickListener {
            if(flagJob){
                timerOn(timeJobOnPause)
            }
            id_game_questions_cs_game.isVisible = true
            id_game_questions_tv_time.isVisible = true
            id_game_questions_iv_timer.isVisible = true
            id_game_questions_cs_pause.isVisible = false
            //отмена корутин
        }

        id_game_question_button_finish_menu.setOnClickListener {
            //отмена корутин
            MAIN.navController?.navigate(R.id.action_gameQuestionsCardsFragment_to_menuFragment)
        }
        ////////////////////////////////////////////////////////////////////////////////////////////

        //получение карт
        getNewCards()

        //показ карт
        loadAllCardsInImageView()

        //таймер для каждого уровня
        timerOn(timeSeconds)

        //выбор верхних карт
        id_game_question_button_up_win.setOnClickListener {
            checkCards(mapAllCard[list4Cards[0]]!! + mapAllCard[list4Cards[1]]!! > mapAllCard[list4Cards[2]]!! + mapAllCard[list4Cards[3]]!!)
        }

        //выбор нижних карт
        id_game_question_button_down_win.setOnClickListener {
            checkCards(mapAllCard[list4Cards[0]]!! + mapAllCard[list4Cards[1]]!! < mapAllCard[list4Cards[2]]!! + mapAllCard[list4Cards[3]]!!)
        }

        //выбор ничьи
        id_game_question_button_nichya_win.setOnClickListener {
            checkCards(mapAllCard[list4Cards[0]]!! + mapAllCard[list4Cards[1]]!! == mapAllCard[list4Cards[2]]!! + mapAllCard[list4Cards[3]]!!)
        }


    }

    //функция загрузки изображения
    private fun loadImage(url:String,id:ImageView){
        Glide.with(requireContext())
            .load(url)
            .into(id)
    }

    //функция показа всплывающего сообщения
    private fun showToast(message:String) = Toast.makeText(requireContext(),message, Toast.LENGTH_LONG).show()


    //функция получения новых карт
    private fun getNewCards(){
        list4Cards = listAllCard.shuffled().slice(0..3)
    }

    //показ всех загруженных карт
    private fun loadAllCardsInImageView(){
        loadImage(NAME_MY_SERVER +list4Cards[0],id_game_question_iv_card1)
        loadImage(NAME_MY_SERVER +list4Cards[1],id_game_question_iv_card2)
        loadImage(NAME_MY_SERVER +list4Cards[2],id_game_question_iv_card3)
        loadImage(NAME_MY_SERVER +list4Cards[3],id_game_question_iv_card4)
    }

    //функция загрузки количества попыток
    private fun loadTimeSeconds(){
        when(requireArguments().getString(COMPLEXITY)){
            COMPLEXITY_EASY   -> { timeSeconds = time_question_easy   }
            COMPLEXITY_MIDDLE -> { timeSeconds = time_question_middle }
            COMPLEXITY_HARD   -> { timeSeconds = time_question_hard   }
        }
    }

    //установка блока или отмена блока для всех кнопок
    private fun enabledButton(flag:Boolean){
        id_game_question_button_up_win.isEnabled = flag
        id_game_question_button_down_win.isEnabled = flag
        id_game_question_button_nichya_win.isEnabled = flag
    }

    //функция включения таймера
    @SuppressLint("SetTextI18n")
    private fun timerOn(time:Int){
        timeJob = CoroutineScope(Dispatchers.Main).launch {
            for(i in 1..time){
                delay(1000)
                id_game_questions_tv_time.text = "${time-i} sec"
                timeJobOnPause = time-i
            }
            enabledButton(false)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun checkCards(flag:Boolean){
        timeJob.cancel()
        enabledButton(false)
        CoroutineScope(Dispatchers.Main).launch {
            id_game_questions_tv_time.text = ""
            id_game_question_tv_card1.isVisible = true
            id_game_question_tv_card4.isVisible = true
            id_game_question_tv_card1.text = (mapAllCard[list4Cards[0]]!! + mapAllCard[list4Cards[1]]!!).toString()
            id_game_question_tv_card4.text = (mapAllCard[list4Cards[2]]!! + mapAllCard[list4Cards[3]]!!).toString()
            if(flag){
                //победа
                showToast("RIGHT!")
                delay(3000)
                id_game_question_tv_card1.isVisible = false
                id_game_question_tv_card4.isVisible = false
                currentLevel += 1
                id_game_question_tv_level.text = "$currentLevel lvl"
                getNewCards()
                loadAllCardsInImageView()
                timerOn(timeSeconds)
                enabledButton(true)
            }else{
                //поражение
                showToast("WRONG...")
                val bundle = Bundle()
                bundle.putString(GAME, GAME_QUESTION)
                bundle.putString(COMPLEXITY,requireArguments().getString(COMPLEXITY))
                bundle.putInt(CURRENT_RESULT,currentLevel)
                when(requireArguments().getString(COMPLEXITY)){
                    COMPLEXITY_EASY -> {
                        if(MAIN.getRecordQuestionEasy()<currentLevel){
                            bundle.putBoolean(CHECK_RECORD,true)
                            MAIN.setRecordQuestionEasy(currentLevel)
                        }else{
                            bundle.putBoolean(CHECK_RECORD,false)
                        }
                    }
                    COMPLEXITY_MIDDLE -> {
                        if(MAIN.getRecordQuestionMiddle()<currentLevel){
                            bundle.putBoolean(CHECK_RECORD,true)
                            MAIN.setRecordQuestionMiddle(currentLevel)
                        }else{
                            bundle.putBoolean(CHECK_RECORD,false)
                        }
                    }
                    COMPLEXITY_HARD -> {
                        if(MAIN.getRecordQuestionHard()<currentLevel){
                            bundle.putBoolean(CHECK_RECORD,true)
                            MAIN.setRecordQuestionHard(currentLevel)
                        }else{
                            bundle.putBoolean(CHECK_RECORD,false)
                        }
                    }
                }
                delay(3000)
                MAIN.navController?.navigate(R.id.action_gameQuestionsCardsFragment_to_gameOverFragment,bundle)
            }
        }
    }

}