package com.example.check.di

import android.content.Context
import androidx.room.Room
import com.example.check.data.local.TodoDao
import com.example.check.data.local.TodoDataBase
import com.example.check.data.repository.TodoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDataBase(@ApplicationContext context: Context): TodoDataBase {
        return Room.databaseBuilder(
            context,
            TodoDataBase::class.java,
            "local_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideTodoDao(db: TodoDataBase): TodoDao = db.todoDao()

    @Provides
    @Singleton
    fun provideTodoRepository(dao: TodoDao): TodoRepository = TodoRepository(dao = dao)
}