package com.example.firebaseaula.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ItemTask(
    modifier: Modifier = Modifier,
    taskName: String,
    isChecked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    onDeleteTask: () -> Unit = {},
    onEditTask: () -> Unit = {}
) {
    Card(
        modifier = modifier.clickable(onClick = onEditTask),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = isChecked,
                    onCheckedChange = onCheckedChange
                )
                Text(
                    text = taskName,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
            IconButton(onClick = onDeleteTask) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    tint = Color.Red,
                    contentDescription = "Excluir tarefa",
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ItemTaskPreview() {
    var isChecked by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ItemTask(
            taskName = "Nome da Tarefa",
            isChecked = isChecked,
            onCheckedChange = { isChecked = it },
            onDeleteTask = {},
            onEditTask = {}
        )
    }
}
