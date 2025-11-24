package com.example.ticket_system_employee.dataSources.local

import com.example.ticket_system_employee.core.db.adapters.AreaAdapterToUI
import com.example.ticket_system_employee.core.db.adapters.toListModel
import com.example.ticket_system_employee.core.db.daos.AreaDao

class AreaDataSource(private val areaDao: AreaDao) {
    fun getAllAreas(): List<AreaAdapterToUI>? {
        val areas = areaDao.getAllAreas()
        if (areas.isEmpty()) return emptyList()
        return areas.toListModel()
    }
    fun saveAreas() {

    }
}