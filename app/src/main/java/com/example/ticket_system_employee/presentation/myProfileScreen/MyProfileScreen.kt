package com.example.ticket_system_employee.presentation.myProfileScreen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import com.example.ticket_system_employee.R
import com.example.ticket_system_employee.core.navigation.LoginRoute
import com.example.ticket_system_employee.presentation.commons.models.*
import com.example.ticket_system_employee.presentation.commons.shared.DialoguesAndSnackBars
import com.example.ticket_system_employee.presentation.commons.shared.GenericProperties
import com.example.ticket_system_employee.presentation.commons.shared.SharedComponents
import com.example.ticket_system_employee.presentation.commons.shared.SharedComponents.getModifierWithOnFocusChanged
import com.example.ticket_system_employee.presentation.theme.Blue
import com.example.ticket_system_employee.presentation.theme.ErrorColor
import com.example.ticket_system_employee.presentation.theme.White
import org.koin.androidx.compose.koinViewModel

@Composable
fun MyProfileView(navController: NavController) {
    val myProfileVM: MyProfileVM = koinViewModel<MyProfileVM>()
    val uiSate = myProfileVM.myProfileUIStateValue.collectAsState().value
    BackHandler { navController.popBackStack() }

    val toolBarModel = ToolBarModel(
        title = stringResource(R.string.txt_my_profile),
        backAction =  { navController.popBackStack() }, showBackIcon = true
    )
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier.fillMaxSize().background(White).systemBarsPadding().imePadding(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SharedComponents.ToolBar(toolBarModel)
        Column(
            modifier = Modifier.fillMaxWidth(0.85f).weight(1f).padding(vertical = 16.dp)
                .verticalScroll(scrollState)
        ) {
            CompanyInformation()
            EmployeeInformation()
        }
        SharedComponents.BlueFilledButton(
            modifier = Modifier.fillMaxWidth(0.9f).padding(bottom = 16.dp),
            text = R.string.txt_button_change_password, enabled = uiSate.enabledButtonChangePassword,
            onClick = { myProfileVM.setShowDialogChangePassword(true) }
        )
    }
    Dialogs(navController)
    if (uiSate.isLoading) { SharedComponents.LoadingSplash(uiSate.loadingMessage) }


}

@Composable
fun CompanyInformation() {
    val myProfileVM: MyProfileVM = koinViewModel<MyProfileVM>()
    val uiSate = myProfileVM.myProfileUIStateValue.collectAsState().value
    val employeeWithCompany = uiSate.employeeWithCompany
    Column(
        modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SharedComponents.Subtitle(
            subtitle = stringResource(R.string.txt_company_information),
            modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp)
        )
        SharedComponents.InformationItem(
            modifier = Modifier.fillMaxWidth().padding(bottom = 6.dp),
            subtitle = stringResource(R.string.txt_name), information = employeeWithCompany.companyName
        )
        SharedComponents.InformationItem(
            modifier = Modifier.fillMaxWidth(),
            subtitle = stringResource(R.string.txt_nit), information = employeeWithCompany.nit
        )
    }
}

@Composable
fun Dialogs(navController: NavController) {
    val myProfileVM: MyProfileVM = koinViewModel<MyProfileVM>()
    val uiSate = myProfileVM.myProfileUIStateValue.collectAsState().value
    if (uiSate.error) {
        myProfileVM.setShowDialogChangePassword(false)
        myProfileVM.setIsLoading(false)
        val dialogModel = DialogModel(
            confirmAction = {
                myProfileVM.setError(false)
                navController.popBackStack()
            }, title = stringResource(R.string.txt_title_error_getting_information),
            color = ErrorColor, message = uiSate.message
        )
        DialoguesAndSnackBars.GenericDialog(dialogModel)
    }
    if (uiSate.showDialogChangePassword) {
        myProfileVM.setIsLoading(true, stringResource(R.string.txt_validating_and_updating_password))
        ChangePasswordDialog()
    }
    if (uiSate.changePasswordSuccess) {
        myProfileVM.setIsLoading(false)
        myProfileVM.setShowDialogChangePassword(false)
        ChangePasswordSuccessDialog(navController)
    }

}

