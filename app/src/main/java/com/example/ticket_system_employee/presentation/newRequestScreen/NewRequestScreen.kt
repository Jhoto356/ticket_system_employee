package com.example.ticket_system_employee.presentation.newRequestScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.ticket_system_employee.R
import com.example.ticket_system_employee.presentation.commons.models.DialogModel
import com.example.ticket_system_employee.presentation.commons.models.TextFieldModel
import com.example.ticket_system_employee.presentation.commons.models.ToolBarModel
import com.example.ticket_system_employee.presentation.commons.models.TrailingIconTypes
import com.example.ticket_system_employee.presentation.commons.shared.DialoguesAndSnackBars
import com.example.ticket_system_employee.presentation.commons.shared.SharedComponents
import com.example.ticket_system_employee.presentation.commons.shared.SharedComponents.getModifierWithOnFocusChanged
import com.example.ticket_system_employee.presentation.theme.Blue
import com.example.ticket_system_employee.presentation.theme.ErrorColor
import com.example.ticket_system_employee.presentation.theme.White
import org.koin.androidx.compose.koinViewModel

@Composable
fun NewRequestView(navController: NavController) {
    val newRequestVM: NewRequestVM = koinViewModel<NewRequestVM>()
    val uiState = newRequestVM.newRequestUIStateValue.collectAsState().value
    val toolBarModel = ToolBarModel(
        title = stringResource(R.string.txt_create_request),
        backAction = { navController.popBackStack() }, showBackIcon = true
    )
    val scrollState = rememberScrollState()
    Dialogs(navController)
    Column(
        modifier = Modifier.fillMaxSize(). background(White).systemBarsPadding().imePadding(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SharedComponents.ToolBar(toolBarModel)
        Column(modifier = Modifier.weight(1f).fillMaxWidth(0.85f).verticalScroll(scrollState)) {
            CompanyInformation()
            EmployeeInformation()
            RequestForm(Modifier.weight(1f))
            SharedComponents.BlueFilledButton(
                modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp), enabled = uiState.enabledButton,
                onClick = {
                    newRequestVM.addNewTicket()
                    newRequestVM.setTicketSuccessSave(true)
                },
                text = R.string.txt_create_request
            )
        }
    }

}

@Composable
fun Dialogs(navController: NavController) {
    val newRequestVM: NewRequestVM = koinViewModel<NewRequestVM>()
    val uiState = newRequestVM.newRequestUIStateValue.collectAsState().value

    if (uiState.ticketSuccessSave) {
        newRequestVM.setIsLoading(false)
        val dialogModel = DialogModel(
            title = "Ticket guardado", color = Blue,
            confirmAction = {
                newRequestVM.setTicketSuccessSave(false)
                navController.popBackStack()
            }, message = "El ticket fue guardadoc correctamnte, y esta pendiente de aprobación."
        )
        DialoguesAndSnackBars.GenericDialog(dialogModel)
    }

    if (uiState.errorStatus) {
        newRequestVM.setIsLoading(false)
        val dialogModel = DialogModel(
            title = stringResource(R.string.txt_title_error_getting_information), color = ErrorColor,
            confirmAction = {
                newRequestVM.setErrorStatus(false)
                navController.popBackStack()
            }, message = stringResource(R.string.txt_error_get_information_by_request)
        )
        DialoguesAndSnackBars.GenericDialog(dialogModel)
    }
}

@Composable
fun CompanyInformation() {
    val newRequestVM: NewRequestVM = koinViewModel<NewRequestVM>()
    val uiState = newRequestVM.newRequestUIStateValue.collectAsState().value
    val employeeWithCompany = uiState.employeeWithCompany
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
fun EmployeeInformation() {
    val newRequestVM: NewRequestVM = koinViewModel<NewRequestVM>()
    val uiState = newRequestVM.newRequestUIStateValue.collectAsState().value
    val employeeWithCompany = uiState.employeeWithCompany
    Column(modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)) {
        SharedComponents.Subtitle(
            subtitle = stringResource(R.string.txt_employee_information),
            modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp)
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
            modifier = Modifier.fillMaxWidth().padding(bottom = 6.dp),
            subtitle = stringResource(R.string.txt_document), information = employeeWithCompany.document
        )

    }

}

@Composable
fun RequestForm(modifier: Modifier) {
    val newRequestVM: NewRequestVM = koinViewModel<NewRequestVM>()
    val uiState = newRequestVM.newRequestUIStateValue.collectAsState().value
    val lookupItemsToUI = uiState.lookupItemsToUI
    if (lookupItemsToUI.requestType != null && lookupItemsToUI.area != null) {
        newRequestVM.setIsLoading(false)
        newRequestVM.setAreaValue(lookupItemsToUI.area)
        newRequestVM.setRequestTypeValue(lookupItemsToUI.requestType)
    }
    Column(modifier = modifier.fillMaxWidth().padding(bottom = 16.dp)) {
        val requestTypeTextFieldModel = TextFieldModel(
            label = { Text(stringResource(R.string.txt_placeholder_request_type)) },
            readOnly = true,
            trailingIcon = TrailingIconTypes.DROPDOWN
        )
        if (uiState.requestTypeValue != null) {
            SharedComponents.OutlinedTextFieldCustom(
                modifier = Modifier.getModifierWithOnFocusChanged { isFocus ->
                    newRequestVM.setRequestTypeFocus(isFocus)
                }.fillMaxWidth().padding(bottom = 16.dp),
                value = uiState.requestTypeValue.description,
                onValueChange = { _ -> newRequestVM.setRequestTypeValue(uiState.requestTypeValue) },
                isFocused = uiState.requestTypeFocus,
                textFieldModel = requestTypeTextFieldModel
            )
        }
        val areaTextFieldModel = TextFieldModel(
            label = { Text(stringResource(R.string.txt_placeholder_area)) },
            readOnly = true,
            trailingIcon = TrailingIconTypes.DROPDOWN
        )
        if (uiState.areaValue != null) {
            SharedComponents.OutlinedTextFieldCustom(
                modifier = Modifier.getModifierWithOnFocusChanged { isFocus ->
                    newRequestVM.setAreaFocus(isFocus)
                }.fillMaxWidth().padding(bottom = 16.dp),
                value = uiState.areaValue.description,
                onValueChange = { _ -> newRequestVM.setAreaValue(uiState.areaValue) },
                isFocused = uiState.areaFocus,
                textFieldModel = areaTextFieldModel
            )
        }
        val descriptionTextFieldModel = TextFieldModel(
            label = { Text(stringResource(R.string.txt_placeholder_description)) },
            maxLines = 5, singleLine = false
        )
        SharedComponents.OutlinedTextFieldCustom(
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
            onValueChange = { value -> newRequestVM.setDescriptionValue(value) },
            value = uiState.descriptionValue,
            textFieldModel = descriptionTextFieldModel
        )
    }

}