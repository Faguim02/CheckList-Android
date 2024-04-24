package com.example.check.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.check.data.repository.TodoRepository
import com.example.check.models.Todo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: TodoRepository
) : ViewModel() {

    var todo: Todo by mutableStateOf(Todo(0, "", false, "", ""))
        private set

    val findAllTodo: Flow<List<Todo>> = repository.findAllTodo()

    fun createTodo(todo: Todo) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.createTodo(todo)
        }
    }

    fun deleteTodo(todo: Todo) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteTodo(todo)
        }
    }

    fun onCheck(todo: Todo) {
        viewModelScope.launch(Dispatchers.IO) {
            val newTodo = todo.copy(isCheck = !todo.isCheck)
            repository.updateTodo(newTodo)
        }
    }
}