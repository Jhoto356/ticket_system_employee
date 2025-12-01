package com.example.ticket_system_employee.core.db.adapters

data class LookupItemsToUI(
    val requestType: RequestTypeAdapterToUI ?= null,
    val area: AreaAdapterToUI ?= null
)