@Composable
fun ChangePasswordDialog() {
    val color = Blue
    val myProfileVM: MyProfileVM = koinViewModel<MyProfileVM>()
    val uiSate = myProfileVM.myProfileUIStateValue.collectAsState().value
    val shape = GenericProperties.roundenShapeDefault
    val borderStroke = GenericProperties.borderStrokeDynamic(color)
    val cardColors = GenericProperties.whiteCardColor

    val toolBarModel = ToolBarModel(title = stringResource(R.string.txt_title_change_password))
    val scrollState = rememberScrollState()
    Dialog(
        onDismissRequest = {
            myProfileVM.setShowDialogChangePassword(false)
            myProfileVM.setIsLoading(false)
        }, properties = GenericProperties.cancelableProperty
    ) {
        Card(
            shape = shape, border = borderStroke, colors = cardColors,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.fillMaxWidth().padding(16.dp).verticalScroll(scrollState)) {
                SharedComponents.ToolBar(toolBarModel)
                if (uiSate.changePasswordError) {
                    myProfileVM.setIsLoading(false)
                    val snackBarModel = SnackBarModel(
                        text = uiSate.message, color = ErrorColor,
                        dismissAction = { myProfileVM.setChangePasswordError(false) },
                        duration = 2000L, modifier = Modifier.fillMaxWidth()
                    )
                    SharedComponents.CardInformationStatus(snackBarModel)
                }
                ChangePasswordForm(Modifier.fillMaxWidth().padding(vertical = 14.dp))
                SharedComponents.BlueFilledButton(
                    modifier = Modifier.fillMaxWidth(), enabled = uiSate.enabledButtonConfirmPasswordChange,
                    onClick = { myProfileVM.validateChangePassword() },
                    text = R.string.txt_button_change_password
                )
            }
        }
    }

}

@Composable
fun ChangePasswordSuccessDialog(navController: NavController) {
    val color = Blue
    val myProfileVM: MyProfileVM = koinViewModel<MyProfileVM>()
    val uiSate = myProfileVM.myProfileUIStateValue.collectAsState().value
    val shape = GenericProperties.roundenShapeDefault
    val borderStroke = GenericProperties.borderStrokeDynamic(color)
    val cardColors = GenericProperties.whiteCardColor

    val toolBarModel = ToolBarModel(title = stringResource(R.string.txt_title_change_password_success))
    val dialogModel = DialogModel(message = stringResource(R.string.txt_change_password_success))
    val navAction = {
        myProfileVM.setChangePasswordSuccess(false)
        navController.navigate(LoginRoute) { popUpTo(LoginRoute) { inclusive = true } }
    }
    Dialog(
        onDismissRequest = { navAction.invoke() },
        properties = GenericProperties.noCancelableProperty
    ) {
        Card(
            shape = shape, border = borderStroke, colors = cardColors,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
                SharedComponents.ToolBar(toolBarModel)
                DialoguesAndSnackBars.MessageContent(dialogModel = dialogModel)
                Spacer(Modifier.height(14.dp))
                SharedComponents.BlueFilledButton(
                    modifier = Modifier.fillMaxWidth(), enabled = uiSate.enabledButtonConfirmPasswordChange,
                    onClick = { navAction.invoke() },
                    text = R.string.txt_accept
                )
            }
        }
    }

}

