package com.example.ticket_system_employee.presentation.commons.mainScreen

import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.example.ticket_system_employee.R
import com.example.ticket_system_employee.presentation.commons.models.ToolBarModel
import com.example.ticket_system_employee.presentation.commons.shared.SharedComponents
import com.example.ticket_system_employee.presentation.theme.White

@Composable
fun MainView(navController: NavHostController) {
    val context = LocalContext.current
    val activity = context as ComponentActivity
    BackHandler {
        activity.finish()
    }
    val toolBarModel = ToolBarModel(title = stringResource(R.string.txt_my_tickets))
    Column(modifier = Modifier.fillMaxSize().background(White)) {
        SharedComponents.ToolBar(toolBarModel)
        Column(modifier = Modifier.weight(1f)) {  }
        SharedComponents.BottomNavigationBar(navController)

    }
}