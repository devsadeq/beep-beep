package domain.gateway

import domain.entity.Restaurant
import domain.entity.Session
import domain.entity.Account
import domain.entity.Price
import domain.entity.User

interface IUserGateway {
    suspend fun createUser(userCreation: Account): User
    suspend fun loginUser(username: String, password: String): Session
    suspend fun refreshAccessToken(refreshToken: String): Pair<String, String>
    suspend fun getUserProfile(): User
    suspend fun updateProfile(fullName: String?, phone: String?): User
    suspend fun getFavoriteRestaurants(): List<Restaurant>
    suspend fun addRestaurantToFavorites(restaurantId: String): Boolean
    suspend fun removeRestaurantFromFavorites(restaurantId: String): Boolean
}