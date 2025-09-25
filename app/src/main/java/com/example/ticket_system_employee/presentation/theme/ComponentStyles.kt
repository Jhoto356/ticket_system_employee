package com.example.ticket_system_employee.presentation.theme

import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.TextFieldColors

object ComponentStyles {
    private val outlinedTextFieldColors = TextSelectionColors(
        handleColor = Blue,
        backgroundColor = Blue.copy(alpha = 0.4f)
    )
    private val outlinedTextFieldColorsError = TextSelectionColors(
        handleColor = ErrorColor,
        backgroundColor = ErrorColor.copy(alpha = 0.4f)
    )

    val blueFilledButton = ButtonColors(
        containerColor = Blue,
        contentColor = White,
        disabledContainerColor = Gray,
        disabledContentColor = White
    )

    fun blueOutlinedTextFieldColors(isError: Boolean = false) = TextFieldColors(
        focusedTextColor = Black,
        unfocusedTextColor = Black,
        disabledTextColor = Gray,
        errorTextColor = Black,
        focusedContainerColor = Transparent,
        unfocusedContainerColor = Transparent,
        disabledContainerColor = Transparent,
        errorContainerColor = Transparent,
        cursorColor = Blue,
        errorCursorColor = if (isError) ErrorColor else Blue,
        textSelectionColors = if (isError) outlinedTextFieldColorsError else outlinedTextFieldColors,
        focusedIndicatorColor = Blue,
        unfocusedIndicatorColor = Black,
        disabledIndicatorColor = Gray,
        errorIndicatorColor = if (isError) ErrorColor else Blue,
        focusedLeadingIconColor = Blue,
        unfocusedLeadingIconColor = Black,
        disabledLeadingIconColor = Gray,
        errorLeadingIconColor = if (isError) ErrorColor else Blue,
        focusedTrailingIconColor = Blue,
        unfocusedTrailingIconColor = Black,
        disabledTrailingIconColor = Gray,
        errorTrailingIconColor = if (isError) ErrorColor else Blue,
        focusedLabelColor = Blue,
        unfocusedLabelColor = Black,
        disabledLabelColor = Gray,
        errorLabelColor = if (isError) ErrorColor else Blue,
        focusedPlaceholderColor = Blue,
        unfocusedPlaceholderColor = Gray,
        disabledPlaceholderColor = Gray,
        errorPlaceholderColor = Gray,
        focusedSupportingTextColor = Blue,
        unfocusedSupportingTextColor = Black,
        disabledSupportingTextColor = Gray,
        errorSupportingTextColor = if (isError) ErrorColor else Blue,
        focusedPrefixColor = Blue,
        unfocusedPrefixColor = Black,
        disabledPrefixColor = Gray,
        errorPrefixColor = ErrorColor,
        focusedSuffixColor = Blue,
        unfocusedSuffixColor = Black,
        disabledSuffixColor = Gray,
        errorSuffixColor = ErrorColor
    )
}