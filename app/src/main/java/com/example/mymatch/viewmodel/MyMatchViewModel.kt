package com.example.mymatch.viewmodel


import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mymatch.beans.MatchBean
import com.example.mymatch.beans.matchList
import com.example.mymatch.model.MatchAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.IOException


class MyMatchViewModel : ViewModel() {
    var match2 = mutableStateOf(MatchBean(equipe1 = "equipe1", equipe2 = "equipe2"))
    val myList2 = mutableStateListOf(
        MatchBean(
            id = 1,
            date = System.currentTimeMillis(), // Utilisation de l'heure actuelle comme date
            equipe1 = "Real Madrid",
            equipe2 = "Barcelona",
            score_equipe = 2,
            score_equipe2 = 1,
            status = true
        ),
        MatchBean(
            id = 2,
            date = System.currentTimeMillis(), // Utilisation de l'heure actuelle comme date
            equipe1 = "Manchester United",
            equipe2 = "Liverpool",
            score_equipe = 0,
            score_equipe2 = 0,
            status = false
        ),
    )
    val buttonValue = mutableIntStateOf(0)
    fun loadList() {
        myList2.clear()
        viewModelScope.launch(Dispatchers.Default) {
            try {
                val newData = MatchAPI.load7DayzMatch()
                // myList2.addAll(newData)
                launch(Dispatchers.Main) {
                    myList2.addAll(newData)
                    println("Load list")
                }
            } catch (e: IOException) {
                println("catch")
                e.printStackTrace()
            }
        }
    }

    fun createMatch(team1: String, team2: String): MutableState<MatchBean> {
        val date = System.currentTimeMillis()
        viewModelScope.launch(Dispatchers.Default) {
            try {
                MatchAPI.createMatch(equipe1 = team1, equipe2 = team2, date = date)
                match2 = mutableStateOf(MatchBean(equipe1 = team1, equipe2 = team2))
                launch(Dispatchers.Main) {
                }
            } catch (e: IOException) {
                println("catch")
                e.printStackTrace()
            }
        }
        loadList()
        return match2

    }

    fun addScore(id: Long, equipe: Int): MutableState<MatchBean> {
        viewModelScope.launch(Dispatchers.Default) {
            try {
                // match2 = MatchAPI.add1Point(id, equipe)
                launch(Dispatchers.Main)
                {

                }
            } catch (e: IOException) {
                println("catch")
                e.printStackTrace()
            }
        }
        return match2
    }


}