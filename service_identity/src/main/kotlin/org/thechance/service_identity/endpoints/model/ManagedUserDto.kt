package org.thechance.service_identity.endpoints.model

import kotlinx.serialization.Serializable

@Serializable
data class ManagedUserDto(
    val id: String,
    val fullName: String,
    val username: String,
    val email: String,
    val permissions: List<PermissionDto>
)