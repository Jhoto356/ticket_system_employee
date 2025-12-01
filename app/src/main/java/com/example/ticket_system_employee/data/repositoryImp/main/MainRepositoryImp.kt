package com.example.ticket_system_employee.data.repositoryImp.main

import android.content.Context
import com.example.ticket_system_employee.R
import com.example.ticket_system_employee.core.db.adapters.TicketAdapterToUI
import com.example.ticket_system_employee.dataSources.local.AreaDataSource
import com.example.ticket_system_employee.dataSources.local.RequestTypeDataSource
import com.example.ticket_system_employee.dataSources.local.TicketDataSource
import com.example.ticket_system_employee.domain.repository.main.MainRepository
import com.example.ticket_system_employee.domain.result.main.MainResult
import org.koin.java.KoinJavaComponent.inject

class MainRepositoryImp(private val context: Context): MainRepository {
    private val ticketDataSource: TicketDataSource by inject(TicketDataSource::class.java)
    private val areaDataSource: AreaDataSource by inject(AreaDataSource::class.java)
    private val requestTypeDataSource: RequestTypeDataSource by inject(RequestTypeDataSource::class.java)

    override fun getTickets(): MainResult {
        try {
            val tickets = ticketDataSource.getAllTickets()
            if (tickets.isEmpty()) return MainResult.NotContent
            val requestsType = requestTypeDataSource.getAllRequestType()
            val areas = areaDataSource.getEnabledAreas()
            if (requestsType.isEmpty() || areas.isEmpty()) {
                val message = context.getString(R.string.txt_error_loading_information)
                return MainResult.Failure(message)
            }
            val lstTicket = ArrayList<TicketAdapterToUI>()
            tickets.map { ticket ->
                val ticketAdapterToUI = TicketAdapterToUI(
                    id = ticket.id,
                    category = requestsType.first().description,
                    areaId = requestsType.first().description,
                    description = ticket.description,
                    status = ticket.status,
                    registerDate = ticket.registerDate
                )
                lstTicket.add(ticketAdapterToUI)
            }
            return MainResult.Tickets(lstTicket)
        } catch (e: Exception) {
            e.printStackTrace()
            val message = context.getString(R.string.txt_error_loading_information)
            return MainResult.Failure(message)
        }

    }

}