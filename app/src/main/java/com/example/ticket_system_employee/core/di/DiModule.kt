package com.example.ticket_system_employee.core.di

import com.example.ticket_system_employee.data.repositoryImp.LoginRepositoryImp
import com.example.ticket_system_employee.dataSources.local.EmployeeDataSource
import com.example.ticket_system_employee.domain.repository.login.LoginRepository
import com.example.ticket_system_employee.domain.useCases.login.LoginUseCases
import com.example.ticket_system_employee.presentation.loginScreen.LoginVM
import org.koin.dsl.module


val appModule = module {
   single { DataBase.providerRoom(get()) }
}

val dataBaseModule = module {
    single { DataBase.providerEmployeeDao(get()) }
}

val dataSourcesModule = module {
    single { EmployeeDataSource() }
}

val viewModelModule = module {
    factory { LoginVM() }
}

val repositoryImpModule = module {
    single<LoginRepository> { LoginRepositoryImp(get()) }
}

val useCaseModule = module {
    factory { LoginUseCases(get()) }
}



