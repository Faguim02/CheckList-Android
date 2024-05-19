package com.example.check.presentation.home_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.check.R
import com.example.check.presentation.MainViewModel
import com.example.check.presentation.home_screen.components.CreateTodoComponent
import com.example.check.presentation.home_screen.components.EmptyComponent
import com.example.check.presentation.home_screen.components.TodosComponent
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    mainViewModel: MainViewModel
) {

    val sheetState = rememberModalBottomSheetState()
    var showBottomSheet by remember { mutableStateOf(false) }

    val todos by mainViewModel.findAllTodo.collectAsStateWithLifecycle(initialValue = emptyList())

    Scaffold(
        topBar = {
            TopBarApp()
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    showBottomSheet = true
                },
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Icon(
                    imageVector = Icons.Rounded.Add,
                    contentDescription = null
                )
            }
        }
    ) {paddingValues ->

        Column(
            modifier = Modifier.padding(paddingValues)
        ) {
            if (showBottomSheet) {
                ModalBottomSheet(
                    onDismissRequest = { showBottomSheet = false },
                    sheetState = sheetState,
                    containerColor = MaterialTheme.colorScheme.background
                ) {

                    CreateTodoComponent(mainViewModel)

                }
            }

            when {
                (todos.isEmpty()) -> EmptyComponent()
                else -> TodosComponent(mainViewModel)
            }

        }
    }
}

@Composable
fun TopBarApp() {
    Column {
        Row(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.primary, RoundedCornerShape(bottomEnd = 16.dp))
                .height(160.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "Check List",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Monospace,
                    color = Color.White
                )
                Text(
                    text = "Suas tarefas aqui.",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = FontFamily.Monospace,
                    color = Color.White
                )
            }
            Image(
                modifier = Modifier.size(80.dp),
                painter = painterResource(R.drawable.ilustration),
                contentDescription = null
            )
        }
        Box {
            Box(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.primary)
                    .fillMaxWidth()
                    .height(16.dp)
            )
            Box(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.background, RoundedCornerShape(topStart = 16.dp))
                    .fillMaxWidth()
                    .height(16.dp)
            )
        }
    }
}