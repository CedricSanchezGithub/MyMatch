package com.example.mymatch.viewmodel


import android.icu.text.SimpleDateFormat
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mymatch.beans.MatchBean
import com.example.mymatch.beans.matchList
import com.example.mymatch.model.MatchAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.invoke
import kotlinx.coroutines.launch
import java.io.IOException
import java.sql.Date
import java.util.Locale


class MyMatchViewModel : ViewModel() {

    var equipe1 = mutableStateOf("")
    var equipe2 = mutableStateOf("")
    val dialogShown = mutableStateOf(false)

    fun formatDate(milliseconds: Long): String {
        val dateFormat = java.text.SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault())
        val date = (milliseconds)
        return dateFormat.format(date)
    }

    var match2 = mutableStateOf(MatchBean(equipe1 = "", equipe2 = ""))

    val myList2 = mutableStateListOf<MatchBean>()

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
                e.printStackTrace()
            }
        }
    }

    fun createMatch(team1: String, team2: String) {
        val date = System.currentTimeMillis()
        viewModelScope.launch(Dispatchers.Default) {
            try {
                println("viewmodel creer")
                MatchAPI.createMatch(equipe1 = team1, equipe2 = team2, date = date)
                val newMatch = MatchBean(equipe1 = team1, equipe2 = team2, date = date)
                launch(Dispatchers.Main) {
                    myList2.add(newMatch)
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    fun addScore(id: Long, equipe: Int) {
        viewModelScope.launch(Dispatchers.Default) {
            try {
                val newData = MatchAPI.add1Point(id, equipe)
                launch(Dispatchers.Main)
                {
                    if (newData != null) {
                        match2.value = newData
                    }
                }
            } catch (e: IOException) {
                println("catch")
                e.printStackTrace()
            }
        }
    }

    fun changeStatus(idMatch: Long) {

        viewModelScope.launch(Dispatchers.Default) {
            try {
                MatchAPI.finishMatch(idMatch)
                loadList()
            } catch (e: IOException) {
                println("catch")
                e.printStackTrace()
            }
        }
    }
}