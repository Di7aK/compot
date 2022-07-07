package com.di7ak.compot.features.main.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.di7ak.compot.domain.models.TodoModel
import com.di7ak.compot.ui.components.TodoItemView

@Composable
fun MainScreenView(modifier: Modifier, todos: List<TodoModel>, onTodoClick: (todo: TodoModel) -> Unit, onTodoEditClick: (todo: TodoModel) -> Unit) {
    Column(modifier = modifier) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(todos) { todo ->
                TodoItemView(
                    modifier = Modifier.fillMaxWidth(),
                    name = todo.name,
                    onClick = { onTodoClick(todo) },
                    onEditClick = { onTodoEditClick(todo) }
                )
            }
        }
    }
}