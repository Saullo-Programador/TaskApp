package com.example.firebaseaula.ui.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firebaseaula.authentication.FirebaseAuthRepository
import com.example.firebaseaula.domain.usecase.DeleteTaskUseCase
import com.example.firebaseaula.domain.usecase.GetTasksUseCase
import com.example.firebaseaula.domain.usecase.UpdateTaskUseCase
import com.example.firebaseaula.models.Task
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val firebaseAuthRepository: FirebaseAuthRepository,
    private val getTasksUseCase: GetTasksUseCase,
    private val deleteTaskUseCase: DeleteTaskUseCase,
    private val updateTaskUseCase: UpdateTaskUseCase
) : ViewModel() {

    private val _tasks = MutableStateFlow<List<Task>>(emptyList())
    val tasks: StateFlow<List<Task>> = _tasks

    private val _taskBeingEdited = MutableStateFlow<Task?>(null)
    val taskBeingEdited: StateFlow<Task?> = _taskBeingEdited

    private val _isRandomOrder = MutableStateFlow(false) // Estado do modo aleatório
    val isRandomOrder: StateFlow<Boolean> = _isRandomOrder

    private val _userName = MutableStateFlow<String?>(null)
    val userName = _userName.asStateFlow()


    private val _isChecked = MutableStateFlow(false)
    val isChecked: StateFlow<Boolean> = _isChecked

    init {
        viewModelScope.launch {
            val name = firebaseAuthRepository.getUserName()
            _userName.value = name
        }
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

    fun editTask(task: Task) {
        _taskBeingEdited.value = task
    }

    fun updateTask(taskId: String, newName: String, onSuccess: () -> Unit) {
        viewModelScope.launch {
            try {
                updateTaskUseCase(taskId, newName)
                _taskBeingEdited.value = null
                onSuccess()
                loadTasks()
            } catch (e: Exception) {
                Log.e("HomeViewModel", "Error updating task", e)
            }
        }
    }
    fun cancelEdit() {
        _taskBeingEdited.value = null
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


    fun checkTask(taskId: String, isChecked: Boolean) {
        viewModelScope.launch {
            try {
                val updatedTasks = _tasks.value.map {
                    if (it.id == taskId) it.copy(isChecked = isChecked) else it
                }
                _tasks.value = updatedTasks
            } catch (e: Exception) {
                Log.e("HomeViewModel", "Error checking task", e)
            }
        }
    }

    fun signOut() {
        firebaseAuthRepository.signOut()
    }
}
