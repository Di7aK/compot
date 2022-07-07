package com.di7ak.compot.features.details.model

import com.di7ak.compot.common.UiAction

sealed class DetailsUiAction: UiAction {
    object Back: DetailsUiAction()
}