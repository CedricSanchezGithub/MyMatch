package com.example.mymatch.beans

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


data class MatchBean(
    var id:Long? = null,
    var date: Long? = null,
    var equipe1: String = "Equipe 1",
    var equipe2: String = "Equipe 2",
    var score_equipe: Int? = 0,
    var score_equipe2: Int? = 0,
    var status: Boolean = false)


const val Score_Equipe1 = ""
const val Score_Equipe2 = ""
val matchList = arrayListOf(
    MatchBean(null, 541544848, "Nantes", "Avignon", 12, 35),
    MatchBean( null,541544848,"PSG","HBLM", 15, 22)
)

