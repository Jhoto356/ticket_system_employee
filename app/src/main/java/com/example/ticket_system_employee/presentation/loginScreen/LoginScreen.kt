package com.example.ticket_system_employee.presentation.loginScreen

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.ticket_system_employee.BuildConfig
import com.example.ticket_system_employee.R
import com.example.ticket_system_employee.core.navigation.StartupNavHost
import com.example.ticket_system_employee.presentation.commons.models.DialogModel
import com.example.ticket_system_employee.presentation.commons.models.TextFieldModel
import com.example.ticket_system_employee.presentation.commons.models.TrailingIconTypes
import com.example.ticket_system_employee.presentation.commons.shared.DialoguesAndSnackBars
import com.example.ticket_system_employee.presentation.commons.shared.SharedComponents
import com.example.ticket_system_employee.presentation.commons.shared.SharedComponents.getModifierWithOnFocusChanged
import com.example.ticket_system_employee.presentation.theme.*
import org.koin.androidx.compose.koinViewModel

class LoginScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TicketSystemEmployeeTheme {
                val navController = rememberNavController()
                StartupNavHost(navController)
            }
        }
    }
}

@Composable
fun LoginDialogs(onNavToMain: () -> Unit) {
    val loginVM: LoginVM = koinViewModel<LoginVM>()
    val uiState = loginVM.loginUIStateValues.collectAsState().value
    val error = uiState.errorLogin
    val message = uiState.errorMessage
    val success = uiState.successLogin
    val isLoading = uiState.isLoading

    if (isLoading) { SharedComponents.LoadingSplash(uiState.loadingMessage) }
    if (success) { onNavToMain.invoke() }
    if (error) {
        val dialogModel = DialogModel(
            confirmAction = {
                loginVM.setErrorLogin(false, "")
            }, color = ErrorColor, message = message,
            title = stringResource(R.string.txt_title_error_login)
        )
        DialoguesAndSnackBars.GenericErrorDialog(dialogModel)
        loginVM.setIsLoading(false, "")
    }

}

@Composable
fun LoginView(onNavToMain: () -> Unit) {
    val context: Context = LocalContext.current
    val activity = context as ComponentActivity

    val scrollState = rememberScrollState()
    Box (modifier = Modifier.fillMaxSize().systemBarsPadding().imePadding().background(White)) {
        LoginDialogs(onNavToMain)
        Column(modifier = Modifier.align(Alignment.Center).fillMaxWidth(0.85f).fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier.padding(top = 20.dp, bottom = 14.dp).fillMaxWidth(),
                text = stringResource(R.string.txt_welcome),
                style = TextStyles.titleStyle(Black)
            )
            Image(
                painter = painterResource(R.drawable.areandina_black),
                modifier = Modifier.fillMaxWidth(0.9f),
                contentDescription = null
            )
            Column(modifier = Modifier.fillMaxWidth().wrapContentHeight().weight(1f).verticalScroll(scrollState),
                verticalArrangement = Arrangement.Center
            ) {
                LoginForm(modifier = Modifier.fillMaxWidth())
            }
            Text(
                modifier = Modifier.padding(12.dp).fillMaxWidth(),
                text = stringResource(R.string.txt_version, BuildConfig.VERSION_NAME),
                style = TextStyles.versionStyle(Black)
            )
        }
        BackHandler { activity.finish() }
    }

}

@Composable
fun LoginForm(modifier: Modifier) {
    val loginVM: LoginVM = koinViewModel<LoginVM>()
    val uiState = loginVM.loginUIStateValues.collectAsState().value
    val enabledButton = uiState.enabledButton
    val passwordVisible = uiState.passwordVisible
    val passwordFocus = uiState.passwordFocus
    val onClick = { loginVM.validateLogin() }
    Column(modifier = modifier, verticalArrangement = Arrangement.Center) {
        val domainTextFieldModel = TextFieldModel(
            label = { Text(stringResource(R.string.txt_placeholder_domain))  }
        )
        SharedComponents.OutlinedTextFieldCustom(
            modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp),
            onValueChange = { value -> loginVM.setDomainValue(value) },
            value = uiState.domainValue,
            textFieldModel = domainTextFieldModel
        )
        val emailTextFieldModel = TextFieldModel(
            label = { Text(stringResource(R.string.txt_placeholder_email)) },
            keyboardType = KeyboardType.Email,
        )
        SharedComponents.OutlinedTextFieldCustom(
            modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp),
            onValueChange = { value -> loginVM.setEmailValue(value)},
            value = uiState.emailValue,
            textFieldModel = emailTextFieldModel
        )
        val passwordTextFieldModel = TextFieldModel(
            label = { Text(stringResource(R.string.txt_placeholder_password)) },
            keyboardType = KeyboardType.Password,
            trailingIcon = TrailingIconTypes.PASSWORD,
            togglePasswordAction = { loginVM.setPasswordVisible(passwordVisible) }
        )
        SharedComponents.OutlinedTextFieldPassword(
            modifier = Modifier.getModifierWithOnFocusChanged {
                isFocus -> loginVM.setPasswordFocus(isFocus, passwordVisible)
            }.fillMaxWidth().padding(bottom = 16.dp),
            onValueChange = { value -> loginVM.setPasswordValue(value)},
            value = uiState.passwordValue,
            textFieldModel = passwordTextFieldModel,
            isPasswordVisible = passwordVisible,
            isFocused = passwordFocus
        )
        SharedComponents.BlueFilledButton(
            modifier.fillMaxWidth(), enabledButton, onClick, R.string.txt_button_login)

    }

}
