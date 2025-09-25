package com.example.ticket_system_employee.presentation.loginScreen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.ticket_system_employee.core.di.Module
import com.example.ticket_system_employee.core.navigation.StartupNavHost
import com.example.ticket_system_employee.presentation.theme.TicketSystemEmpleyeeTheme
import com.example.ticket_system_employee.presentation.theme.White

class LoginScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TicketSystemEmpleyeeTheme {
                val navController = rememberNavController()
                StartupNavHost(navController)
            }
        }
    }
}

@Composable
fun LoginMain(navController: NavHostController) {
    Box(modifier = Modifier.fillMaxSize().systemBarsPadding().imePadding().background(White)) {

    }

}
