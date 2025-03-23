package com.example.firebaseaula.ui.navigation

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.firebaseaula.ui.screens.AddTaskScreen
import com.example.firebaseaula.ui.viewModel.AddTaskViewModel

fun NavGraphBuilder.addTaskScreen(
    onPopBackStack: () -> Unit
){
    composable (AppGraph.home.ADD_TASK) {
        val viewModel: AddTaskViewModel = hiltViewModel()
        val isLoading by viewModel.isLoading.collectAsState()
        AddTaskScreen(
            onClick = { taskName ->
                viewModel.addTask(taskName) {
                    onPopBackStack()
                }
            },
            isLoading = isLoading
        )
    }
}