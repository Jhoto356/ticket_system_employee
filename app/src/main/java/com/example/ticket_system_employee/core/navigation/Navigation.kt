package com.example.ticket_system_employee.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.ticket_system_employee.presentation.commons.mainScreen.MainView
import com.example.ticket_system_employee.presentation.commons.myProfile.MyProfileView
import com.example.ticket_system_employee.presentation.loginScreen.LoginView

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
    composable<ProfileRoute> {
        MyProfileView(
            onNavToMain = {
                navController.popBackStack()
            }
        )
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
    }

}