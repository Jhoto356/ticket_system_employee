package com.example.ticket_system_employee.presentation.commons.shared

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.CardColors
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import com.example.ticket_system_employee.presentation.theme.Black
import com.example.ticket_system_employee.presentation.theme.Gray
import com.example.ticket_system_employee.presentation.theme.White

object GenericProperties {
    fun borderStrokeDynamic(color: Color) = BorderStroke(1.dp, color)
    val roundenShapeDefault = RoundedCornerShape(16.dp)
    val noCancelableProperty = DialogProperties(
        dismissOnBackPress = false,
        dismissOnClickOutside = false
    )
    val cancelableProperty = DialogProperties(
        dismissOnBackPress = true,
        dismissOnClickOutside = true
    )
    val whiteCardColor = CardColors(
        containerColor = White,
        contentColor = Black,
        disabledContainerColor = White,
        disabledContentColor = Black
    )
    fun textOrOutlinedContainerButtonColors(color: Color) = ButtonColors(
        containerColor = White,
        contentColor = color,
        disabledContainerColor = White,
        disabledContentColor = Gray,
    )
}