package com.example.firebaseaula.authentication

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.tasks.await
import org.checkerframework.checker.units.qual.Current
import javax.inject.Inject

data class AuthResult(
    val currentUser: FirebaseUser? = null,
    val isInitLoading: Boolean = true
)

class FirebaseAuthRepository @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) {
    private val _currentUser = MutableStateFlow(AuthResult())
    val currentUser = _currentUser.asStateFlow()

    init {
        firebaseAuth.addAuthStateListener {firebaseAuth ->
            _currentUser.update {
                it.copy(
                    currentUser = firebaseAuth.currentUser,
                    isInitLoading = false
                )
            }
        }
    }

    suspend fun signUp(email:String, password: String){
        firebaseAuth.createUserWithEmailAndPassword(email,password)
            .await()
    }
    suspend fun signIn(email: String, password: String){
        firebaseAuth.signInWithEmailAndPassword(email,password)
            .await()
    }
    fun signOut(){
        firebaseAuth.signOut()
    }
    fun getCurrentUserId(): String? {
        return firebaseAuth.currentUser?.uid
    }
}