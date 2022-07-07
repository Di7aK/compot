package com.di7ak.compot.features.edit.model

import com.di7ak.compot.common.UiAction

sealed class EditUiAction: UiAction {
    object Back: EditUiAction()
}