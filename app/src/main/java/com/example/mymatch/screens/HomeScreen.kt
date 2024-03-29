package com.example.mymatch.screens

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mymatch.R
import com.example.mymatch.ui.theme.MyMatchTheme
import com.example.mymatch.viewmodel.MyMatchViewModel

@Preview(showBackground = true, showSystemUi = true)
@Preview(showBackground = true, showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun HomeScreenPreview() {
    // Modifier pour remplir l'écran avec le fond bleu foncé
    Surface() {
        HomeScreen()

    }
}


@Composable
fun HomeScreen(navController: NavController? = null) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(50.dp))
        // Logo de l'application
        Image(
            painter = painterResource(id = R.drawable.logo1), // Assurez-vous de remplacer "R.drawable.logo" par votre propre ressource d'image
            contentDescription = "Logo de MyMatch",
            modifier = Modifier.padding(16.dp),
            contentScale = ContentScale.Fit
        )
        Spacer(modifier = Modifier.height(10.dp))
        // Style de texte pour le nom de l'application
        Row {
            Text(
                text = "Bienvenue sur l'application",
                style = TextStyle(
                    color = Color.White,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Row {
            Text(
                text = "MyMatch",
                style = TextStyle(
                    color = Color.White,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
            )

        }
        Row {
            Button(
                onClick = {
                    navController?.navigate("AllMatchScreen")
                }, // Assurez-vous d'ajuster la destination selon votre configuration de navigation
                modifier = Modifier
                    .padding(16.dp)

                    .clickable { navController?.navigate("AllMatchScreen") },
                colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.secondary)
            ) {
                Text("Voir tous les matchs")
            }
        }


    }
}