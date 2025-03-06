package com.example.firebaseaula.ui.viewModel

import androidx.lifecycle.ViewModel
import com.example.firebaseaula.repository.UsersRepository
import com.example.firebaseaula.ui.state.SignInUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val repository: UsersRepository
): ViewModel(){
    private val _uiState = MutableStateFlow(SignInUiState())
    val uiState = _uiState.asStateFlow()

    init {
        _uiState.update { currentState ->
            currentState.copy(
                onEmailChange = {email ->
                    _uiState.update {
                        it.copy(email = email)
                    }
                },
                onPasswordChange = {password ->
                    _uiState.update {
                        it.copy(password = password)
                    }
                }
            )
        }
    }
    fun authenticate(){
        with(_uiState.value){
            _uiState.update {
                it.copy(
                    isAuthenticated = repository.authenticate(
                        email,
                        password
                    )
                )
            }
        }
    }
}