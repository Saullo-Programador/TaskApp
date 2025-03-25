package com.example.firebaseaula.domain.usecase

import com.example.firebaseaula.repository.TaskRepository
import javax.inject.Inject

class UpdateTaskUseCase @Inject constructor(
    private val repository: TaskRepository
) {
    suspend operator fun invoke(taskId: String, newName: String) {
        if (taskId.isNotBlank() && newName.isNotBlank()) {
            repository.updateTask(taskId = taskId, newName = newName)
        }else{
            throw IllegalArgumentException("ID da tarefa ou nome inválido")

        }
    }
}