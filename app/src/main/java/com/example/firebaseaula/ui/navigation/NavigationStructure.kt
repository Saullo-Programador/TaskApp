package com.example.firebaseaula.ui.navigation

object AuthGraph {
    const val ROOT = "auth_graph"
    const val SIGN_IN = "sign_in"
    const val SIGN_UP = "sign_up"
}

object HomeGraph {
    const val ROOT = "home_graph"
    const val HOME = "home"
}

object WelcomeGraph {
    const val ROOT = "welcome_graph"
    const val INITIAL = "initial"
    const val SPLASH = "splash"
}

object RootGraph {
    const val ROOT = "root_graph"
}

object AppGraph {
    val welcome = WelcomeGraph
    val auth = AuthGraph
    val home = HomeGraph
    val initial = RootGraph
}