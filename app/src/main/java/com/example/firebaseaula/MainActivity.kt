package com.example.firebaseaula

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.firebaseaula.ui.navigation.AppGraph
import com.example.firebaseaula.ui.navigation.authNavGraph
import com.example.firebaseaula.ui.navigation.homeNavGraph
import com.example.firebaseaula.ui.navigation.welcomeNavGraph
import com.example.firebaseaula.ui.theme.FirebaseAulaTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FirebaseAulaTheme {
                Surface(
                    modifier = Modifier
                        .windowInsetsPadding(WindowInsets.statusBars)
                        .fillMaxSize()
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        route = AppGraph.initial.ROOT,
                        startDestination = AppGraph.welcome.ROOT
                    ){
                        welcomeNavGraph(navController = navController)
                        authNavGraph(navController = navController)
                        homeNavGraph(navController = navController)
                    }
                }
            }
        }
    }
}
