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
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.firebaseaula.repository.UsersRepository
import com.example.firebaseaula.ui.components.CustomButton
import com.example.firebaseaula.ui.components.CustomTextField
import com.example.firebaseaula.ui.viewModel.SignInViewModel

@Composable
fun SignInScreen(
    viewModel: SignInViewModel = hiltViewModel(),
    onSignInClick: () -> Unit,
    onSignUpClick: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    SignInContent(
        email = uiState.email,
        emailOnValue = uiState.onEmailChange,
        password = uiState.password,
        passwordOnValue = uiState.onPasswordChange,
        onSignInClick = onSignInClick,
        onSignUpClick = onSignUpClick
    )
}

@Composable
fun SignInContent(
    modifier: Modifier = Modifier,
    email: String,
    emailOnValue: (String) -> Unit,
    password: String,
    passwordOnValue: (String) -> Unit,
    onSignInClick: () -> Unit,
    onSignUpClick: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(23.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SignInForm(
            email = email,
            emailOnValue = emailOnValue,
            password = password,
            passwordOnValue = passwordOnValue,
            onSignInClick = onSignInClick,
            onSignUpClick = onSignUpClick
        )

    }
}

@Composable
fun SignInForm(
    modifier: Modifier = Modifier,
    email: String,
    emailOnValue: (String) -> Unit,
    password: String,
    passwordOnValue: (String) -> Unit,
    onSignInClick: () -> Unit,
    onSignUpClick: () -> Unit
){
    Column(
        modifier = modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ){
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
        CustomButton(
            text = "Entrar",
            onClick = onSignInClick,
            cornerRadius = 8,
            modifier = Modifier.padding(top = 5.dp)
        )
        TextButton(
            onClick = onSignUpClick,
            Modifier
                .fillMaxWidth(0.8f)
                .padding(8.dp)
        ) {
            Text(text = "Cadastrar")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SignIpPreview() {
    val fakeViewModel = SignInViewModel(repository = UsersRepository())
    SignInScreen(
        viewModel = fakeViewModel,
        onSignInClick = {},
        onSignUpClick = {}
    )
}