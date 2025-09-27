package com.example.ticket_system_employee.presentation.myProfile

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.ticket_system_employee.presentation.commons.models.ToolBarModel
import com.example.ticket_system_employee.presentation.commons.shared.SharedComponents
import com.example.ticket_system_employee.presentation.theme.White
import com.example.ticket_system_employee.R
import org.koin.androidx.compose.koinViewModel

@Composable
fun MyProfileView(navController: NavController) {
    BackHandler {
        navController.popBackStack()
    }
    val toolBarModel = ToolBarModel(
        title = stringResource(R.string.txt_my_profile),
        backAction =  { navController.popBackStack() }, showBackIcon = true
    )
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier.fillMaxWidth().background(White),
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
            modifier = Modifier.fillMaxWidth(0.9f).padding(bottom = 24.dp),
            text = R.string.txt_button_change_password,
            onClick = {  }
        )
    }

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