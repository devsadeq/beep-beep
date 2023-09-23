package data.remote.model

data class NotificationDto(
    val id: String? = null,
    val title: String,
    val body: String,
    val date: Long,
    val userId: String? = null,
    val topic: String? = null,
)
