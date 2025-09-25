package com.example.ticket_system_employee.presentation.commons.models

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType

data class TextFieldModel(
    val label: @Composable () -> Unit,
    val readOnly: Boolean = false,
    val singleLine: Boolean = true,
    val trailingIcon: TrailingIconTypes = TrailingIconTypes.NONE,
    val keyboardImeAction: ImeAction = ImeAction.Next,
    val keyboardType: KeyboardType = KeyboardType.Text,
    val suffix: @Composable () -> Unit = {},
    val maxLines: Int = 1,
    val togglePasswordAction: () -> Unit = {}
)