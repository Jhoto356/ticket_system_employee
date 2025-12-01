package com.example.ticket_system_employee.dataSources.local

import android.content.Context
import com.example.ticket_system_employee.core.db.adapters.RequestTypeAdapterToUI
import com.example.ticket_system_employee.core.db.adapters.toListModel
import com.example.ticket_system_employee.core.db.daos.RequestTypeDao
import com.example.ticket_system_employee.core.db.entities.RequestTypeEntity

class RequestTypeDataSource(private val requestTypeDao: RequestTypeDao) {
    fun getAllRequestType(): List<RequestTypeAdapterToUI> {
        val requestsType = requestTypeDao.getAllRequestsType()
        if (requestsType.isEmpty()) return emptyList()
        return requestsType.toListModel()
    }
    fun getEnabledRequestType(): List<RequestTypeAdapterToUI> {
        val areas = requestTypeDao.getEnabledRequestsType()
        if (areas.isEmpty()) return emptyList()
        return areas.toListModel()
    }
    fun insertRequestType(lstRequestType: ArrayList<RequestTypeEntity>) {
        try {
            requestTypeDao.insertRequestsType(lstRequestType)
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }
}