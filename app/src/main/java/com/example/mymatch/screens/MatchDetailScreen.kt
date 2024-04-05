package com.example.mymatch.screens


import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

import com.example.mymatch.ui.theme.MyMatchTheme
import com.example.mymatch.viewmodel.MyMatchViewModel


//Code affiché dans la Preview, thème claire, thème sombre
@Preview(showBackground = true, showSystemUi = true)
@Preview(showBackground = true, showSystemUi = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun MatchDetailPreview() {
    MyMatchTheme {
        Surface {
            MatchDetailScreen(10, myMatchViewModel = MyMatchViewModel())
        }
    }
}


@Composable
fun MatchDetailScreen(
    id: Long?,
    navHostController: NavHostController? = null,
    myMatchViewModel: MyMatchViewModel
) {

    val match = myMatchViewModel.myList2.find { it.id == id }



    Column(
        modifier = Modifier
            // Couleur de fond
            .background(MaterialTheme.colorScheme.primary) // Utilisation d'une couleur de fond légèrement grisée
            // Taille complète
            .fillMaxSize()
    ) {


        Spacer(modifier = Modifier.height(16.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "My Match",
                fontSize = 30.sp,
                textAlign = TextAlign.Center,
                color = Color.Black,
                modifier = Modifier.fillMaxWidth()
            )



        }

        Spacer(modifier = Modifier.height(25.dp)) // Ajout d'un espace vertical entre les équipes A et B
        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,

        ) {

            AnimatedVisibility(visible = match?.status == true) {
                Text(
                    text = "Match en cours !",
                    textAlign = TextAlign.Center,
                    fontSize = 30.sp,
                    fontFamily = FontFamily.Monospace,
                    color = Color.Black
                )
            }

            AnimatedVisibility(visible = match?.status == false) {
                Text(
                    text = "Match terminé !",
                    textAlign = TextAlign.Center,
                    fontSize = 30.sp,
                    fontFamily = FontFamily.Monospace,
                    color = Color.Black
                )
            }
        }

        Spacer(modifier = Modifier.height(25.dp)) // Ajout d'un espace vertical entre les équipes A et B


        // Equipes
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.tertiaryContainer)
        ) {
            Spacer(modifier = Modifier.width(15.dp))
            if (match != null) {
                Text(
                    text = match.equipe1,
                    textAlign = TextAlign.Center,
                    fontSize = 27.sp,
                    color = Color.Black,
                    modifier = Modifier.weight(1f)
                )
                println("Test equipe 1 ")
            }

            if (match != null) {
                Text(
                    text = match.equipe2,
                    textAlign = TextAlign.Center,
                    fontSize = 27.sp,
                    color = Color.Black,
                    modifier = Modifier.weight(1f)
                )
                println("Test equipe 2 ")
            }
        }
        Spacer(modifier = Modifier.height(35.dp)) // Ajout d'un espace vertical entre les équipes A et B
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Case gauche
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .background(Color.White)
                    .border(width = 1.dp, color = Color.Black)

            ) {
                if (match != null) {
                    Text(
                        text = match.score_equipe1.toString(),
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
            // Texte "VS"
            Text(
                text = "VS",
                fontStyle = FontStyle.Italic,
                textAlign = TextAlign.Center,
                fontSize = 35.sp,
                color = Color.Black,
                modifier = Modifier.padding(horizontal = 50.dp) // Ajout de marges horizontales autour du texte
            )
            // Case droite
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .background(Color.White)
                    .border(width = 1.dp, color = Color.Black)

            ) {
                if (match != null) {
                    Text(
                        text = match.score_equipe2.toString(),
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(35.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Bouton +1 Equipe 1
            Button(
                onClick = {
                    if (match != null && match.status) {
                        match.id?.let { myMatchViewModel.addScore(it, 1) }
                        match.id?.let { myMatchViewModel.loadList() }
                    } else{
                        myMatchViewModel.setErrorText("Erreur ! Le match est terminé !")
                    }
                },
                contentPadding = ButtonDefaults.ButtonWithIconContentPadding,
                modifier = Modifier.padding(start = 69.dp),
                colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.secondary)
            ) {
                Icon(
                    Icons.Filled.Add,
                    contentDescription = "Localized description",
                    modifier = Modifier.size(ButtonDefaults.IconSize),
                    tint = Color.Black // Couleur de l'icône blanche
                )
                Text(
                    text = "1",
                    fontSize = 20.sp, // Augmentation de la taille du texte à 20sp
                    color = Color.Black // Couleur du texte noire
                )
            }
            Spacer(modifier = Modifier.width(80.dp))

            // Bouton +1 Equipe 2
            Button(
                onClick = {
                    if (match != null && match.status) {
                        match.id?.let { myMatchViewModel.addScore(it, 2) }
                        match.id?.let { myMatchViewModel.loadList() }
                    }
                    else{
                        myMatchViewModel.setErrorText("Erreur ! Le match est terminé !")
                    }
                },
                contentPadding = ButtonDefaults.ButtonWithIconContentPadding,
                modifier = Modifier.padding(start = 42.dp),
                colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.secondary)
            ) {
                Icon(
                    Icons.Filled.Add,
                    contentDescription = "Localized description",
                    modifier = Modifier.size(ButtonDefaults.IconSize),
                    tint = Color.Black // Couleur de l'icône noir
                )
                Text(
                    text = "1",
                    fontSize = 20.sp, // Augmentation de la taille du texte à 20sp
                    color = Color.Black // Couleur du texte noir
                )
            }
        }
        Spacer(modifier = Modifier.height(25.dp)) // Ajout d'un espace vertical entre les équipes A et B

        // Bouton match terminé
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center // Centrage horizontal du bouton
        ) {
            Button(

                onClick = {
                    if (match != null) {
                        println(match.status)
                        match.id?.let { myMatchViewModel.changeStatus(it) }
                        println(match.status)
                    }

                },
                contentPadding = ButtonDefaults.ButtonWithIconContentPadding,
                modifier = Modifier.padding(vertical = 16.dp),
                colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.secondary)
            ) {
                Icon(
                    Icons.Filled.Clear,
                    contentDescription = "Localized description",
                    modifier = Modifier.size(ButtonDefaults.IconSize),
                    tint = Color.Black // Couleur de l'icône blanche
                )
                Text(
                    text = "Match Terminé",
                    fontSize = 20.sp, // Augmentation de la taille du texte à 20sp
                    color = Color.Black // Couleur du texte blanche
                )
            }




        }

        Row(
            modifier = Modifier.fillMaxWidth(),
           horizontalArrangement = Arrangement.Center ) {
            if (match != null) {
                val scoreEquipe1 = match.score_equipe1 ?: 0 // Si score_equipe1 est null, utiliser 0
                val scoreEquipe2 = match.score_equipe2 ?: 0 // Si score_equipe2 est null, utiliser 0

                val difference = scoreEquipe1 - scoreEquipe2
                val seuilDomination = 5 // Choisir un seuil approprié pour la différence de score
                if (difference >= seuilDomination) {
                    Text(
                        text = "Domination de l'équipe 1", // Adapter le message en fonction de l'équipe qui domine
                        textAlign = TextAlign.Center,
                        color = Color.Black,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                } else if (difference <= -seuilDomination) {
                    Text(
                        text = "Domination de l'équipe 2", // Adapter le message en fonction de l'équipe qui domine
                        textAlign = TextAlign.Center,
                        color = Color.Black,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
            }
        }






    }
}


@Composable
fun errorTextMatch(myMatchViewModel: MyMatchViewModel) : String {
    return myMatchViewModel.errorText.value
}