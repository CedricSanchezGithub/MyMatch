package com.example.mymatch.viewmodel


import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mymatch.beans.PictureBean
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MainViewModel : ViewModel() {

    val searchText = mutableStateOf("")
    val myList = mutableStateListOf<PictureBean>()
    val runInProgress = mutableStateOf(false)
    val errorMessage = mutableStateOf("")

    //fun filterList() = myList.filter { it.title.contains(searchText.value, true) }

    fun uploadSearchText(newText: String) {
        searchText.value = newText
    }

    fun loadData() {//Simulation de chargement de donnée
        myList.clear()

        //Une tache en cours
        runInProgress.value = true
        errorMessage.value = ""


    }

}
