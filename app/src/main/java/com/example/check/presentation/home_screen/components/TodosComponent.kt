package com.example.check.presentation.home_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountBox
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.check.models.Todo
import com.example.check.presentation.MainViewModel

@Composable
fun TodosComponent(
    mainViewModel: MainViewModel
) {

    val todos by mainViewModel.findAllTodo.collectAsStateWithLifecycle(initialValue = emptyList())

    LazyColumn(

    ) {
        items(items = todos) {todo ->
            when {
                todo.isCheck -> CardTodo(todo, MaterialTheme.colorScheme.secondary, Color.Black, mainViewModel)
                else -> CardTodo(todo, MaterialTheme.colorScheme.tertiary, Color.Transparent, mainViewModel)
            }
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardTodo(todo: Todo, colorCard: Color, colorIcon: Color, mainViewModel: MainViewModel) {

    Card(
        modifier = Modifier
            .padding(horizontal = 8.dp),
        onClick = {
            mainViewModel.onCheck(todo = todo)
        },
        colors = CardDefaults.cardColors(
            containerColor = colorCard,
        )
    ){
        Row {
            IconButton(
                onClick = {}
            ){
                Icon(
                    imageVector = Icons.Rounded.Check,
                    contentDescription = null,
                    tint = colorIcon
                )
            }

            Column {
                Text(
                    text = todo.task,
                    fontSize = 16.sp,
                    fontFamily = FontFamily.Monospace,
                    fontWeight = FontWeight.SemiBold,
                )
                Text(
                    text = "${todo.dateIn} ${todo.dateOut}",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                )
            }
            Spacer(
                modifier = Modifier.weight(1f)
            )

            IconButton(
                onClick = {
                    mainViewModel.deleteTodo(todo)
                }
            ){
                Icon(
                    imageVector = Icons.Rounded.Delete,
                    contentDescription = null
                )
            }
        }
    }
}