package com.example.ticket_system_employee.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.ticket_system_employee.presentation.mainScreen.MainView
import com.example.ticket_system_employee.presentation.myProfileScreen.MyProfileView
import com.example.ticket_system_employee.presentation.loginScreen.LoginView
import com.example.ticket_system_employee.presentation.newRequestScreen.NewRequestView

private fun NavGraphBuilder.loginNav(navController: NavHostController) {
    composable<LoginRoute> {
        LoginView(
            onNavToMain = {
                navController.navigate(MainRoute) {
                    popUpTo(LoginRoute) { inclusive = true }
                }
            }
        )
    }

}

private fun NavGraphBuilder.myProfileNav(navController: NavHostController) {
    composable<MyProfileRoute> {
        MyProfileView(navController)
    }

}
private fun NavGraphBuilder.newRequestNav(navController: NavHostController) {
    composable<NewRequestRoute> {
        NewRequestView()
    }

}

private fun NavGraphBuilder.mainNav(navController: NavHostController) {
    composable<MainRoute> { MainView(navController) }
}

@Composable
fun StartupNavHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = LoginRoute) {
        loginNav(navController)
        mainNav(navController)
        myProfileNav(navController)
        newRequestNav(navController)
    }

}