package com.example.firebaseaula.ui.viewModel

import javax.inject.Inject

class FakeHomeViewModel @Inject constructor(){

    /*
    val tasks = MutableStateFlow(
        listOf(
            Task(id = "1", name = "Comprar leite", isChecked = false),
            Task(id = "2", name = "Enviar e-mail", isChecked = true),
            Task(id = "3", name = "Estudar Kotlin", isChecked = false)
        )
    )

    val isRandomOrder = MutableStateFlow(false)
    val taskBeingEdited = MutableStateFlow<Task?>(null)

    fun toggleOrder() {
        isRandomOrder.value = !isRandomOrder.value
    }

    fun deleteTask(taskId: String) {
        tasks.value = tasks.value.filterNot { it.id == taskId }
    }

    fun editTask(task: Task) {
        taskBeingEdited.value = task
    }

    fun checkTask(taskId: String, isChecked: Boolean) {
        tasks.value = tasks.value.map { task ->
            if (task.id == taskId) task.copy(isChecked = isChecked) else task
        }
    }

    fun cancelEdit() {
        taskBeingEdited.value = null
    }

 */
}
