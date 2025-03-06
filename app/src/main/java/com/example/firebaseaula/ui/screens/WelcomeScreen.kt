package com.example.firebaseaula.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.firebaseaula.R
import com.example.firebaseaula.ui.components.CustomButton
import com.example.firebaseaula.ui.navigation.AppGraph
import com.example.firebaseaula.ui.theme.FirebaseAulaTheme
import kotlinx.coroutines.delay

@Composable
fun WelcomeScreen(navController: NavController) {
    val img = painterResource(id = R.drawable.transferir)
    var start by remember { mutableStateOf(false) }
    WelcomeContent(
        painter = img,
        onClick = { start = true },
        isLoading = start
    )

    LaunchedEffect(start) {
        if(start) {
            delay(1500)
            start = false
            navController.navigate(AppGraph.auth.SIGN_IN){
                popUpTo(AppGraph.welcome.INITIAL) { inclusive = true }
            }
        }
    }
}

@Composable
fun WelcomeContent(
    modifier: Modifier = Modifier,
    painter: Painter,
    onClick: () -> Unit,
    isLoading: Boolean = false
) {
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        Image(
            painter = painter,
            contentDescription = "Background",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 25.dp, horizontal = 20.dp)
                .align(Alignment.BottomCenter)
        ) {
            CustomButton(
                text = "Começar",
                onClick = onClick,
                isLoading = isLoading,
                colors = ButtonDefaults.buttonColors(Color.Magenta),
                cornerRadius = 10
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FirstPreview() {
    FirebaseAulaTheme {
        WelcomeContent(
            painter = painterResource(id = R.drawable.transferir),
            onClick = {},
            isLoading = false
        )
    }
}