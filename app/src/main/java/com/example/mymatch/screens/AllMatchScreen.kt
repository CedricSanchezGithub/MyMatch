package com.example.mymatch.screens

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
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
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.example.mymatch.R
import com.example.mymatch.Routes
import com.example.mymatch.beans.MatchBean
import com.example.mymatch.ui.theme.MyMatchTheme
import com.example.mymatch.viewmodel.MyMatchViewModel


//Code affiché dans la Preview, thème claire, thème sombre
@Preview(showBackground = true, showSystemUi = true)
@Preview(showBackground = true, showSystemUi = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun AllMatchScreenPreview() {
    MyMatchTheme {
        Surface() {

        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AllMatchScreen(
    navHostController: NavHostController? = null,
    myMatchViewModel: MyMatchViewModel
) {
    //Couleur à retirer lors de l'utilisation des thèmes de couleur
    Column(modifier = Modifier.background(MaterialTheme.colorScheme.background)) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.width(50.dp))

            // Image à gauche de l'écran
            Image(
                painter = painterResource(id = R.drawable.coupe),
                contentDescription = "Logo équipe A",
                modifier = Modifier
                    .width(100.dp) // Largeur souhaitée
                    .aspectRatio(1f) // Conserver le ratio hauteur/largeur
                    .scale(2f, 2f) // Pas de mise à l'échelle en hauteur, échelle de 1 en hauteur
            )

            Spacer(modifier = Modifier.width(5.dp))

            // Texte centré horizontalement
            Text(
                text = " - My Match",
                fontSize = 30.sp,
                textAlign = TextAlign.Center,
                color = Color.Black,
                modifier = Modifier.weight(1f) // Le texte prendra tout l'espace disponible
            )
        }

        Spacer(modifier = Modifier.height(16.dp)) // Ajoute un espace de 16dp entre le titre et la colonne

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.weight(1f)
        ) {
            val filterList =
                myMatchViewModel.myList2 //.filter { it.title.contains(mainViewModel.searchText.value, ignoreCase = true) }

            items(filterList.size) {
                PictureRowItem(data = filterList[it],
                    onPictureClick = {
                        navHostController?.navigate(
                            Routes.MatchDetailScreen.withObject(
                                filterList[it]
                            )
                        )
                    })
            }
        }

        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Row {
                Button(
                    onClick = { myMatchViewModel.searchText.value = "" },
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
                    onClick = { myMatchViewModel.loadData() },
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
}


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun PictureRowItem(
    modifier: Modifier = Modifier,
    data: MatchBean,
    onPictureClick: () -> Unit,
) {

    Row(
        modifier = modifier
            .height(110.dp)
            .background(Color.White)
            .clickable(onClick = onPictureClick)


    ) {


        Column(
            Modifier
                .padding(8.dp)
                .fillMaxWidth()
        ) {

            // Titre
            Text(
                text = "data.title_A",
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
                    modifier = Modifier.size(70.dp) // Taille du logo
                )
                Spacer(modifier = Modifier.width(15.dp)) // Espace entre le logo et le texte
                Text(
                    text = buildAnnotatedString {
                        append(data.equipe1)
                        withStyle(style = SpanStyle(color = Color.Red)) {
                            append("${data.score_equipe} ")
                        }

                    },
                    fontSize = 20.sp,
                    color = Color.Blue,
                    modifier = Modifier.weight(1f) // Fait en sorte que le texte prenne le reste de l'espace disponible

                )



                Spacer(modifier = Modifier.width(15.dp)) // Espace entre le logo et le texte

                Text(
                    text = buildAnnotatedString {
                        withStyle(style = SpanStyle(color = Color.Red)) {
                            append("${data.score_equipe2} ")
                        }
                        append(data.equipe2)
                    },
                    fontSize = 20.sp,
                    color = Color.Blue,
                    modifier = Modifier.weight(1f) // Fait en sorte que le texte prenne le reste de l'espace disponible
                )

                Image(
                    painter = painterResource(id = R.drawable.hblm), // Remplacez R.drawable.paris_saint_germain_logo par la référence à votre ressource d'image
                    contentDescription = "Logo Paris Saint-Germain",
                    modifier = Modifier.size(70.dp) // Taille du logo
                )
            }
        }

    }
}





