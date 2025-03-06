package com.example.firebaseaula.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.firebaseaula.ui.screens.HomeScreen
import com.example.firebaseaula.ui.screens.SignInScreen
import com.example.firebaseaula.ui.screens.SignUpScreen
import com.example.firebaseaula.ui.screens.WelcomeScreen


fun NavGraphBuilder.welcomeNavGraph(navController: NavHostController){
    navigation(
        route = AppGraph.welcome.ROOT,
        startDestination = AppGraph.welcome.INITIAL
    ){
        composable(route = AppGraph.welcome.INITIAL) {
            WelcomeScreen(navController)
        }
    }
}

fun NavGraphBuilder.authNavGraph(navController: NavHostController){
    navigation(
        route = AppGraph.auth.ROOT,
        startDestination = AppGraph.auth.SIGN_IN
    ){
        composable(
            route = AppGraph.auth.SIGN_IN
        ){
            SignInScreen(
                onSignInClick = {
                    navController.navigate(AppGraph.home.ROOT) {
                        popUpTo(AppGraph.auth.ROOT) { inclusive = true }
                    }
                },
                onSignUpClick = {
                    navController.navigate(AppGraph.auth.SIGN_UP)
                }
            )
        }
        composable(
            route = AppGraph.auth.SIGN_UP
        ){
            SignUpScreen(
                onSignUpClick = {
                    navController.navigate(AppGraph.auth.SIGN_IN)
                }
            )
        }
    }
}

fun NavGraphBuilder.homeNavGraph(navController: NavHostController){
    navigation(
        route = AppGraph.home.ROOT,
        startDestination = AppGraph.home.HOME
    ){
        composable(
            route = AppGraph.home.HOME
        ){
            HomeScreen(
                onSairClick = {
                    navController.popBackStack(AppGraph.auth.ROOT,inclusive = true)
                    navController.navigate(AppGraph.auth.SIGN_IN)
                }
            )
        }
    }
}
