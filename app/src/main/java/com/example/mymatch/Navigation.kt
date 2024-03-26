package com.example.mymatch

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mymatch.beans.MatchBean
import com.example.mymatch.screens.AllMatchScreen
import com.example.mymatch.screens.MatchDetailScreen
import com.example.mymatch.viewmodel.MyMatchViewModel

sealed class Routes(val route: String) {
    //Route 1
    data object AllMatchScreen : Routes("AllMatchScreen")

    //Route 2 avec paramètre
    data object MatchDetailScreen : Routes("MatchDetailScreen/{id}") {
        //Méthode(s) qui vienne(nt) remplit le ou les paramètres
        fun withId(id: Int) = "MatchDetailScreen/$id"

        fun withObject(data: MatchBean) = "MatchDetailScreen/${data.id}"
    }

    data object MatchListScreen : Routes("mexicanlistScreen")
}

@Composable
fun AppNavigation() {

    val navHostController : NavHostController = rememberNavController()

    //viewModel appartient au framework peremt de récupérer une instance déjà existante s'il en existe une
    val myMatchViewModel: MyMatchViewModel = viewModel()


    //Import version avec Composable
    NavHost(navController = navHostController, startDestination = Routes.AllMatchScreen.route) {
        //Route 1 vers notre SearchScreen
        composable(Routes.AllMatchScreen.route) {
            //on peut passer le navHostController à un écran s'il déclenche des navigations
            AllMatchScreen(navHostController, myMatchViewModel = myMatchViewModel)
        }

        //Route 2 vers un écran de détail
        composable(
            route = Routes.MatchDetailScreen.route,
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) {
            val id = it.arguments?.getInt("id") ?: 1
            MatchDetailScreen(navHostController, myMatchViewModel=myMatchViewModel)
        }


    }
}