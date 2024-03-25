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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
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
import com.example.mymatch.beans.matchList

import com.example.mymatch.component.teamA
import com.example.mymatch.component.teamB
import com.example.mymatch.component.versus
import com.example.mymatch.ui.theme.MyMatchTheme
import com.example.mymatch.viewmodel.MainViewModel
import com.google.ai.client.generativeai.type.content


//Code affiché dans la Preview, thème claire, thème sombre
@Preview(showBackground = true, showSystemUi = true)
@Preview(showBackground = true, showSystemUi = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun MatchDetailPreview() {
    MyMatchTheme {
        Surface {

            val mainViewModel: MainViewModel = viewModel()
            mainViewModel.myList2.addAll(matchList)
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
                teamA(data = matchList[0])
                Spacer(modifier = Modifier.height(16.dp)) // Ajout d'un espace vertical entre les équipes A et B
                versus()
                Spacer(modifier = Modifier.height(16.dp)) // Ajout d'un espace vertical entre les équipes A et B
                teamB(data = matchList[1])
            }

        }
    }
}






