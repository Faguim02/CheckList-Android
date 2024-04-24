package com.example.check.data.repository

import com.example.check.data.local.TodoDao
import com.example.check.models.Todo
import kotlinx.coroutines.flow.Flow

class TodoRepository(
    private val dao: TodoDao
) {
    suspend fun createTodo(todo: Todo) = dao.createTodo(todo)

    suspend fun updateTodo(todo: Todo) = dao.updateTodo(todo)

    suspend fun deleteTodo(todo: Todo) = dao.deleteTodo(todo)

    fun findAllTodo(): Flow<List<Todo>> = dao.findAllTodo()
}