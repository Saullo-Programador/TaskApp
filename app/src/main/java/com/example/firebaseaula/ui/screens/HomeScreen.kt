package com.example.firebaseaula.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.firebaseaula.ui.components.FloatingButton
import com.example.firebaseaula.ui.components.ItemTask
import com.example.firebaseaula.ui.components.LoadingScreen
import com.example.firebaseaula.ui.components.ScreenComponents
import com.example.firebaseaula.ui.theme.FirebaseAulaTheme
import com.example.firebaseaula.ui.viewModel.HomeViewModel

@Composable
fun HomeScreen(
    onSairClick: () -> Unit,
    onAddClick: () -> Unit,
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    val userName by homeViewModel.userName.collectAsState()
    HomeContent(
        onClickSignOut = onSairClick,
        onAddClick = onAddClick,
        userName = userName
    )
}

@Composable
fun HomeContent(
    onClickSignOut: () -> Unit,
    onAddClick: () -> Unit,
    userName: String?,
) {

    Scaffold(
        floatingActionButton = {
            FloatingButton(
                onClick = onAddClick,
                icon = Icons.Default.Add,
                contentDescription = "Adicionar uma nova senha ao gerenciador"
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding).fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HomeTopBar(onClickSignOut = onClickSignOut)
            Text(
                text = userName ?: "Carregando...",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            TaskListScreen()
            //TaskListFake()
        }
    }
}


@Composable
fun TaskListScreen(viewModel: HomeViewModel = hiltViewModel()) {

    var isLoading by remember { mutableStateOf(true) }
    val isRandomOrder by viewModel.isRandomOrder.collectAsState()
    val tasks by viewModel.tasks.collectAsState(initial = emptyList())
    val taskBeingEdited by viewModel.taskBeingEdited.collectAsState()

    if (tasks.isEmpty()) {
        isLoading = false
    }

    if (isLoading) {
        LoadingScreen()
    } else {
        if (taskBeingEdited != null) {
            UpdateTaskScreen(
                onClickCancelar = {viewModel.cancelEdit()},
                onClickSalvar = {newName ->
                    taskBeingEdited?.let{
                        viewModel.updateTask(it.id, newName){
                            viewModel.cancelEdit()
                        }
                    }
                },
                isLoading = false
            )
        }else{
            Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
                Button(
                    onClick = { viewModel.toggleOrder() },
                    modifier = Modifier.padding(bottom = 5.dp)
                ) {
                    Text(if (isRandomOrder) "Modo Normal" else "Modo Aleatório")
                }

                if (tasks.isEmpty()) {
                    ScreenComponents(text = "Nenhum Task Criada")
                } else {
                    LazyColumn {
                        items(tasks.size) { index ->
                            val task = tasks[index]
                            ItemTask(
                                modifier = Modifier.padding(vertical = 5.dp),
                                taskName = task.name,
                                onDeleteTask = { viewModel.deleteTask(taskId = task.id) },
                                onEditTask = { viewModel.editTask(task) },
                                onCheckedChange = { isChecked ->
                                    viewModel.checkTask(taskId = task.id, isChecked = isChecked )
                                },
                                isChecked = task.isChecked,
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun HomeTopBar(
    onClickSignOut: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .background(MaterialTheme.colorScheme.primary)
            .clip(RoundedCornerShape(bottomStart = 26.dp, bottomEnd = 26.dp))
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = "Gerenciador de Senhas",
            style = MaterialTheme.typography.titleLarge,
            color = Color.White
        )
        IconButton(onClick = onClickSignOut) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ExitToApp,
                tint = Color.White,
                contentDescription = "Sair do aplicativo",
            )
        }
    }
}

/*
@Composable
fun TaskListFake(viewModel: FakeHomeViewModel = FakeHomeViewModel()) {
    val isLoading = false
    val isRandomOrder by viewModel.isRandomOrder.collectAsState()
    val tasks by viewModel.tasks.collectAsState()
    val taskBeingEdited by viewModel.taskBeingEdited.collectAsState()

    if (isLoading) {
        LoadingScreen()
    } else {
        if (taskBeingEdited != null) {
            UpdateTaskScreen(
                onClickCancelar = { viewModel.cancelEdit() },
                onClickSalvar = { newName ->
                    taskBeingEdited?.let {
                        viewModel.checkTask(it.id, false) // Simula update
                        viewModel.cancelEdit()
                    }
                },
                isLoading = false
            )
        } else {
            Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
                Button(
                    onClick = { viewModel.toggleOrder() },
                    modifier = Modifier.padding(bottom = 5.dp)
                ) {
                    Text(if (isRandomOrder) "Modo Normal" else "Modo Aleatório")
                }

                if (tasks.isEmpty()) {
                    ScreenComponents(text = "Nenhuma Task Criada")
                } else {
                    LazyColumn {
                        items(tasks.size) { index ->
                            val task = tasks[index]
                            ItemTask(
                                modifier = Modifier.padding(vertical = 5.dp),
                                taskName = task.name,
                                onDeleteTask = { viewModel.deleteTask(taskId = task.id) },
                                onEditTask = { viewModel.editTask(task) },
                                onCheckedChange = { isChecked ->
                                    viewModel.checkTask(taskId = task.id, isChecked = isChecked)
                                },
                                isChecked = task.isChecked,
                            )
                        }
                    }
                }
            }
        }
    }
}

 */


@Preview(showBackground = true)
@Composable
fun HomePreview() {
    FirebaseAulaTheme {
        HomeContent(
            onClickSignOut = {},
            onAddClick = {},
            userName = ""
        )
    }
}