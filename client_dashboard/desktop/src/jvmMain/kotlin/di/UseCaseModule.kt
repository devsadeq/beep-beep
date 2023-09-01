package di

import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import org.thechance.common.domain.usecase.*

val UseCaseModule = module {
    singleOf(::GetUserInfoUseCase) { bind<IGetUserInfoUseCase>() }
    singleOf(::GetUsersUseCase) { bind<IGetUsersUseCase>() }
    singleOf(::GetTaxisUseCase) { bind<IGetTaxisUseCase>() }
    singleOf(::CreateNewTaxiUseCase) { bind<ICreateNewTaxiUseCase>() }
    singleOf(::FindTaxisByUserNameUseCase) { bind<IFindTaxisByUsernameUseCase>() }
    singleOf(::LoginUserUseCase) { bind<ILoginUserUseCase>() }
    singleOf(::ManageRestaurantUseCase) { bind<IManageRestaurantUseCase>() }
    singleOf(::HandleLocationUseCase) { bind<IHandleLocationUseCase>() }
    singleOf(::GetTaxiReportUseCase) { bind<IGetTaxiReportUseCase>() }
    singleOf(::FilterTaxisUseCase) { bind<IFilterTaxisUseCase>() }
    singleOf(::SearchUsersUseCase) { bind<ISearchUsersUseCase>() }
    singleOf(::ThemeManagementUseCase) { bind<IThemeManagementUseCase>() }
    singleOf(::UpdateTaxiUseCase) { bind<IUpdateTaxiUseCase>() }
    singleOf(::DeleteTaxiUseCase) { bind<IDeleteTaxiUseCase>() }
}