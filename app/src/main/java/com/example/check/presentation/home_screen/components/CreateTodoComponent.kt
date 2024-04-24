package com.example.check.presentation.home_screen.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.check.models.Todo
import com.example.check.presentation.MainViewModel
import java.text.SimpleDateFormat
import java.util.Date

@Composable
fun CreateTodoComponent(
    mainViewModel: MainViewModel
) {

    var lembretText by remember { mutableStateOf("Adicionar lembrete") }
    var showLembret: Boolean by remember { mutableStateOf(false) }

    val dateActual = Date()
    val formatador = SimpleDateFormat("dd/MM/yyyy")

    var task: String by remember { mutableStateOf("") }
    val dateFormated = formatador.format(dateActual)
    var dataLembret: String = ""

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .border(width = 1.dp, color = Color.Black, shape = RoundedCornerShape(8.dp)),
            value = task,
            onValueChange = { task = it },
            placeholder = {
                Text("Digite sua tarefa aqui")
            },
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                focusedContainerColor = MaterialTheme.colorScheme.tertiary,
                unfocusedContainerColor = MaterialTheme.colorScheme.tertiary
            )
        )
        Spacer(
            modifier = Modifier
                .height(16.dp)
        )
        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = { showLembret = !showLembret },
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.tertiary
                )
            ){
                Icon(
                    imageVector = Icons.Rounded.Notifications,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onTertiary
                )
                Spacer(
                    modifier = Modifier
                        .width(8.dp)
                )
                Text(
                    text = lembretText,
                    color = MaterialTheme.colorScheme.onTertiary
                )
            }

            Button(
                onClick = {
                    if (task.isNotEmpty()) {
                        val todo = Todo(0, task, false, dateFormated, dataLembret)

                        mainViewModel.createTodo(todo)

                        task = ""
                    }
                },
                shape = RoundedCornerShape(8.dp)
            ){
                Text(
                    text = "Confirmar"
                )
            }
        }

        when (showLembret) {
            true -> {
                lembretText = "Não lembrar"
                dataLembret = "- 1/1/2024"
                Spacer(
                    modifier = Modifier
                        .height(16.dp)
                )
                LembretTask(
                    onSendDateLembret = { day, month, year ->
                        dataLembret = " - $day/$month/$year"
                    }
                )
            }
            else -> {
                lembretText = "Adicionar lembrete"
                dataLembret = ""
            }
        }

        Spacer(
            modifier = Modifier
                .height(64.dp)
        )
    }
}

@Composable
fun LembretTask(
    onSendDateLembret: (day: Int, month: Int, year: Int) -> Unit
) {
    var dayDate by remember { mutableStateOf(1) }
    var monthDate by remember { mutableStateOf(1) }
    var yearDate by remember { mutableStateOf(2024) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        InterationDate(
            dayDate,
            {
                when {
                    (dayDate < 31) -> dayDate++
                    else -> dayDate = 1
                }
                onSendDateLembret(dayDate, monthDate, yearDate)
            },
            {
                when {
                    (dayDate > 1) -> dayDate--
                    else -> dayDate = 31
                }
                onSendDateLembret(dayDate, monthDate, yearDate)
            }
        )
        InterationDate(
            monthDate,
            {
                when {
                    (monthDate < 12) -> monthDate++
                    else -> monthDate = 1
                }
                onSendDateLembret(dayDate, monthDate, yearDate)
            },
            {
                when {
                    (monthDate > 1) -> monthDate--
                    else -> monthDate = 12
                }
                onSendDateLembret(dayDate, monthDate, yearDate)
            }
        )
        InterationDate(
            yearDate,
            {
                when {
                    (yearDate <= 2100) -> yearDate++
                    else -> yearDate = 2024
                }
                onSendDateLembret(dayDate, monthDate, yearDate)
            },
            {
                if (yearDate > 2024) {
                    yearDate--
                }
                onSendDateLembret(dayDate, monthDate, yearDate)
            }
        )
    }
}

@Composable
fun InterationDate(
    typeDate: Int,
    incrementDate: () -> Unit,
    decrementDate: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            shape = RoundedCornerShape(8.dp),
            onClick = incrementDate,
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.tertiary
            )
        ) {
            Icon(
                imageVector = Icons.Rounded.KeyboardArrowUp,
                contentDescription = null,
                tint = Color.Black
            )
        }
        Text(
            modifier = Modifier
                .padding(vertical = 8.dp),
            text = typeDate.toString()
        )
        Button(
            shape = RoundedCornerShape(8.dp),
            onClick = decrementDate,
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.tertiary
            )
        ) {
            Icon(
                imageVector = Icons.Rounded.KeyboardArrowDown,
                contentDescription = null,
                tint = Color.Black
            )
        }
    }
}