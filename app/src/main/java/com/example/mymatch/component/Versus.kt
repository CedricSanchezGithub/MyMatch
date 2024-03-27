package com.example.mymatch.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mymatch.viewmodel.MyMatchViewModel


@Composable
fun versus(myMatchViewModel: MyMatchViewModel) {
    Column(modifier = Modifier.background(MaterialTheme.colorScheme.primary)) {
        Spacer(modifier = Modifier.height(15.dp)) // Espace entre le haut de la colonne et la première rangée

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
                Text(
                    text = myMatchViewModel.myList2[0].score_equipe1.toString() ,
                    modifier = Modifier.align(Alignment.Center)
                )
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
                Text(
                    text = myMatchViewModel.myList2[0].score_equipe2.toString() ,
                    modifier = Modifier.align(Alignment.Center)
                )
            }

        }


        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = {
                    myMatchViewModel.myList2[0].id?.let { myMatchViewModel.addScore(it,1) }
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

            Button(
                onClick = {
                    myMatchViewModel.myList2[0].id?.let { myMatchViewModel.addScore(it,2) }
                },
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
    }
}

