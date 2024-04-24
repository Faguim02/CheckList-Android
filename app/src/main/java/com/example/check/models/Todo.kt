package com.example.check.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Todo(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val task: String,
    val isCheck: Boolean,
    val dateIn: String,
    val dateOut: String
)
