package com.example.firebaseaula.repository

import com.example.firebaseaula.models.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject


class TaskRepository @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val fireAuth: FirebaseAuth
) {
    suspend fun getTasks(userId: String): List<Task> {
        val querySnapshot = firestore.collection("tasks")
            .whereEqualTo("userId", userId)
            .get()
            .await()

        return querySnapshot.documents.map { doc ->
            Task(
                id = doc.id,
                name = doc.getString("name") ?: "",
                userId = doc.getString("userId") ?: ""
            )
        }
    }

    suspend fun addTask(task: Task) {
        val user = fireAuth.currentUser ?: throw Exception("Usuário não autenticado")
        val newTask = task.copy(userId = user.uid)
        firestore.collection("tasks").add(newTask).await()

    }

    suspend fun deleteTask(taskId: String) {
        firestore.collection("tasks").document(taskId).delete().await()
    }
}
