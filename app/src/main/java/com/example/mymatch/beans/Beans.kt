package com.example.mymatch.beans


data class PictureBean(
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


val pictureList = arrayListOf(
    PictureBean(1, "https://picsum.photos/200", "Match 1", Equipe_A, Equipe_B, Score_EquipeA, Score_EquipeB),
    PictureBean(2, "https://picsum.photos/201", "Match 2", Equipe_A, Equipe_B, Score_EquipeA, Score_EquipeB),
    PictureBean(3, "https://picsum.photos/202", "Match 3", Equipe_A, Equipe_B, Score_EquipeA, Score_EquipeB),
    PictureBean(4, "https://picsum.photos/203", "Match 4", Equipe_A, Equipe_B, Score_EquipeA, Score_EquipeB)
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
    MatchBean(1, " Paris-Saint-Germain ", " V D V D V ", Score_Equipe1, Score_Equipe2, "Logo"),
    MatchBean(2, " HBLM "," D D D D D ", Score_Equipe1, Score_Equipe2, "Logo2")
)