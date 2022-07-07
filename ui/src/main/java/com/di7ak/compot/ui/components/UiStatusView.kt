package com.di7ak.compot.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.di7ak.compot.common.UiStatus

@Composable
fun UiStatusView(modifier: Modifier, status: UiStatus) {
    when (status) {
        is UiStatus.Loading -> {
            LoadingView(
                modifier = modifier,
                message = status.message
            )
        }
        is UiStatus.Error -> {
            ErrorView(
                modifier = modifier,
                message = status.message
            )
        }

        else -> {}
    }
}