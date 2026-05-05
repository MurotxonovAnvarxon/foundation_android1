package com.devuz.foundation_android

import SavedScreen
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.devuz.foundation_android.screens.detail.DetailScreen
import com.devuz.foundation_android.screens.home.HomeScreen
import com.devuz.foundation_android.ui.theme.RickPrimary

@Suppress("ParamsComparedByRef")
@Composable
fun NavigationHost(
    navController: NavHostController, innerPadding: PaddingValues
) {
    NavHost(
        navController = navController,
        startDestination = "home_screen",
        modifier = Modifier
            .background(color = RickPrimary)
            .statusBarsPadding()
            .navigationBarsPadding()
    ) {
        composable(route = "home_screen") {
            HomeScreen(
                onCharacterSelected = { characterId, characterName ->
                    navController.navigate("detail_screen/$characterId/$characterName")
                },

                innerPadding = innerPadding,
                onNavigateSavedScreen = {
                    navController.navigate("saved_screen")

                }
            )
        }
        composable(route = "detail_screen/{characterId}/{characterName}") { backStackEntry ->
            val characterId: String = backStackEntry.arguments?.getString("characterId") ?: "-1"
            val characterName: String = backStackEntry.arguments?.getString("characterName") ?: ""
            DetailScreen(
                onBackClicked = {
                    navController.navigateUp()
                }, characterId = characterId.toLong(), characterName = characterName
            )
        }
        composable(route = "saved_screen") { backStackEntry ->
            SavedScreen(
                onCharacterSelected = { characterId ->
//                    navController.navigate("detail_screen/$characterId/$characterName")
                }, onBackClicked = {
                    navController.navigateUp()
                }, innerPadding = innerPadding
            )
        }
    }
}