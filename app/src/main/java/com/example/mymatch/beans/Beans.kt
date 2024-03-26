package com.example.mymatch.beans

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


data class MatchBean(
    val id : Int,
    val title_A : String,
    val win : String,
    val Score_Equipe1: String,
    val Score_Equipe2: String,
    val logo : String,

)

const val Score_Equipe1 = ""
const val Score_Equipe2 = ""
val matchList = arrayListOf(
    MatchBean(1, " PSG ", " V D V D V ", Score_Equipe1, Score_Equipe2, "Logo"),
    MatchBean(2, " HBLM "," D D D D D ", Score_Equipe1, Score_Equipe2, "Logo2")
)

