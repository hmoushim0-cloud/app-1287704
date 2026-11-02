package sa.factory.app1287704.model

import kotlinx.serialization.Serializable

@Serializable
data class TesterVerificationMilestone(
    val id: String,
    val title: String,
    val description: String,
    val priceMinorUnits: Long,
    val iconVector: String = "verified"
)

@Serializable
data class AppDeploymentCampaign(
    val requestId: String,
    val items: List<TesterVerificationMilestone>,
    val customerPhone: String,
    val totalAmountMinorUnits: Long,
    val status: AppDeploymentCampaignStatus = AppDeploymentCampaignStatus.PENDING,
    val createdAtEpochMs: Long = System.currentTimeMillis()
)

enum class AppDeploymentCampaignStatus {
    PENDING,
    ACCEPTED,
    IN_PROGRESS,
    COMPLETED,
    CANCELLED
}
