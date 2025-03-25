package com.example.firebaseaula.ui.screens

import androidx.compose.material.icons.filled.Done
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.firebaseaula.ui.components.CustomButton
import com.example.firebaseaula.ui.components.CustomTextField
import com.example.firebaseaula.ui.components.LoadingScreen
import com.example.firebaseaula.ui.components.TopBarComponents

@Composable
fun UpdateTaskScreen(
    onClickSalvar: (String) -> Unit,
    onClickCancelar: () -> Unit,
    isLoading: Boolean
) {
    var taskName by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        TopBarComponents(title = "Update Task")

        if (isLoading) {
            LoadingScreen()
        } else {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ){
                CustomTextField(
                    value = taskName,
                    onValueChange = { taskName = it },
                    label = "Task Name",
                    placeholder = "Digite aqui...",
                    trailingIcon = Icons.Default.Clear,
                    onTrailingIconClick = { taskName = "" },
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    CustomButton(
                        text = "Salvar",
                        onClick = { onClickSalvar(taskName) },
                        icon = Icons.Default.Done,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.primary
                        ),
                        modifier = Modifier
                            .weight(1f)
                            .height(55.dp)
                    )
                    CustomButton(
                        text = "Cancelar",
                        onClick = { onClickCancelar() },
                        icon = Icons.Default.Clear,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.error
                        ),
                        modifier = Modifier
                            .weight(1f)
                            .height(55.dp)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UpdateTaskScreenPreview() {
    UpdateTaskScreen(
        onClickSalvar = {},
        onClickCancelar = {},
        isLoading = false
    )
}