package com.example.firebaseaula.domain.usecase

import com.example.firebaseaula.repository.TaskRepository
import javax.inject.Inject

class DeleteTaskUseCase @Inject constructor(
    private val repository: TaskRepository
) {
    suspend operator fun invoke(taskId: String) {
        repository.deleteTask(taskId)
    }

}