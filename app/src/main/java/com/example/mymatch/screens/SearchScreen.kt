package com.example.mymatch.screens

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.content.MediaType.Companion.Text
import androidx.compose.foundation.layout.Arrangement
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
import com.example.mymatch.beans.PictureBean
import com.example.mymatch.beans.pictureList
import com.example.mymatch.ui.theme.MyMatchTheme
import com.example.mymatch.viewmodel.MainViewModel


//Code affiché dans la Preview, thème claire, thème sombre
@Preview(showBackground = true, showSystemUi = true)
@Preview(showBackground = true, showSystemUi = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun SearchScreenPreview() {
    MyMatchTheme {
        Surface() {

            val mainViewModel: MainViewModel = viewModel()
            mainViewModel.myList.addAll(pictureList)
            mainViewModel.searchText.value = "BC"
            mainViewModel.errorMessage.value = "Un message d'erreur"
            mainViewModel.runInProgress.value = true

            SearchScreen(mainViewModel = mainViewModel)
        }
    }
}

//Composable représentant l'ensemble de l'écran
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SearchScreen(
    navHostController: NavHostController? = null,
    mainViewModel: MainViewModel
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

        Spacer(modifier = Modifier.height(16.dp)) // Ajoute un espace de 16dp entre le titre et la colonne

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.weight(1f)
        ) {


            //
            val filterList =
                mainViewModel.myList //.filter { it.title.contains(mainViewModel.searchText.value, ignoreCase = true) }

            items(filterList.size) {
                PictureRowItem(data = filterList[it],
                    onPictureClick = {
                    })
            }
        }

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
                Text("Actualiser la liste")
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
                Text("Créer un match")
            }
        }

    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun PictureRowItem(modifier: Modifier = Modifier, data: PictureBean, onPictureClick: () -> Unit) {

    Row(
        modifier = modifier
            .height(100.dp)
            .background(Color.White)
    ) {


        Column(
            Modifier
                .padding(8.dp)
                .fillMaxWidth()
        ) {

            // Titre
            Text(
                text = data.title,
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
                    text = "${data.Equipe_A} ${data.Score_EquipeA}",
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
                    text = "${data.Equipe_B} ${data.Score_EquipeB}",
                    fontSize = 14.sp,
                    color = Color.Blue,
                    modifier = Modifier.weight(1f) // Fait en sorte que le texte prenne le reste de l'espace disponible
                )
            }
        }

    }
}