@Composable
fun ChangePasswordForm(modifier: Modifier) {
    val myProfileVM: MyProfileVM = koinViewModel<MyProfileVM>()
    val uiState = myProfileVM.myProfileUIStateValue.collectAsState().value
    Column(modifier = modifier) {
        val currentPasswordTextFieldModel = TextFieldModel(
            label = { Text(stringResource(R.string.txt_placeholder_current_password)) },
            keyboardType = KeyboardType.Password, trailingIcon = TrailingIconTypes.PASSWORD,
            togglePasswordAction = { myProfileVM.setCurrentPasswordVisible(uiState.currentPasswordVisible) }
        )
        SharedComponents.OutlinedTextFieldPassword(
            modifier = Modifier.getModifierWithOnFocusChanged {
                    isFocus -> myProfileVM.setCurrentPasswordFocus(isFocus, uiState.currentPasswordVisible)
            }.fillMaxWidth().padding(bottom = 16.dp),
            onValueChange = { value -> myProfileVM.setCurrentPasswordValue(value)}, value = uiState.currentPasswordValue,
            textFieldModel = currentPasswordTextFieldModel,
            isPasswordVisible = uiState.currentPasswordVisible, isFocused = uiState.currentPasswordFocus
        )
        val newPasswordTextFieldModel = TextFieldModel(
            label = { Text(stringResource(R.string.txt_placeholder_new_password)) },
            keyboardType = KeyboardType.Password, trailingIcon = TrailingIconTypes.PASSWORD,
            togglePasswordAction = { myProfileVM.setNewPasswordVisible(uiState.newPasswordVisible) }
        )
        SharedComponents.OutlinedTextFieldPassword(
            modifier = Modifier.getModifierWithOnFocusChanged {
                    isFocus -> myProfileVM.setNewPasswordFocus(isFocus, uiState.newPasswordVisible)
            }.fillMaxWidth().padding(bottom = 16.dp),
            onValueChange = { value -> myProfileVM.setNewPasswordValue(value)},
            value = uiState.newPasswordValue, textFieldModel = newPasswordTextFieldModel,
            isPasswordVisible = uiState.newPasswordVisible, isFocused = uiState.newPasswordFocus
        )
        val confirmPasswordTextFieldModel = TextFieldModel(
            label = { Text(stringResource(R.string.txt_placeholder_confirm_password)) },
            keyboardType = KeyboardType.Password, trailingIcon = TrailingIconTypes.PASSWORD,
            togglePasswordAction = { myProfileVM.setConfirmPasswordVisible(uiState.confirmPasswordVisible) }
        )
        SharedComponents.OutlinedTextFieldPassword(
            modifier = Modifier.getModifierWithOnFocusChanged {
                    isFocus -> myProfileVM.setConfirmPasswordFocus(isFocus, uiState.confirmPasswordVisible)
            }.fillMaxWidth().padding(bottom = 16.dp),
            onValueChange = { value -> myProfileVM.setConfirmPasswordValue(value)}, value = uiState.confirmPasswordValue,
            textFieldModel = confirmPasswordTextFieldModel,
            isPasswordVisible = uiState.confirmPasswordVisible, isFocused = uiState.confirmPasswordFocus
        )
    }

}

@Composable
fun EmployeeInformation() {
    val myProfileVM: MyProfileVM = koinViewModel<MyProfileVM>()
    val uiSate = myProfileVM.myProfileUIStateValue.collectAsState().value
    val employeeWithCompany = uiSate.employeeWithCompany
    Column(
        modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SharedComponents.Subtitle(
            subtitle = stringResource(R.string.txt_employee_information),
            modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp)
        )
        SharedComponents.InformationItem(
            modifier = Modifier.fillMaxWidth().padding(bottom = 6.dp),
            subtitle = stringResource(R.string.txt_email), information = employeeWithCompany.email
        )
        SharedComponents.InformationItem(
            modifier = Modifier.fillMaxWidth().padding(bottom = 6.dp),
            subtitle = stringResource(R.string.txt_name), information = employeeWithCompany.fullName
        )
        SharedComponents.InformationItem(
            modifier = Modifier.fillMaxWidth().padding(bottom = 6.dp),
            subtitle = stringResource(R.string.txt_last_name), information = employeeWithCompany.fullLastName
        )
        SharedComponents.InformationItem(
            modifier = Modifier.fillMaxWidth(),
            subtitle = stringResource(R.string.txt_document), information = employeeWithCompany.document
        )
    }

}