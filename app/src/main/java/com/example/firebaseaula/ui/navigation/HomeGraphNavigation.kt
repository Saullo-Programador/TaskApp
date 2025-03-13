package com.example.firebaseaula.ui.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navOptions
import androidx.navigation.navigation
import com.example.firebaseaula.ui.screens.HomeScreen
import com.example.firebaseaula.ui.viewModel.HomeViewModel

fun NavGraphBuilder.homeGraph(

) {
    navigation(
        route = AppGraph.home.ROOT,
        startDestination = AppGraph.home.HOME
    ) {
        composable(
            route = AppGraph.home.HOME
        ) {
            val viewModel: HomeViewModel = hiltViewModel()
            HomeScreen(
                onSairClick = { viewModel.signOut() }
            )
        }
    }
}

fun NavHostController.navigateToHomeGraph(
    navOptions: NavOptions? = navOptions {
        popUpTo(graph.id) {
            inclusive = true
        }
    }
) {
    navigate(AppGraph.home.ROOT, navOptions)
}
