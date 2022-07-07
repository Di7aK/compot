package com.di7ak.compot.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun TodoItemView(modifier: Modifier, name: String, onClick: () -> Unit, onEditClick: () -> Unit) {
    Row(
        modifier = modifier
            .clickable { onClick() }
            .padding(paddingValues = PaddingValues(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 8.dp)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = name,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.weight(1f)
        )

        IconButton(onClick = { onEditClick() }) {
            Icon(imageVector = Icons.Outlined.Edit, contentDescription = "Edit")
        }
    }
}

@Preview
@Composable
private fun TodoItemView_Preview() {
    TodoItemView(modifier = Modifier, name =  "name", onClick = {}, onEditClick = {})
}