package sa.factory.app1287704.data

import sa.factory.app1287704.model.AppDeploymentCampaign
import sa.factory.app1287704.model.TesterVerificationMilestone
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

interface AppDeploymentCampaignRepository {
    fun getAvailableCatalog(): Flow<List<TesterVerificationMilestone>>
    fun getActiveRequest(): Flow<AppDeploymentCampaign?>
    suspend fun submitRequest(request: AppDeploymentCampaign): Result<String>
}

class InMemoryAppDeploymentCampaignRepository : AppDeploymentCampaignRepository {
    private val _catalog = MutableStateFlow<List<TesterVerificationMilestone>>(listOf(
        TesterVerificationMilestone("1", "حزمة الفحص المغلق لجوجل بلاي", "توفير عشرين مختبرا نشطا مع تفاعل يومي مستمر لاجتياز شروط النشر بنسبة قبول مؤكدة", 5000L, "verified"),
        TesterVerificationMilestone("2", "تدقيق الامتثال لمتجر آب ستور", "مراجعة شاملة لسياسات الخصوصية وتجربة المستخدم وحل أسباب الرفض المتكررة قبل الرفع الرسمي", 9500L, "security")
    ))
    private val _active = MutableStateFlow<AppDeploymentCampaign?>(null)

    override fun getAvailableCatalog(): Flow<List<TesterVerificationMilestone>> = _catalog.asStateFlow()
    override fun getActiveRequest(): Flow<AppDeploymentCampaign?> = _active.asStateFlow()

    override suspend fun submitRequest(request: AppDeploymentCampaign): Result<String> {
        _active.value = request
        return Result.success(request.requestId)
    }
}
