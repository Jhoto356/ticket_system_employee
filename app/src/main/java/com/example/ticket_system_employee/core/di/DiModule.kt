package com.example.ticket_system_employee.core.di

import com.example.ticket_system_employee.core.uitls.DateAndTimeUtils
import com.example.ticket_system_employee.data.repositoryImp.login.LoginRepositoryImp
import com.example.ticket_system_employee.data.repositoryImp.main.MainRepositoryImp
import com.example.ticket_system_employee.data.repositoryImp.myProfile.MyProfileRepositoryImp
import com.example.ticket_system_employee.data.repositoryImp.newRequest.NewRequestRepositoryImp
import com.example.ticket_system_employee.dataSources.local.AreaDataSource
import com.example.ticket_system_employee.dataSources.local.CompanyDataSource
import com.example.ticket_system_employee.dataSources.local.EmployeeDataSource
import com.example.ticket_system_employee.dataSources.local.RequestTypeDataSource
import com.example.ticket_system_employee.dataSources.local.TicketDataSource
import com.example.ticket_system_employee.domain.repository.login.LoginRepository
import com.example.ticket_system_employee.domain.repository.main.MainRepository
import com.example.ticket_system_employee.domain.repository.myProfile.MyProfileRepository
import com.example.ticket_system_employee.domain.repository.newRequest.NewRequestRepository
import com.example.ticket_system_employee.domain.useCases.login.LoginUseCases
import com.example.ticket_system_employee.domain.useCases.main.MainUseCases
import com.example.ticket_system_employee.domain.useCases.myProfile.MyProfileUseCases
import com.example.ticket_system_employee.domain.useCases.newRequest.NewRequestUseCases
import com.example.ticket_system_employee.presentation.myProfileScreen.MyProfileVM
import com.example.ticket_system_employee.presentation.loginScreen.LoginVM
import com.example.ticket_system_employee.presentation.mainScreen.MainVM
import com.example.ticket_system_employee.presentation.newRequestScreen.NewRequestVM
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val appModule = module {
   single { DataBase.providerRoom(get()) }
    single { DateAndTimeUtils() }
}

val dataBaseModule = module {
    single { DataBase.providerEmployeeDao(get()) }
    single { DataBase.providerCompanyDao(get()) }
    single { DataBase.providerTicketDao(get()) }
    single { DataBase.providerAreaDao(get()) }
    single { DataBase.providerRequestTypeDao(get()) }
}

val dataSourcesModule = module {
    single { CompanyDataSource(get()) }
    single { EmployeeDataSource(get()) }
    single { TicketDataSource(get()) }
    single { RequestTypeDataSource(get()) }
    single { AreaDataSource(get()) }
}

val repositoryImpModule = module {
    single<LoginRepository> { LoginRepositoryImp(get()) }
    single<MyProfileRepository> { MyProfileRepositoryImp(get()) }
    single<NewRequestRepository> { NewRequestRepositoryImp(get()) }
    single<MainRepository> { MainRepositoryImp(get()) }
}

val useCaseModule = module {
    single { MyProfileUseCases(get()) }
    single { LoginUseCases(get()) }
    single { NewRequestUseCases(get()) }
    single { MainUseCases(get()) }
}

val viewModelModule = module {
    viewModel<NewRequestVM> { NewRequestVM(get()) }
    viewModel<MyProfileVM> { MyProfileVM(get()) }
    viewModel<LoginVM> { LoginVM(get()) }
    viewModel<MainVM> { MainVM(get()) }
}





