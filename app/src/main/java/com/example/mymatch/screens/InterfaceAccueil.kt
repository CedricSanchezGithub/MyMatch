package com.example.mymatch.screens

import android.content.res.Configuration
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mymatch.beans.matchList
import com.example.mymatch.ui.theme.MyMatchTheme
import com.example.mymatch.viewmodel.MyMatchViewModel


//Code affiché dans la Preview, thème claire, thème sombre
@Preview(showBackground = true, showSystemUi = true)
@Preview(showBackground = true, showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun InterfaceAccueilPreview() {
    MyMatchTheme {
        Surface() {
            val myMatchViewModel: MyMatchViewModel = viewModel()
            myMatchViewModel.myList2.addAll(matchList)
            AllMatchScreen(myMatchViewModel = myMatchViewModel)
        }
    }
}

@Composable
fun InterfaceAccueil(){

}