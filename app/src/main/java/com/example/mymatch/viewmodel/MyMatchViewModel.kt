package com.example.mymatch.viewmodel


import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.mymatch.beans.MatchBean
import com.example.mymatch.beans.matchList


class MyMatchViewModel : ViewModel() {

    val searchText = mutableStateOf("")
    val runInProgress = mutableStateOf(false)
    val errorMessage = mutableStateOf("")

    val myList2 = mutableStateListOf<MatchBean>()
    //fun filterList() = myList.filter { it.title.contains(searchText.value, true) }

    fun uploadSearchText(newText: String) {
        searchText.value = newText
    }

    fun loadData() {//Simulation de chargement de donnée
        myList2.clear()
        myList2.addAll(matchList)


        //Une tache en cours
        runInProgress.value = true
        errorMessage.value = ""


    }

}
