package com.example.ticket_system_employee.core.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.ticket_system_employee.core.db.AppDB
import com.example.ticket_system_employee.core.db.ConstantsDB
import com.example.ticket_system_employee.core.db.daos.EmployeeDao

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

}