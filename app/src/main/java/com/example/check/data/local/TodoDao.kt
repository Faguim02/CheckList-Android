package com.example.check.data.local

import androidx.room.*
import com.example.check.models.Todo
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createTodo(todo: Todo)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateTodo(todo: Todo)

    @Delete
    suspend fun deleteTodo(todo: Todo)

    @Query("SELECT * FROM Todo WHERE id = :id")
    fun findOneTodo(id: Int): Todo

    @Query("SELECT * FROM Todo")
    fun findAllTodo(): Flow<List<Todo>>

}