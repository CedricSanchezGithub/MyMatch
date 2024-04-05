package com.example.mymatch.viewmodel


import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mymatch.beans.MatchBean
import com.example.mymatch.model.MatchAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.IOException
import java.util.Locale


class MyMatchViewModel : ViewModel() {

    var equipe1 = mutableStateOf("")
    var equipe2 = mutableStateOf("")
    val dialogShown = mutableStateOf(false)

    private val _errorText = mutableStateOf("")
    val errorText: State<String> = _errorText

    private val _errorTextColor = mutableStateOf(Color.Black) // Par défaut, la couleur du texte est noire
    val errorTextColor: State<Color> = _errorTextColor

    fun setErrorText(text: String) {
        _errorText.value = text
        _errorTextColor.value = Color.Black
    }

    fun formatDate(milliseconds: Long): String {
        val dateFormat = java.text.SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault())
        val date = (milliseconds)
        return dateFormat.format(date)
    }

    var match2 = mutableStateOf(MatchBean(equipe1 = "", equipe2 = ""))

    val myList2 = mutableStateListOf<MatchBean>()

    fun deleteMatchVM(id : Long){
        viewModelScope.launch(Dispatchers.Default) {
            try {
                MatchAPI.deleteMatch(id)
            } catch (e: IOException) {
                e.printStackTrace()
            }
            loadList()
        }
    }


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