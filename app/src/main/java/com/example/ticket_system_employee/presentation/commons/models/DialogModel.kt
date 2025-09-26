package com.example.ticket_system_employee.presentation.commons.models

import androidx.compose.ui.graphics.Color

data class DialogModel(
    val dismissRequest: () -> Unit = {},
    val closeAction: () -> Unit = {},
    val confirmAction: () -> Unit = {},
    val negativeAction: () -> Unit = {},
    val title: String = "",
    val message: String = "",
    val confirmTextButton: String = "",
    val negativeTextButton: String = "",
    val color: Color = Color.Unspecified,
)
