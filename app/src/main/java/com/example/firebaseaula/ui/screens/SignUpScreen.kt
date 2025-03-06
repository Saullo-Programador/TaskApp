package com.example.firebaseaula.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.firebaseaula.ui.components.CustomButton
import com.example.firebaseaula.ui.components.CustomTextField
import com.example.firebaseaula.ui.viewModel.SignUpViewModel

@Composable
fun SignUpScreen(
    viewModel: SignUpViewModel = hiltViewModel(),
    onSignUpClick: () -> Unit
) {

    val uiState by viewModel.uiState.collectAsState()

    SignUpContent(
        user = uiState.user,
        userOnValue = uiState.onUserChange,
        email = uiState.email,
        emailOnValue = uiState.onEmailChange,
        password = uiState.password,
        passwordOnValue = uiState.onPasswordChange,
        confirmPassword = uiState.confirmPassword,
        confirmPasswordOnValue = uiState.onConfirmPasswordChange,
        startOnClick = onSignUpClick,
    )
}

@Composable
fun SignUpContent(
    modifier: Modifier = Modifier,
    user: String,
    userOnValue: (String) -> Unit,
    email: String,
    emailOnValue: (String) -> Unit,
    password: String,
    passwordOnValue: (String) -> Unit,
    confirmPassword: String,
    confirmPasswordOnValue: (String) -> Unit,
    startOnClick: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(23.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SignUpForm(
            user = user,
            userOnValue = userOnValue,
            email = email,
            emailOnValue = emailOnValue,
            password = password,
            passwordOnValue = passwordOnValue,
            confirmPassword = confirmPassword,
            confirmPasswordOnValue = confirmPasswordOnValue,
            startOnClick = startOnClick
        )
    }
}

@Composable
fun SignUpForm(
    modifier: Modifier = Modifier,
    user: String,
    userOnValue: (String) -> Unit,
    email: String,
    emailOnValue: (String) -> Unit,
    password: String,
    passwordOnValue: (String) -> Unit,
    confirmPassword: String,
    confirmPasswordOnValue: (String) -> Unit,
    startOnClick: () -> Unit
    ){
    Column(
        modifier = modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ){
        CustomTextField(
            value = user,
            onValueChange = userOnValue,
            leadingIcon = Icons.Default.Person,
            trailingIcon = Icons.Default.Clear,
            label = "Usuário",
            placeholder = "Digite seu Usuário"
        )
        CustomTextField(
            value = email,
            onValueChange = emailOnValue,
            leadingIcon = Icons.Default.Email,
            trailingIcon = Icons.Default.Clear,
            label = "E-mail",
            placeholder = "Digite seu Email"
        )
        CustomTextField(
            value = password,
            onValueChange = passwordOnValue,
            leadingIcon = Icons.Default.Lock,
            isPasswordField = true,
            label = "Senha",
            placeholder = "Digite sua Senha"
        )
        CustomTextField(
            value = confirmPassword,
            onValueChange = confirmPasswordOnValue,
            leadingIcon = Icons.Default.Lock,
            isPasswordField = true,
            label = "Confirmar Senha",
            placeholder = "Digite sua Senha"
        )
        CustomButton(
            text = "Entrar",
            onClick = startOnClick,
            cornerRadius = 8,
            modifier = Modifier.padding(top = 5.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SignUpPreview() {
    SignUpScreen(
        onSignUpClick = {}
    )
}