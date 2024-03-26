package com.example.mymatch.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mymatch.R
import com.example.mymatch.beans.MatchBean


@Composable
fun teamA(data : MatchBean){
    Column(modifier = Modifier.background(MaterialTheme.colorScheme.tertiary ).border(width = 1.dp, color = Color.Black)
    ) {


        Spacer(modifier = Modifier.height(15.dp)) // Espace entre le haut de la colonne et la première rangée
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth().padding(start = 10.dp)
        ) {
            Spacer(modifier = Modifier.width(15.dp)) // Espace entre le haut de la colonne et la première rangée
            Text(
                text = data.equipe1,
                fontSize = 30.sp,
                color = Color.Black,
                modifier = Modifier.weight(1f)
            )

            Text(
                text = data.equipe2,
                textAlign = TextAlign.End,
                fontSize = 30.sp,
                color = Color.Black,
                modifier = Modifier.weight(1f)
            )
        }
        Spacer(modifier = Modifier.height(15.dp)) // Espace entre le haut de la colonne et la première rangée


    }
}
