package com.example.ticket_system_employee.presentation.loginScreen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.ticket_system_employee.core.di.Module
import com.example.ticket_system_employee.core.navigation.StartupNavHost
import com.example.ticket_system_employee.presentation.theme.TicketSystemEmpleyeeTheme

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

}
