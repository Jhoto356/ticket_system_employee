package com.example.ticket_system_employee.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.ticket_system_employee.presentation.loginScreen.LoginMain

private fun NavGraphBuilder.loginNav(navController: NavHostController) {
    composable<LoginRoute> { LoginMain(navController) }

}

@Composable
fun StartupNavHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = LoginRoute) {
        loginNav(navController)

    }

}