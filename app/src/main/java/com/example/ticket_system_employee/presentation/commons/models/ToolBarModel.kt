package com.example.ticket_system_employee.presentation.commons.models

data class ToolBarModel(
    val title: String,
    val backAction: () -> Unit = {},
    val showBackIcon: Boolean = false
)
