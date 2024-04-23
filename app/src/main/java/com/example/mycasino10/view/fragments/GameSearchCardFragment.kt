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
import com.example.mycasino10.model.constant.GAME_SEARCH
import com.example.mycasino10.model.constant.MAIN
import com.example.mycasino10.model.constant.NAME_MY_SERVER
import com.example.mycasino10.model.constant.TIME_LONG
import com.example.mycasino10.model.constant.TIME_SHORT
import com.example.mycasino10.model.constant.attempt_search_easy
import com.example.mycasino10.model.constant.attempt_search_hard
import com.example.mycasino10.model.constant.attempt_search_middle
import com.example.mycasino10.model.constant.listAllCard
import com.example.mycasino10.model.constant.url_image_background
import com.example.mycasino10.model.constant.url_obratnaya_storona
import kotlinx.android.synthetic.main.fragment_game_search_card.*
import kotlinx.coroutines.*

class GameSearchCardFragment : Fragment() {

    private var list9Cards = emptyList<String>()
    private var hiddenCard = ""

    private var currentLevel = 1

    private var countAttempts = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_game_search_card, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ////////////////////////////////////////////////////////////////////////////////////////////
        //обработка нажатия на кнопку НАЗАД(пауза)
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner){
            id_game_search_cs_game.isVisible = false
            id_game_search_tv_time.isVisible = false
            id_game_search_iv_timer.isVisible = false
            id_game_search_cs_pause.isVisible = true
        }

        //поставить на паузу
        id_game_search_button_pause.setOnClickListener {
            id_game_search_cs_game.isVisible = false
            id_game_search_tv_time.isVisible = false
            id_game_search_iv_timer.isVisible = false
            id_game_search_cs_pause.isVisible = true
        }

        //завершение+выход в меню
        id_game_search_button_finish_menu.setOnClickListener {
            MAIN.navController?.navigate(R.id.action_gameSearchCardFragment_to_menuFragment)
        }

        //выход из паузы(продолжение игры)
        id_game_search_button_continue.setOnClickListener {
            id_game_search_cs_game.isVisible = true
            id_game_search_tv_time.isVisible = true
            id_game_search_iv_timer.isVisible = true
            id_game_search_cs_pause.isVisible = false
        }
        ////////////////////////////////////////////////////////////////////////////////////////////

        //загрузка количества попыток
        loadCountAttempts()

        id_game_search_tv_time.text = "$countAttempts attempts"

        //загрузка фоновой картинки
        loadImage(url_image_background,id_game_search_img)

        //первое получение карт
        getNewCards()

        //установка загаданной карты
        setHiddenCard()

        //загрузка обратных сторон карт
        loadCardsInImageView()

        //показ загаданной карты
        loadImage(NAME_MY_SERVER +hiddenCard,id_game_search_iv_card10)

        //проверка карты
        id_game_search_iv_card1.setOnClickListener {
          checkCard(0,id_game_search_iv_card1)
        }

        //проверка карты
        id_game_search_iv_card2.setOnClickListener {
            checkCard(1,id_game_search_iv_card2)
        }

        //проверка карты
        id_game_search_iv_card3.setOnClickListener {
            checkCard(2, id_game_search_iv_card3)
        }

        //проверка карты
        id_game_search_iv_card4.setOnClickListener {
            checkCard(3,id_game_search_iv_card4)
        }

        //проверка карты
        id_game_search_iv_card5.setOnClickListener {
            checkCard(4,id_game_search_iv_card5)
        }

        //проверка карты
        id_game_search_iv_card6.setOnClickListener {
            checkCard(5,id_game_search_iv_card6)
        }

        //проверка карты
        id_game_search_iv_card7.setOnClickListener {
            checkCard(6,id_game_search_iv_card7)
        }

        //проверка карты
        id_game_search_iv_card8.setOnClickListener {
            checkCard(7,id_game_search_iv_card8)
        }

        //проверка карты
        id_game_search_iv_card9.setOnClickListener {
            checkCard(8,id_game_search_iv_card9)
        }

    }

    //функция загрузки изображения
    private fun loadImage(url:String,id:ImageView){
        Glide.with(requireContext())
            .load(url)
            .into(id)
    }

    //функция показа всплывающего сообщения
    private fun showToast(message:String,time:String){
        when(time){
            TIME_SHORT -> { Toast.makeText(requireContext(),message,Toast.LENGTH_SHORT).show() }
            TIME_LONG -> { Toast.makeText(requireContext(),message,Toast.LENGTH_LONG).show() }
        }
    }

    //функция получения новых карт
    private fun getNewCards(){
        list9Cards = listAllCard.shuffled().slice(0..8)
    }

    //функция выбора загаданной карты из представленных
    private fun setHiddenCard(){
        hiddenCard = list9Cards.shuffled()[0]
    }

    //функция загрузки обратных сторон всех карт
    private fun loadCardsInImageView(){
        loadImage(url_obratnaya_storona,id_game_search_iv_card1)
        loadImage(url_obratnaya_storona,id_game_search_iv_card2)
        loadImage(url_obratnaya_storona,id_game_search_iv_card3)
        loadImage(url_obratnaya_storona,id_game_search_iv_card4)
        loadImage(url_obratnaya_storona,id_game_search_iv_card5)
        loadImage(url_obratnaya_storona,id_game_search_iv_card6)
        loadImage(url_obratnaya_storona,id_game_search_iv_card7)
        loadImage(url_obratnaya_storona,id_game_search_iv_card8)
        loadImage(url_obratnaya_storona,id_game_search_iv_card9)
    }

    //функция загрузки количества попыток
    private fun loadCountAttempts(){
        when(requireArguments().getString(COMPLEXITY)){
            COMPLEXITY_EASY   -> { countAttempts = attempt_search_easy   }
            COMPLEXITY_MIDDLE -> { countAttempts = attempt_search_middle }
            COMPLEXITY_HARD   -> { countAttempts = attempt_search_hard   }
        }
    }

    //установка блока или отмена блока для всех карт
    private fun enabledImageView(flag:Boolean){
        id_game_search_iv_card1.isEnabled = flag
        id_game_search_iv_card2.isEnabled = flag
        id_game_search_iv_card3.isEnabled = flag
        id_game_search_iv_card4.isEnabled = flag
        id_game_search_iv_card5.isEnabled = flag
        id_game_search_iv_card6.isEnabled = flag
        id_game_search_iv_card7.isEnabled = flag
        id_game_search_iv_card8.isEnabled = flag
        id_game_search_iv_card9.isEnabled = flag
    }

    @SuppressLint("SetTextI18n")
    private fun checkCard(index:Int, id:ImageView){
        CoroutineScope(Dispatchers.Main).launch {
            //обновление попыток
            countAttempts-=1
            id_game_search_tv_time.text = "$countAttempts attempts"

            loadImage(NAME_MY_SERVER +list9Cards[index],id)

            if(list9Cards[index]==hiddenCard){
                //угадал
                currentLevel+=1
                enabledImageView(false)
                showToast("you guessed it!", TIME_SHORT)
                id_game_search_tv_level.text="level $currentLevel"
                delay(3000)
                loadCountAttempts()
                id_game_search_tv_time.text = "$countAttempts attempts"
                loadCardsInImageView()
                getNewCards()
                setHiddenCard()
                loadImage(NAME_MY_SERVER + hiddenCard,id_game_search_iv_card10)
                enabledImageView(true)
            }else{
                if(countAttempts==0){
                    //попытки кончились
                    enabledImageView(false)
                    val bundle = Bundle()
                    bundle.putString(GAME, GAME_SEARCH)
                    bundle.putString(COMPLEXITY,requireArguments().getString(COMPLEXITY))
                    bundle.putInt(CURRENT_RESULT,currentLevel)
                    when(requireArguments().getString(COMPLEXITY)){
                        COMPLEXITY_EASY -> {
                            if(MAIN.getRecordSearchEasy() < currentLevel){
                                bundle.putBoolean(CHECK_RECORD,true)
                                MAIN.setRecordSearchEasy(currentLevel)
                            }else{
                                bundle.putBoolean(CHECK_RECORD,false)
                            }
                        }
                        COMPLEXITY_MIDDLE -> {
                            if(MAIN.getRecordSearchMiddle() < currentLevel){
                                bundle.putBoolean(CHECK_RECORD,true)
                                MAIN.setRecordSearchMiddle(currentLevel)
                            }else{
                                bundle.putBoolean(CHECK_RECORD,false)
                            }
                        }
                        COMPLEXITY_HARD -> {
                            if(MAIN.getRecordSearchHard() < currentLevel){
                                bundle.putBoolean(CHECK_RECORD,true)
                                MAIN.setRecordSearchHard(currentLevel)
                            }else{
                                bundle.putBoolean(CHECK_RECORD,false)
                            }
                        }
                    }
                    delay(3000)
                    MAIN.navController?.navigate(R.id.action_gameSearchCardFragment_to_gameOverFragment,bundle)
                }
            }
        }
    }

}