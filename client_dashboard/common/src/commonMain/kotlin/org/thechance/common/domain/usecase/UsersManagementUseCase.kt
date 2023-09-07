package org.thechance.common.domain.usecase

import org.thechance.common.domain.entity.DataWrapper
import org.thechance.common.domain.entity.Permission
import org.thechance.common.domain.entity.User
import org.thechance.common.domain.getway.IUsersGateway

interface IUsersManagementUseCase {

    suspend fun getUserInfo(): String

    suspend fun getUsers(
        query: String? = null,
        byPermissions: List<Permission>,
        byCountries: List<String>,
        page: Int,
        numberOfUsers: Int
    ): DataWrapper<User>

}

class UsersManagementUseCase(
    private val userGateway: IUsersGateway
) : IUsersManagementUseCase {

    override suspend fun getUsers(
        query: String?,
        byPermissions: List<Permission>,
        byCountries: List<String>,
        page: Int,
        numberOfUsers: Int,
    ): DataWrapper<User> {
        return userGateway.getUsers(query, byPermissions, byCountries, page, numberOfUsers)
    }

    override suspend fun getUserInfo(): String {
        return userGateway.getUserData()
    }

}