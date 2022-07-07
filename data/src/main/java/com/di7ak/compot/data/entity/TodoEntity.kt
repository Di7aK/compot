package com.di7ak.compot.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.di7ak.compot.domain.models.TodoModel

@Entity(tableName = "todos")
internal data class TodoEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String = "",
    val content: String = ""
)

internal fun TodoEntity.toTodoModel() = TodoModel(
    id = this.id,
    name = this.name,
    content = this.content
)

internal fun TodoModel.toTodoEntity() = TodoEntity(
    id = this.id,
    name = this.name,
    content = this.content
)