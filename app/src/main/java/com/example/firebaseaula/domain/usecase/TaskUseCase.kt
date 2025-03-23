package com.example.firebaseaula.domain.usecase

import com.example.firebaseaula.models.Task
import com.example.firebaseaula.repository.TaskRepository
import javax.inject.Inject

class TaskUseCase @Inject constructor(
    private val taskRepository: TaskRepository
) {
    suspend fun addTask(task: Task) = taskRepository.addTask(task)
    suspend fun getTasks(userId: String): List<Task> = taskRepository.getTasks(userId)
}