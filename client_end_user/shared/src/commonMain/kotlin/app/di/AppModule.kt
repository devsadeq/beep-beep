package app.di

import data.gateway.FakeGatewayImp
import data.local.source.FakeLocalDataSourceImp
import data.remote.service.FakeApiService
import data.remote.service.FakeApiServiceImp
import data.remote.source.FakeRemoteDataSourceImp
import domain.gateway.FakeGateway
import domain.source.FakeLocalDataSource
import domain.source.FakeRemoteDataSource
import domain.usecase.FakeUseCase
import domain.usecase.FakeUseCaseImp
import org.koin.core.Koin
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module
import presentation.screens.HomeViewModel

object AppModule {

    fun initKoin(appDeclaration: KoinAppDeclaration = {}) = startKoin {
        appDeclaration()
        modules(
            networkModule,
            gatewayModule,
            useCaseModule,
            viewModelModule,
            dataSourceModule
            dataSourceModule
        )
    }

    val networkModule = module {

        single<FakeApiService> { FakeApiServiceImp() }

    }

    val dataSourceModule = module {
        single<FakeRemoteDataSource> {
            FakeRemoteDataSourceImp(get())
        }

        single<FakeLocalDataSource> {
            FakeLocalDataSourceImp()
        }

    }

    val gatewayModule = module {
        single<FakeGateway> { FakeGatewayImp(get(), get()) }
    }

    val useCaseModule = module {
        single<FakeUseCase>{ FakeUseCaseImp(get()) }
    }

    val viewModelModule = module {
        factory { HomeViewModel() }
    }

}