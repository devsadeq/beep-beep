package org.thechance.service_restaurant.domain.usecase

import org.thechance.service_restaurant.domain.entity.Category
import org.thechance.service_restaurant.domain.gateway.IRestaurantOptionsGateway
import org.thechance.service_restaurant.domain.usecase.validation.RestaurantValidation
import org.thechance.service_restaurant.domain.usecase.validation.Validation
import org.thechance.service_restaurant.domain.utils.INVALID_ID
import org.thechance.service_restaurant.domain.utils.INVALID_NAME
import org.thechance.service_restaurant.domain.utils.MultiErrorException
import org.thechance.service_restaurant.domain.utils.NOT_FOUND

interface IManageCategoryUseCase {
    suspend fun getCategories(page: Int, limit: Int): List<Category>
    suspend fun createCategory(category: Category): Boolean
    suspend fun updateCategory(category: Category): Boolean
    suspend fun deleteCategory(categoryId: String): Boolean
}

class ManageCategoryUseCase(
    private val restaurantOptions: IRestaurantOptionsGateway,
    private val restaurantValidation: RestaurantValidation,
    private val basicValidation: Validation
) : IManageCategoryUseCase {
    override suspend fun getCategories(page: Int, limit: Int): List<Category> {
        basicValidation. validatePagination(page,limit)
        return restaurantOptions.getCategories(page, limit)
    }

    override suspend fun createCategory(category: Category): Boolean {
        if (!basicValidation.isValidName(category.name)) {
            throw MultiErrorException(listOf(INVALID_NAME))
        }
        return restaurantOptions.addCategory(category)
    }

    override suspend fun updateCategory(category: Category): Boolean {
        restaurantValidation.validationCategory(category)
        checkIfCategoryIsExist(category.id)
        return restaurantOptions.updateCategory(category)
    }

    override suspend fun deleteCategory(categoryId: String): Boolean {
        checkIfCategoryIsExist(categoryId)
        return restaurantOptions.deleteCategory(categoryId)
    }

    private suspend fun checkIfCategoryIsExist(categoryId: String) {
        if (!basicValidation.isValidId(categoryId)) {
            throw MultiErrorException(listOf(INVALID_ID))
        }
        if (restaurantOptions.getCategory(categoryId) == null) {
            throw MultiErrorException(listOf(NOT_FOUND))
        }
    }
}