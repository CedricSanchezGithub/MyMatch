package com.example.mymatch.screens


import android.content.res.Configuration.UI_MODE_NIGHT_YES
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
import androidx.compose.ui.semantics.Role.Companion.Button
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.mymatch.beans.matchList

import com.example.mymatch.ui.theme.MyMatchTheme
import com.example.mymatch.viewmodel.MyMatchViewModel


//Code affiché dans la Preview, thème claire, thème sombre
@Preview(showBackground = true, showSystemUi = true)
@Preview(showBackground = true, showSystemUi = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun MatchDetailPreview() {
    MyMatchTheme {
        Surface {
            MatchDetailScreen(10,myMatchViewModel = MyMatchViewModel())
        }
    }
}





@Composable
fun MatchDetailScreen(id : Long?,
    navHostController: NavHostController? = null,
    myMatchViewModel: MyMatchViewModel
) {

    val idNav = myMatchViewModel.myList2.find { it.id == id }
    println("Test pour Anthony + ${idNav} ")


    Column(
                modifier = Modifier
                    // Couleur
                    .background(MaterialTheme.colorScheme.primary)
                    // Taille complète
                    .fillMaxSize()
            )
            {


                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = " My Match ",
                        fontSize = 30.sp,
                        textAlign = TextAlign.Center,
                        color = Color.Black,
                        modifier = Modifier.fillMaxWidth()

                    )
                }
                Spacer(modifier = Modifier.height(25.dp)) // Ajout d'un espace vertical entre les équipes A et B
                // Equipe
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth().padding(start = 10.dp)
                ) {
                    Spacer(modifier = Modifier.width(15.dp)) // Espace entre le haut de la colonne et la première rangée
                    if (idNav != null) {
                        Text(

                            text = idNav.equipe1,
                            fontSize = 30.sp,
                            color = Color.Black,
                            modifier = Modifier.weight(1f)
                        )
                        println("Test equipe 1 ")
                    }

                    if (idNav != null) {
                        Text(

                            text = idNav.equipe2,

                            textAlign = TextAlign.End,
                            fontSize = 30.sp,
                            color = Color.Black,
                            modifier = Modifier.weight(1f)
                        )
                        println("Test equipe 2 ")
                    }
                }

                Spacer(modifier = Modifier.height(16.dp)) // Ajout d'un espace vertical entre les équipes A et B


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
                    ) {
                        if (idNav != null) {
                            Text(
                                text = idNav.score_equipe1.toString() ,
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
                            .padding(start = 10.dp)

                    ){
                        if (idNav != null) {
                            Text(
                                text = idNav.score_equipe2.toString() ,
                                modifier = Modifier.align(Alignment.Center)
                            )
                        }
                    }

                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Bouton +1 Equipe 1
                    Button(
                        onClick = {
                            if (idNav != null) {
                                idNav.id?.let { myMatchViewModel.addScore(it, 1) }
                            }
                        },
                        contentPadding = ButtonDefaults.ButtonWithIconContentPadding,
                        modifier = Modifier.padding(start = 69.dp)
                    )
                    {
                        Icon(
                            Icons.Filled.Add,
                            contentDescription = "Localized description",
                            modifier = Modifier.size(ButtonDefaults.IconSize)
                        )
                        Text(
                            text = "1",
                            fontSize = 20.sp // Augmentation de la taille du texte à 20sp
                        )
                    }
                    Spacer(modifier = Modifier.width(80.dp))

                    // Bouton +1 Equipe 2
                    Button(
                        onClick = {
                            if (idNav != null) {
                                idNav.id?.let { myMatchViewModel.addScore(it, 2) }
                            }                        },
                        contentPadding = ButtonDefaults.ButtonWithIconContentPadding,
                        modifier = Modifier.padding(start = 42.dp)
                    )
                    {
                        Icon(
                            Icons.Filled.Add,
                            contentDescription = "Localized description",
                            modifier = Modifier.size(ButtonDefaults.IconSize)
                        )
                        Text(
                            text = "1",
                            fontSize = 20.sp // Augmentation de la taille du texte à 20sp
                        )
                    }

                }
                // Bouton match terminé
                Row {

                    Button(
                        onClick = {

                        },
                        contentPadding = ButtonDefaults.ButtonWithIconContentPadding,
                        modifier = Modifier.padding(start = 42.dp).background(color = Color.Red)
                    )
                    {
                        Icon(
                            Icons.Filled.Add,
                            contentDescription = "Localized description",
                            modifier = Modifier.size(ButtonDefaults.IconSize)
                        )
                        Text(
                            text = "Match Terminé",
                            fontSize = 20.sp // Augmentation de la taille du texte à 20sp
                        )
                    }
                }
                Spacer(modifier = Modifier.height(16.dp)) // Ajout d'un espace vertical entre les équipes A et B

            }

        }


