package com.di7ak.compot.features.details.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.di7ak.compot.domain.models.TodoModel

@Composable
fun DetailsScreenView(modifier: Modifier, todo: TodoModel) {
    Column(
        modifier = modifier.padding(16.dp)
    ) {
        Text(
            text = todo.content,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.verticalScroll(rememberScrollState())
                .fillMaxWidth()
        )
    }
}