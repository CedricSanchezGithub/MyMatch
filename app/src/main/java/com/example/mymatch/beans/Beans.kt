package com.example.mymatch.beans

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


data class AllMatchBean(
    val id: Int,
    val url: String,
    val title: String,
    val Equipe_A: String,
    val Equipe_B: String,
    val Score_EquipeA: String,
    val Score_EquipeB: String,
)


const val Equipe_A = " PSG "
const val Equipe_B = " HMLB "

const val Score_EquipeA = " 5 "
const val Score_EquipeB = " 6"


val allMatchList = arrayListOf(
    AllMatchBean(1, "https://picsum.photos/200", "Match 1", Equipe_A, Equipe_B, Score_EquipeA, Score_EquipeB),
    AllMatchBean(2, "https://picsum.photos/201", "Match 2", Equipe_A, Equipe_B, Score_EquipeA, Score_EquipeB),
    AllMatchBean(3, "https://picsum.photos/202", "Match 3", Equipe_A, Equipe_B, Score_EquipeA, Score_EquipeB),
    AllMatchBean(4, "https://picsum.photos/203", "Match 4", Equipe_A, Equipe_B, Score_EquipeA, Score_EquipeB)
)

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

