package com.example.firebaseaula.ui.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firebaseaula.authentication.FirebaseAuthRepository
import com.example.firebaseaula.domain.usecase.TaskUseCase
import com.example.firebaseaula.models.Task
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AddTaskViewModel @Inject constructor(
    private val taskUseCase: TaskUseCase,
    private val fireAuth: FirebaseAuthRepository
) : ViewModel() {

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _tasks = MutableStateFlow<List<Task>>(emptyList())
    val tasks: StateFlow<List<Task>> get() = _tasks


    fun addTask(taskName: String, onSuccess: () -> Unit) {
        val userId = fireAuth.getCurrentUserId()
        if (userId != null) {
            val task = Task(name = taskName, userId = userId)
            viewModelScope.launch {
                try {
                    _isLoading.value = true
                    taskUseCase.addTask(task)
                    loadUserTasks()
                    onSuccess()
                    loadUserTasks()
                } catch (e: Exception) {
                    _isLoading.value = false
                    Log.e("AddTaskViewModel", "Erro ao adicionar tarefa", e)
                }
            }
        }
    }
    fun loadUserTasks() {
        val userId = fireAuth.getCurrentUserId()
        if (userId != null) {
            viewModelScope.launch {
                try {
                    _isLoading.value = true
                    _tasks.value = taskUseCase.getTasks(userId)
                    _isLoading.value = false
                } catch (e: Exception) {
                    Log.e("AddTaskViewModel", "Erro ao carregar tarefas do usuário", e)
                }
            }
        }
    }
    init {
        loadUserTasks()
    }
}