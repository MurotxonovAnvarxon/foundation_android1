package com.devuz.foundation_android

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.devuz.foundation_android.screens.DetailScreen
import com.devuz.foundation_android.screens.home.HomeScreen
import com.devuz.foundation_android.ui.theme.RickPrimary

@Suppress("ParamsComparedByRef")
@Composable
fun NavigationHost(
    navController: NavHostController,
    innerPadding: PaddingValues
) {
    NavHost(
        navController = navController,
        startDestination = "home_screen",
        modifier = Modifier
            .background(color = RickPrimary)
            .padding(innerPadding)
    ) {
        composable(route = "home_screen") {
            HomeScreen(
                onCharacterSelected = { characterId ->
                    navController.navigate("detail_screen/$characterId")
                }
            )
        }
        composable(route = "detail_screen/{characterId}") {
            DetailScreen(
                onBackClicked = {
                    navController.navigateUp()
                }
            )
        }
    }
}