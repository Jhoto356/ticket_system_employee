package com.example.ticket_system_employee.presentation.commons.models

data class NavItemOption(
    val item: NavItem,
    val action: () -> Unit
)
