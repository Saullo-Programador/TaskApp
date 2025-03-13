package com.example.firebaseaula.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.firebaseaula.ui.theme.FirebaseAulaTheme

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onSairClick: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            text = "Home Screen",
            fontSize = 19.sp,
            modifier = modifier
                .padding(bottom = 15.dp)
        )
        TextButton(
            onClick = onSairClick
        ) {
            Text(
                text = "Sair da conta",
                fontSize = 16.sp,
                color = Color.Red
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomePreview(){
    FirebaseAulaTheme {
        HomeScreen(
            onSairClick = {}
        )
    }
}