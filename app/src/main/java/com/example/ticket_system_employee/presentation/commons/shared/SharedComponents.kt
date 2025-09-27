package com.example.ticket_system_employee.presentation.commons.shared

import androidx.activity.ComponentActivity
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.ticket_system_employee.R
import com.example.ticket_system_employee.core.navigation.MyProfileRoute
import com.example.ticket_system_employee.core.navigation.NewRequestRoute
import com.example.ticket_system_employee.presentation.commons.models.*
import com.example.ticket_system_employee.presentation.theme.*

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
    private fun NavItemIcon(imageVector: ImageVector) {
        Icon(
            imageVector = imageVector,
            contentDescription = null
        )
    }

    @Composable
    private fun LabelItemIcon(text: Int) {
        Text(
            text = stringResource(text),
            style = TextStyles.bottomNavItemStyle()
        )
    }

    @Composable
    fun BottomNavigationBar(navController: NavController) {
        val context = LocalContext.current
        val activity = context as ComponentActivity
        val selectedItem = remember { NavItem.NONE }
        val destinations = listOf(
            NavItemOption(
                item = NavItem.NEW_REQUEST,
                action = { navController.navigate(NewRequestRoute) }
            ),
            NavItemOption(
                item = NavItem.LOGOUT,
                action = { activity.finish() }
            ),
            NavItemOption(
                item = NavItem.MY_PROFILE,
                action = { navController.navigate(MyProfileRoute) }
            )
        )
        Column(modifier = Modifier.fillMaxWidth()) {
            HorizontalDivider(
                modifier = Modifier.fillMaxWidth(), color = Gray, thickness = 1.dp
            )
            NavigationBar(
                modifier = Modifier.fillMaxWidth(),
                containerColor = White,
                contentColor = Black
            ) {
                destinations.forEach { navItemOption ->
                    val selected = navItemOption.item == selectedItem
                    NavigationBarItem(
                        selected = selected, icon = { NavItemIcon(navItemOption.item.imageVector!!) },
                        colors = GenericProperties.navItemColors, modifier = Modifier.weight(1f),
                        label =  { LabelItemIcon(navItemOption.item.title!!) },
                        onClick =  {
                            navItemOption.action.invoke()
                        }
                    )

                }
            }
        }

    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun ToolBar(toolBarModel: ToolBarModel) {
        TopAppBar(
            title = {
                Text(
                    text = toolBarModel.title, modifier = Modifier.fillMaxWidth(),
                    style = TextStyles.titleStyle(Black)
                )
            }, modifier = Modifier.fillMaxWidth(),
            navigationIcon = {
                if (toolBarModel.showBackIcon) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Default.ArrowBack,
                        contentDescription = null, tint = Black,
                        modifier = Modifier.clickable(onClick = { toolBarModel.backAction.invoke() })
                    )
                }
            }, colors = GenericProperties.whiteTopAppBarColors
        )
    }

    @Composable
    fun LoadingSplash(message: String = "") {
        Box(modifier = Modifier.fillMaxSize().background(Black.copy(alpha = 0.5f))) {
            Column(modifier = Modifier.align(Alignment.Center)) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.CenterHorizontally).padding(bottom = 12.dp),
                    color = White
                )
                if (message.isNotEmpty()) {
                    Text(
                        text = message,
                        style = TextStyles.loadingTextStyle(White),
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }

    }

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
    fun BlueFilledButton(modifier: Modifier, enabled: Boolean = true, onClick: () -> Unit, text: Int) {
        Button(
            colors = ComponentStyles.blueFilledButton, onClick = { onClick.invoke() },
            enabled = enabled, modifier = modifier
        ) {
            Text(
                text = stringResource(text),
                style= TextStyles.buttonFilledStyle(),
            )
        }

    }

    @Composable
    fun Subtitle(subtitle: String, modifier: Modifier) {
        Text(
            text = subtitle, modifier = modifier,
            style = TextStyles.subtitleSectionStyle(Black)
        )
    }

    @Composable
    fun InformationItem(subtitle: String, information: String, modifier: Modifier) {
        Column(modifier = modifier) {
            Text(
                text = subtitle, modifier = Modifier.fillMaxWidth(),
                style = TextStyles.subtitleInformationStyle(Black)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = information, modifier = Modifier.fillMaxWidth(),
                style = TextStyles.informationItemStyle(Black)
            )
        }

    }

}