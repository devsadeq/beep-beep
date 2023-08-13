package org.thechance.service_notification.domain.model

data class Notification(
    val id: String? = null,
    val title: String,
    val body: String,
    val date: Long,
    val userId: String? = null,
    val topic: String? = null,
)