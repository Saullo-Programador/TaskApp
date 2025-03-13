package com.example.firebaseaula.ui.viewModel

import androidx.lifecycle.ViewModel
import com.example.firebaseaula.authentication.FirebaseAuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val firebaseAuthRepository: FirebaseAuthRepository
):ViewModel() {

    fun signOut(){
        firebaseAuthRepository.signOut()
    }
}