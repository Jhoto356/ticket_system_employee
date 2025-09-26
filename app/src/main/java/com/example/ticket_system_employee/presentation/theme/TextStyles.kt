package com.example.ticket_system_employee.presentation.theme

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

object TextStyles {
    fun titleStyle(color: Color) = TextStyle(
        color = color,
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center
    )

    fun contentStyle(color: Color) = TextStyle(
        color = color,
        fontSize = 20.sp,
        fontWeight = FontWeight.Normal,
        textAlign = TextAlign.Start
    )

    fun loadingTextStyle(color: Color) = TextStyle(
        color = color,
        fontSize = 18.sp,
        fontWeight = FontWeight.SemiBold,
        textAlign = TextAlign.Center
    )

    fun buttonFilledStyle() = TextStyle(
        color = White,
        fontSize = 18.sp,
        fontWeight = FontWeight.Normal,
        textAlign = TextAlign.Center
    )

    fun buttonDialogOutlinedStyle(color: Color) = TextStyle(
        color = color,
        fontSize = 18.sp,
        fontWeight = FontWeight.Normal,
        textAlign = TextAlign.Center
    )

    fun versionStyle(color: Color) = TextStyle(
        color = color,
        fontSize = 16.sp,
        fontWeight = FontWeight.Normal,
        textAlign = TextAlign.Center
    )

}