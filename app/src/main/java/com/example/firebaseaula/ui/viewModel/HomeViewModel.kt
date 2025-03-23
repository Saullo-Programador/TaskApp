package com.example.firebaseaula.ui.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firebaseaula.authentication.FirebaseAuthRepository
import com.example.firebaseaula.domain.usecase.DeleteTaskUseCase
import com.example.firebaseaula.domain.usecase.GetTasksUseCase
import com.example.firebaseaula.models.Task
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val firebaseAuthRepository: FirebaseAuthRepository,
    private val getTasksUseCase: GetTasksUseCase,
    private val deleteTaskUseCase: DeleteTaskUseCase
) : ViewModel() {

    private val _tasks = MutableStateFlow<List<Task>>(emptyList())
    val tasks: StateFlow<List<Task>> = _tasks

    private val _isRandomOrder = MutableStateFlow(false) // Estado do modo aleatório
    val isRandomOrder: StateFlow<Boolean> = _isRandomOrder

    init {
        loadTasks()
    }

    private fun loadTasks() {
        val userId = firebaseAuthRepository.getCurrentUserId()
        if (userId != null) {
            viewModelScope.launch {
                try {
                    val taskList = getTasksUseCase(userId = userId)
                    _tasks.value = if (_isRandomOrder.value) taskList.shuffled() else taskList
                } catch (e: Exception) {
                    Log.e("HomeViewModel", "Error loading tasks", e)
                }
            }
        } else {
            Log.e("HomeViewModel", "User not authenticated")
        }
    }

    fun toggleOrder() {
        _isRandomOrder.value = !_isRandomOrder.value
        loadTasks()
    }

    fun deleteTask(taskId: String) {
        viewModelScope.launch {
            try {
                deleteTaskUseCase(taskId)
                loadTasks()
            } catch (e: Exception) {
                Log.e("HomeViewModel", "Error deleting task", e)
            }
        }
    }

    fun signOut() {
        firebaseAuthRepository.signOut()
    }
}
