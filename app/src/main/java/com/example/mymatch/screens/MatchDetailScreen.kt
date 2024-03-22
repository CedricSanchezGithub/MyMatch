package com.example.mymatch.screens


import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.content.MediaType.Companion.Text
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Send
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.example.mymatch.R
import com.example.mymatch.beans.MatchBean
import com.example.mymatch.beans.PictureBean
import com.example.mymatch.beans.matchList

import com.example.mymatch.beans.pictureList
import com.example.mymatch.ui.theme.MyMatchTheme
import com.example.mymatch.viewmodel.MainViewModel
import com.google.ai.client.generativeai.type.content


//Code affiché dans la Preview, thème claire, thème sombre
@Preview(showBackground = true, showSystemUi = true)
@Preview(showBackground = true, showSystemUi = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun MatchDetailPreview() {
    MyMatchTheme {
        Surface() {

            val mainViewModel: MainViewModel = viewModel()
            mainViewModel.myList2.addAll(matchList)
            mainViewModel.searchText.value = "BC"
            mainViewModel.errorMessage.value = "Un message d'erreur"
            mainViewModel.runInProgress.value = true

           // MatchDetail(mainViewModel = mainViewModel)
            Column {
                Spacer(modifier = Modifier.height(16.dp)) // Ajout d'un espace vertical entre les équipes A et B

                teamA(modifier = Modifier.background(Color.LightGray), data = matchList[0])
                Spacer(modifier = Modifier.height(16.dp)) // Ajout d'un espace vertical entre les équipes A et B
                versus(modifier = Modifier.background(Color.White))
                Spacer(modifier = Modifier.height(16.dp)) // Ajout d'un espace vertical entre les équipes A et B

                teamB(modifier = Modifier.background(Color.LightGray), data = matchList[1])
            }

        }
    }
}

//Composable représentant l'ensemble de l'écran

@Composable
fun MatchDetail(
    navHostController: NavHostController? = null,
    mainViewModel: MainViewModel,
) {
    //Couleur à retirer lors de l'utilisation des thèmes de couleur
    Column(modifier = Modifier.background(Color.LightGray)) {

        Text(
            text = " My Match ",
            fontSize = 30.sp,
            textAlign = TextAlign.Center,
            color = Color.Blue,
            modifier = Modifier.fillMaxWidth()

        )

        Row(modifier = Modifier.background(Color.LightGray)){
            MatchGlobal(data = matchList[0])

        }

        Spacer(modifier = Modifier.height(16.dp)) // Ajoute un espace de 16dp entre le titre et la colonne

        Row {
            Button(
                onClick = { mainViewModel.searchText.value = "" },
                contentPadding = ButtonDefaults.ButtonWithIconContentPadding
            ) {
                Icon(
                    Icons.Filled.Clear,
                    contentDescription = "Localized description",
                    modifier = Modifier.size(ButtonDefaults.IconSize)
                )
                Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                Text("Ajouter +1 a Equipe A")
            }

            Spacer(Modifier.size(ButtonDefaults.IconSpacing))

            Button(
                onClick = { mainViewModel.loadData() },
                contentPadding = ButtonDefaults.ButtonWithIconContentPadding
            ) {
                Icon(
                    Icons.Filled.Send,
                    contentDescription = "Localized description",
                    modifier = Modifier.size(ButtonDefaults.IconSize)
                )
                Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                Text("Ajouter +1 a Equipe B")
            }
        }

    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MatchGlobal(modifier: Modifier = Modifier, data: MatchBean) {

    Row(
        modifier = modifier
            .height(100.dp)
            .background(Color.White)
    ) {

    // Equipe A 
        Column(
            Modifier
                .padding(8.dp)
                .fillMaxWidth()
        ) {

            // Titre
            Text(
                text = data.title_A,
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                color = Color.Black,
                modifier = Modifier.fillMaxWidth()

            )

            // Équipe A avec son logo et son score
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.psg), // Remplacez R.drawable.logo_equipe_a par la ressource correspondant au logo de l'équipe A
                    contentDescription = "Logo équipe A",
                    modifier = Modifier.size(24.dp) // Taille du logo
                )
                Spacer(modifier = Modifier.width(8.dp)) // Espace entre le logo et le texte
                Text(
                    text = "${data.title_A} ${data.Score_Equipe1}",
                    fontSize = 14.sp,
                    color = Color.Blue,
                    modifier = Modifier.weight(1f) // Fait en sorte que le texte prenne le reste de l'espace disponible
                )
            }

            Spacer(modifier = Modifier.width(8.dp)) // Espace entre le logo et le texte

            // Équipe B avec son logo et son score
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.hblm), // Remplacez R.drawable.paris_saint_germain_logo par la référence à votre ressource d'image
                    contentDescription = "Logo Paris Saint-Germain",
                    modifier = Modifier.size(24.dp) // Taille du logo
                )
                Spacer(modifier = Modifier.width(8.dp)) // Espace entre le logo et le texte
                Text(
                    text = "${data.title_A} ${data.Score_Equipe2}",
                    fontSize = 14.sp,
                    color = Color.Blue,
                    modifier = Modifier.weight(1f) // Fait en sorte que le texte prenne le reste de l'espace disponible
                )
            }
        }

    }
}



@Composable
fun teamA(modifier: Modifier, data :MatchBean){
Column(modifier = Modifier.background(Color.LightGray)) {

    Spacer(modifier = Modifier.height(30.dp)) // Espace entre le haut de la colonne et la première rangée

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Image(
            painter = painterResource(id = R.drawable.psg), // Remplacez R.drawable.logo_equipe_a par la ressource correspondant au logo de l'équipe A
            contentDescription = "Logo équipe A",
            modifier = Modifier.size(80.dp) // Taille du logo
        )
        Text(
            text = data.title_A,
            fontSize = 25.sp,
            color = Color.Black,
            modifier = Modifier.weight(1f) // Fait en sorte que le texte prenne le reste de l'espace disponible
        )
    }
    Row {
        Spacer(modifier = Modifier.width(80.dp)) // Espace à gauche de la deuxième rangée

        Text(
            text = "Derniers résultats = ${data.win}" ,
            fontSize = 20.sp,
            textAlign = TextAlign.Left,
            color = Color.Black,
            modifier = Modifier.fillMaxWidth()
        )
    }
}
}


@Composable
fun teamB(modifier: Modifier, data :MatchBean) {
    Column(modifier = Modifier.background(Color.LightGray)) {

        Spacer(modifier = Modifier.height(30.dp)) // Espace entre le haut de la colonne et la première rangée

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = R.drawable.hblm), // Remplacez R.drawable.logo_equipe_a par la ressource correspondant au logo de l'équipe A
                contentDescription = "Logo équipe A",
                modifier = Modifier.size(80.dp) // Taille du logo
            )
            Text(
                text = data.title_A,
                fontSize = 25.sp,
                color = Color.Black,
                modifier = Modifier.weight(1f) // Fait en sorte que le texte prenne le reste de l'espace disponible
            )
        }
        Row {
            Spacer(modifier = Modifier.width(80.dp)) // Espace à gauche de la deuxième rangée

            Text(
                text = "Derniers résultats = ${data.win}",
                fontSize = 20.sp,
                textAlign = TextAlign.Left,
                color = Color.Black,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
fun versus(modifier: Modifier) {
    Column(modifier = Modifier.background(Color.White)) {

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
                    .padding(end = 10.dp)
            )

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
                    .padding(start = 10.dp)
            )
        }
    }
}