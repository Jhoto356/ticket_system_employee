package com.example.ticket_system_employee.presentation.commons.models

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.ticket_system_employee.R

enum class NavItem(val imageVector: ImageVector?, val title: Int?) {
    NEW_REQUEST(Icons.Default.AddCircle, R.string.txt_go_new_request),
    LOGOUT(Icons.AutoMirrored.Default.ExitToApp, R.string.txt_close_session),
    MY_PROFILE(Icons.Default.AccountCircle, R.string.txt_go_my_profile),
    NONE(null, null)
}