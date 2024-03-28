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
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.example.mymatch.R
import com.example.mymatch.Routes
import com.example.mymatch.beans.MatchBean
import com.example.mymatch.ui.theme.MyMatchTheme
import com.example.mymatch.viewmodel.MyMatchViewModel
import java.text.SimpleDateFormat
import java.util.Locale


//Code affiché dans la Preview, thème claire, thème sombre
@Preview(showBackground = true, showSystemUi = true)
@Preview(showBackground = true, showSystemUi = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun AllMatchScreenPreview() {
    MyMatchTheme {
        Surface() {
            AllMatchScreen(myMatchViewModel = MyMatchViewModel())
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
            items(myMatchViewModel.myList2.size) { index ->
                val match = myMatchViewModel.myList2[index]
                PictureRowItem(
                    data = match,
                    onPictureClick = {
                        navHostController?.navigate(
                            Routes.MatchDetailScreen.withObject(
                                match
                            )
                        )
                        println("testEquipe")
                    },
                    myMatchViewModel = myMatchViewModel
                )

                // Ajouter une Divider après chaque élément, sauf le dernier
                if (index < myMatchViewModel.myList2.size - 1) {
                    Divider(modifier = Modifier.padding(horizontal = 16.dp), color = Color.Gray)
                }
            }
        }
        // Boutons
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Row {
                Button(
                    onClick = {
                        myMatchViewModel.loadList()
                        println("click list")
                    },
                    contentPadding = ButtonDefaults.ButtonWithIconContentPadding
                ) {
                    Icon(
                        Icons.Filled.Refresh,
                        contentDescription = "Localized description",
                        modifier = Modifier.size(ButtonDefaults.IconSize)
                    )
                    Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                    Text("Actualiser la liste")
                }

                Spacer(Modifier.size(ButtonDefaults.IconSpacing))


                if (myMatchViewModel.dialogShown.value) {
                    AlertDialog(
                        onDismissRequest = {
                            myMatchViewModel.dialogShown.value = false
                        },
                        title = { Text(text = "Créer un match") },
                        text = {
                            Column {
                                TextField(

                                    value = myMatchViewModel.equipe1.value,
                                    onValueChange = { myMatchViewModel.equipe1.value = it },
                                    label = { Text("Nom de l'équipe 1") }
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                TextField(
                                    value = myMatchViewModel.equipe2.value,
                                    onValueChange = { myMatchViewModel.equipe2.value = it },
                                    label = { Text("Nom de l'équipe 2") }
                                )
                            }
                        },
                        confirmButton = {
                            Button(
                                onClick = {
                                    println("click test creer")
                                    println(myMatchViewModel.equipe1.value)
                                    println(myMatchViewModel.equipe2.value)
                                    myMatchViewModel.createMatch(
                                        myMatchViewModel.equipe1.value,
                                        myMatchViewModel.equipe2.value
                                    )
                                    myMatchViewModel.dialogShown.value = false
                                   // myMatchViewModel.equipe1 = equipe1
                                //equipe2 = equipe2
                                }
                            ) {
                                // Créer le match
                                Text("Créer")
                            }
                        },
                        dismissButton = {
                            Button(
                                // Fermer boite de dialogue
                                onClick = { myMatchViewModel.dialogShown.value = false }
                            ) {
                                Text("Annuler")
                            }
                        }
                    )
                }



                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Row {
                        // ...

                        Button(
                            onClick = { myMatchViewModel.dialogShown.value = true },
                            contentPadding = ButtonDefaults.ButtonWithIconContentPadding
                        ) {
                            Icon(
                                Icons.Filled.Add,
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
    }
}


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun PictureRowItem(
    modifier: Modifier = Modifier,
    data: MatchBean,
    onPictureClick: () -> Unit,
    myMatchViewModel: MyMatchViewModel
) {
    // Date
    val formattedDate = myMatchViewModel.formatDate(System.currentTimeMillis())
    Text(
        text = "Date: ${formatDate(data.date ?: 0L)}",
        fontSize = 20.sp,
        textAlign = TextAlign.Center,
        color = Color.Black,
        modifier = Modifier.fillMaxWidth()
    )

    Row(
        verticalAlignment = Alignment.CenterVertically,
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
            // Équipe A
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = data.equipe1,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    modifier = Modifier
                        .padding(start = 5.dp) // Décalage à gauche
                        .width(150.dp), // Largeur fixe pour le texte de l'équipe 1
                )
                Spacer(modifier = Modifier.width(8.dp)) // Espace entre le texte de l'équipe 1 et le "-"
                Text(
                    text = " - ",
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.weight(1f)) // Espace flexible au milieu pour centrer le texte "-"
                Text(
                    text = data.equipe2,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    modifier = Modifier
                        .padding(end = 5.dp) // Décalage à droite
                        .weight(1f) // Permet au texte de prendre le reste de l'espace disponible
                )
            }
        }

    }

}
fun formatDate(milliseconds: Long): String {
    val dateFormat = SimpleDateFormat("dd/MM/yyyy - HH:mm", Locale.getDefault())
    val date = (milliseconds)
    return dateFormat.format(date)
}





