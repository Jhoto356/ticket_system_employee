package com.example.ticket_system_employee.presentation.mainScreen

import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.ticket_system_employee.R
import com.example.ticket_system_employee.core.db.entities.TicketEntity
import com.example.ticket_system_employee.core.uitls.UtilsProject
import com.example.ticket_system_employee.presentation.commons.models.ToolBarModel
import com.example.ticket_system_employee.presentation.commons.shared.GenericProperties
import com.example.ticket_system_employee.presentation.commons.shared.SharedComponents
import com.example.ticket_system_employee.presentation.theme.Black
import com.example.ticket_system_employee.presentation.theme.White

@Composable
fun MainView(navController: NavHostController) {
    val context = LocalContext.current
    val activity = context as ComponentActivity
    BackHandler {
        activity.finish()
    }
    val toolBarModel = ToolBarModel(title = stringResource(R.string.txt_my_tickets))
    Column(modifier = Modifier.fillMaxSize().background(White), horizontalAlignment = Alignment.CenterHorizontally) {
        Column(modifier = Modifier.fillMaxWidth(0.85f)) {
            SharedComponents.ToolBar(toolBarModel)
            Column(modifier = Modifier.weight(1f)) {
                SharedComponents.Subtitle("Solicitudes", modifier = Modifier.fillMaxWidth())
                Spacer(modifier = Modifier.height(16.dp))
                ListOfTickets()
            }
            SharedComponents.BottomNavigationBar(navController)
        }

    }

}

@Composable
fun ListOfTickets() {
    LazyColumn(modifier = Modifier.fillMaxWidth()) {
        items(
            count = UtilsProject.lstTickets.size
        ) {
            ItemTicket(UtilsProject.lstTickets[it])
            Spacer(modifier = Modifier.height(14.dp))
        }
    }
}

@Composable
fun ItemTicket(ticketEntity: TicketEntity) {
    val icon = when(ticketEntity.status) {
        0 -> { Icons.Default.Close }
        1 -> { Icons.Default.Check }
        2 -> { Icons.Filled.DateRange }
        3 -> {
            Icons.Default.Delete
        }
        else -> {
            Icons.Default.Close
        }
    }
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = GenericProperties.whiteCardColor,
        border = GenericProperties.borderStrokeDynamic(Black)
    ) {
        Row(Modifier.fillMaxWidth().padding(vertical = 12.dp, horizontal = 10.dp), verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = icon,
                contentDescription = null
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column(modifier = Modifier.weight(1f)) {
                SharedComponents.InformationItem(
                    subtitle = ticketEntity.requestType,
                    information = ticketEntity.description,
                    modifier = Modifier.fillMaxWidth()

                )
            }
        }
    }
}