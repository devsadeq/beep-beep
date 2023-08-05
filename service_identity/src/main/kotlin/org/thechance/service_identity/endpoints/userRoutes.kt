package org.thechance.service_identity.api.endpoints

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject
import org.thechance.service_identity.api.model.request.CreateUserRequest
import org.thechance.service_identity.api.model.request.UpdateUserRequest
import org.thechance.service_identity.data.mappers.toDetailedDto
import org.thechance.service_identity.data.mappers.toDto
import org.thechance.service_identity.data.mappers.toEntity
import org.thechance.service_identity.domain.usecases.user.UserUseCaseContainer
import org.thechance.service_identity.domain.usecases.useraccount.UserAccountUseCase
import org.thechance.service_identity.endpoints.validation.InvalidIDException

fun Route.userRoutes() {


    val userUseCaseContainer: UserUseCaseContainer by inject()

    val userAccountUseCase: UserAccountUseCase by inject()

    route("/users") {

        get {
            val users = userAccountUseCase.getUsers()
            call.respond(HttpStatusCode.OK, users.map { it.toDto() })
        }

        get("/{id}") {
            val id = call.parameters["id"] ?: throw InvalidIDException
            val user = userAccountUseCase.getUser(id).toDto()
            call.respond(HttpStatusCode.OK, user)
        }

        get("/detailed") {
            val users = userUseCaseContainer.getDetailedUsers()
            call.respond(HttpStatusCode.OK, users.map { it.toDetailedDto() })
        }

        post {
            val user = call.receive<CreateUserRequest>()
            val result = userAccountUseCase.createUser(user.toEntity())
            call.respond(HttpStatusCode.Created, result)
        }

        put("/{id}") {
            val id = call.parameters["id"] ?: throw InvalidIDException
            val userDto = call.receive<UpdateUserRequest>()
            val result = userAccountUseCase.updateUser(id, userDto.toEntity())
            call.respond(HttpStatusCode.OK, result)
        }

        delete("/{id}") {
            val id = call.parameters["id"] ?: throw InvalidIDException
            val result = userAccountUseCase.deleteUser(id)
            call.respond(HttpStatusCode.OK, result)
        }

        post("/{userId}/permission") {
            val userId = call.parameters["userId"] ?: throw BadRequestException("User id is required")
            val permissionId = call.receiveParameters()["permissionId"]
                ?: throw BadRequestException("Permission id is required")
            userUseCaseContainer.addPermissionToUser(userId, permissionId)
            call.respond("Permission added to user successfully")
        }

        delete("/{userId}/permission") {
            val userId = call.parameters["userId"] ?: throw BadRequestException("User id is required")
            val permissionId = call.parameters["permissionId"]
                ?: throw BadRequestException("Permission id is required")
            userUseCaseContainer.removePermissionFromUser(userId, permissionId)
            call.respond("Permission removed from user successfully")
        }

        get("/{userId}/permission") {
            val userId = call.parameters["userId"] ?: throw BadRequestException("User id is required")
            val permissions = userUseCaseContainer.getUserPermissions(userId)
            call.respond(HttpStatusCode.OK, permissions.map { it.toDto() })
        }

    }
}