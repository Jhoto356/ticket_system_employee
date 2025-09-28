package com.example.ticket_system_employee.presentation.commons.models

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

data class SnackBarModel(
    val text: String,
    val dismissAction: () -> Unit,
    val closeAction: () -> Unit = {},
    val duration: Long,
    val closeButton: Boolean = false,
    val modifier: Modifier,
    val color: Color
)
