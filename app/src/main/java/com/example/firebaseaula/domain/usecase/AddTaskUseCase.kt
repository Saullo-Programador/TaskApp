package com.example.firebaseaula.domain.usecase

import com.example.firebaseaula.models.Task
import com.example.firebaseaula.repository.TaskRepository
import javax.inject.Inject


class AddTaskUseCase @Inject constructor(
    private val repository: TaskRepository
) {
    suspend operator fun invoke(task: Task) {
        repository.addTask(task)
    }
}