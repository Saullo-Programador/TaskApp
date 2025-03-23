package com.example.firebaseaula.domain.usecase


import com.example.firebaseaula.models.Task
import com.example.firebaseaula.repository.TaskRepository
import javax.inject.Inject

class GetTasksUseCase @Inject constructor(
    private val repository: TaskRepository
) {
    suspend operator fun invoke(userId: String): List<Task> = repository.getTasks(userId = userId)
}
