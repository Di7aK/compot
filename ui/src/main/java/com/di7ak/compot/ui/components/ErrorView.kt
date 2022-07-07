package com.di7ak.compot.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview


@Composable
fun ErrorView(
    modifier: Modifier = Modifier,
    message: String = ""
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = message,
            style = MaterialTheme.typography.labelMedium,
            modifier = Modifier
                .wrapContentSize()
                .align(Alignment.CenterHorizontally)
        )
    }
}

@Preview
@Composable
private fun ErrorView_Preview() {
    ErrorView(modifier = Modifier.wrapContentSize(Alignment.Center), message = "Error")
}