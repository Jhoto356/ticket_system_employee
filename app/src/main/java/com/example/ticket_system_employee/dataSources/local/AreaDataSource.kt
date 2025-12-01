package com.example.ticket_system_employee.dataSources.local

import android.content.Context
import com.example.ticket_system_employee.core.db.adapters.AreaAdapterToUI
import com.example.ticket_system_employee.core.db.adapters.toListModel
import com.example.ticket_system_employee.core.db.daos.AreaDao
import com.example.ticket_system_employee.core.db.entities.AreaEntity

class AreaDataSource(private val areaDao: AreaDao) {
    fun getAllAreas(): List<AreaAdapterToUI> {
        val areas = areaDao.getAllAreas()
        if (areas.isEmpty()) return emptyList()
        return areas.toListModel()
    }
    fun getEnabledAreas(): List<AreaAdapterToUI> {
        val areas = areaDao.getEnabledAreas()
        if (areas.isEmpty()) return emptyList()
        return areas.toListModel()
    }
    fun insertAreas(lstAreas: ArrayList<AreaEntity>) {
        try {
            areaDao.insertAreas(lstAreas)
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }
}