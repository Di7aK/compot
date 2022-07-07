package com.di7ak.compot.features.edit.views

import androidx.compose.foundation.layout.*
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.di7ak.compot.domain.models.TodoModel

@Composable
fun EditScreenView(modifier: Modifier, todo: TodoModel, onChange: (todo: TodoModel) -> Unit) {
    Column(
        modifier = modifier.padding(16.dp)
    ) {
        var name by remember { mutableStateOf(todo.name) }
        var content by remember { mutableStateOf(todo.content) }

        OutlinedTextField(
            value = name,
            onValueChange = {
                name = it
                onChange(todo.copy(name = it))
            },
            label = { Text("Name") },
            modifier = Modifier
                .fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = content,
            onValueChange = {
                content = it
                onChange(todo.copy(content = it))
            },
            label = { Text("Content") },
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        )
    }
}