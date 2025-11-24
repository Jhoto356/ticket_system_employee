package com.example.ticket_system_employee.core.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.ticket_system_employee.core.db.AppDB
import com.example.ticket_system_employee.core.db.ConstantsDB
import com.example.ticket_system_employee.core.db.daos.AreaDao
import com.example.ticket_system_employee.core.db.daos.CompanyDao
import com.example.ticket_system_employee.core.db.daos.EmployeeDao
import com.example.ticket_system_employee.core.db.daos.RequestTypeDao
import com.example.ticket_system_employee.core.db.daos.TicketDao

object DataBase {
    fun providerRoom(context: Context): AppDB {
        return Room.databaseBuilder(context.applicationContext, AppDB::class.java, ConstantsDB.DATABASE_NAME)
            .setJournalMode(RoomDatabase.JournalMode.TRUNCATE)
            .fallbackToDestructiveMigrationOnDowngrade(true)
            .build()
    }

    fun providerEmployeeDao(db: AppDB): EmployeeDao {
        return db.employee()
    }

    fun providerCompanyDao(db: AppDB): CompanyDao {
        return db.company()
    }

    fun providerTicketDao(db: AppDB): TicketDao {
        return db.ticket()
    }

    fun providerRequestTypeDao(db: AppDB): RequestTypeDao {
        return db.requestType()
    }

    fun providerAreaDao(db: AppDB): AreaDao {
        return db.area()
    }

}