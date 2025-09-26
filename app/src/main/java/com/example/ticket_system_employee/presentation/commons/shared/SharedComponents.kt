package com.example.ticket_system_employee.presentation.commons.shared

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.shape.RoundedCornerShape
import com.example.ticket_system_employee.R
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.ticket_system_employee.presentation.commons.models.TextFieldModel
import com.example.ticket_system_employee.presentation.commons.models.TrailingIconTypes
import com.example.ticket_system_employee.presentation.theme.Black
import com.example.ticket_system_employee.presentation.theme.Blue
import com.example.ticket_system_employee.presentation.theme.ComponentStyles
import com.example.ticket_system_employee.presentation.theme.Gray
import com.example.ticket_system_employee.presentation.theme.TextStyles

object SharedComponents {
    @Composable
    private fun IconDropdown(isFocused: Boolean) {
        Icon(
            imageVector = if (!isFocused) Icons.Default.KeyboardArrowDown else Icons.Default.KeyboardArrowUp,
            contentDescription = null
        )
    }

    @Composable
    private fun getTrailingIcon(
        typeIconTrailing: TrailingIconTypes,
        isFocused: Boolean
    ): @Composable (() -> Unit)? {
        if (typeIconTrailing == TrailingIconTypes.NONE) return null
        if (typeIconTrailing == TrailingIconTypes.DROPDOWN) return { IconDropdown(isFocused) }
        return null
    }

    private fun keyBoardOption(imeAction: ImeAction, keyboardType: KeyboardType): KeyboardOptions {
        return KeyboardOptions.Default.copy(
            imeAction = imeAction,
            keyboardType = keyboardType
        )
    }

    @Composable
    private fun IconTogglePassword(isFocused: Boolean, isPasswordVisible: Boolean, togglePasswordAction: () -> Unit) {
        val tintIcon = if (isFocused) Blue else Black
        val icon = if (!isPasswordVisible) R.drawable.ic_show_password else R.drawable.ic_hide_password
        IconButton(
            onClick = { togglePasswordAction.invoke() }
        ) {
            Icon(
                painter = painterResource(icon),
                tint = tintIcon,
                contentDescription = null
            )
        }
    }

    fun Modifier.getModifierWithOnFocusChanged(
        onFocusChanged: (Boolean) -> Unit
    ): Modifier = this.then(
        Modifier.onFocusChanged { onFocusChanged(it.isFocused) }
    )

    @Composable
    fun OutlinedDialogButton(modifier: Modifier, onClick: () -> Unit, text: String, enabled: Boolean = true, color: Color) {
        OutlinedButton(
            onClick = onClick,
            modifier = modifier,
            shape = RoundedCornerShape(8.dp),
            colors = GenericProperties.textOrOutlinedContainerButtonColors(color),
            border = BorderStroke(1.dp, if (enabled) color else Gray),
            enabled = enabled
        ) {
            Text(
                text,
                style = if (enabled) {
                    TextStyles.buttonDialogOutlinedStyle(color)
                } else TextStyles.buttonDialogOutlinedStyle(Gray))
        }

    }

    @Composable
    fun OutlinedTextFieldPassword(
        modifier: Modifier,
        onValueChange: (String) -> Unit = {},
        value: String,
        textFieldModel: TextFieldModel,
        isFocused: Boolean = false,
        isPasswordVisible: Boolean = false
    ) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            label = textFieldModel.label,
            singleLine = textFieldModel.singleLine,
            readOnly = textFieldModel.readOnly,
            modifier = modifier,
            suffix = textFieldModel.suffix,
            keyboardOptions = keyBoardOption(textFieldModel.keyboardImeAction, textFieldModel.keyboardType),
            colors = ComponentStyles.blueOutlinedTextFieldColors(),
            trailingIcon = { IconTogglePassword(isFocused, isPasswordVisible, textFieldModel.togglePasswordAction) },
            maxLines = textFieldModel.maxLines,
            visualTransformation = if (!isPasswordVisible) PasswordVisualTransformation() else VisualTransformation.None
        )
    }

    @Composable
    fun OutlinedTextFieldCustom(
        modifier: Modifier,
        onValueChange: (String) -> Unit = {},
        value: String,
        textFieldModel: TextFieldModel,
        isFocused: Boolean = false,
        visualTransformation: VisualTransformation = VisualTransformation.None
    ) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            label = textFieldModel.label,
            singleLine = textFieldModel.singleLine,
            readOnly = textFieldModel.readOnly,
            modifier = modifier,
            suffix = textFieldModel.suffix,
            keyboardOptions = keyBoardOption(textFieldModel.keyboardImeAction, textFieldModel.keyboardType),
            colors = ComponentStyles.blueOutlinedTextFieldColors(),
            trailingIcon = getTrailingIcon(
                textFieldModel.trailingIcon, isFocused
            ),
            maxLines = textFieldModel.maxLines,
            visualTransformation = visualTransformation
        )
    }

    @Composable
    fun BlueFilledButton(modifier: Modifier, enabled: Boolean = true, onClick: () -> Unit) {
        Button(
            colors = ComponentStyles.blueFilledButton, onClick = { onClick.invoke() },
            enabled = enabled, modifier = modifier
        ) {
            Text(
                text = stringResource(R.string.txt_button_login),
                style= TextStyles.buttonFilledStyle(),
            )
        }

    }

}