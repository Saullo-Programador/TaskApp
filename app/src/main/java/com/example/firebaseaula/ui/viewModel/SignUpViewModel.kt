package com.example.firebaseaula.ui.viewModel

import androidx.lifecycle.ViewModel
import com.example.firebaseaula.ui.state.SignUpUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(): ViewModel(){
    private val _uiState = MutableStateFlow(SignUpUiState())
    val uiState = _uiState.asStateFlow()

    init {
        _uiState.update { currentState ->
            currentState.copy(
                onUserChange = {user ->
                    _uiState.update {
                        it.copy ( user = user )
                    }
                },
                onEmailChange = {email ->
                    _uiState.update {
                        it.copy ( email = email )
                    }
                },
                onPasswordChange = {password ->
                    _uiState.update {
                        it.copy ( password = password )
                    }
                },
                onConfirmPasswordChange = {password  ->
                    _uiState.update {
                        it.copy ( confirmPassword = password )
                    }
                }
            )
        }
    }

}