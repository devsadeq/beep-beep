package org.thechance.service_taxi.api.usecase

import org.thechance.service_taxi.domain.entity.Taxi

interface GetTaxiByIdUseCase {
    suspend operator fun invoke(taxiId: String): Taxi?
}