package com.devuz.foundation_android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.devuz.foundation_android.ui.theme.Foundation_androidTheme
import com.devuz.foundation_android.ui.theme.RickPrimary
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()

            Foundation_androidTheme {
                Box (

                ){
//                    innerPadding ->
                    NavigationHost(
                        navController = navController,
                        innerPadding = PaddingValues()
                    )
                }
            }
        }
    }



}

