package com.example.mymatch.screens


import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.mymatch.beans.matchList

import com.example.mymatch.component.teamA
import com.example.mymatch.component.versus
import com.example.mymatch.ui.theme.MyMatchTheme
import com.example.mymatch.viewmodel.MyMatchViewModel


//Code affiché dans la Preview, thème claire, thème sombre
@Preview(showBackground = true, showSystemUi = true)
@Preview(showBackground = true, showSystemUi = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun MatchDetailPreview() {
    MyMatchTheme {
        Surface {
            MatchDetailScreen(myMatchViewModel = MyMatchViewModel())
        }
    }
}





@Composable
fun MatchDetailScreen(
    navHostController: NavHostController? = null,
    myMatchViewModel: MyMatchViewModel
) {

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
                teamA(myMatchViewModel = myMatchViewModel)
                Spacer(modifier = Modifier.height(16.dp)) // Ajout d'un espace vertical entre les équipes A et B
                versus(myMatchViewModel = myMatchViewModel)
                Spacer(modifier = Modifier.height(16.dp)) // Ajout d'un espace vertical entre les équipes A et B

            }

        }


