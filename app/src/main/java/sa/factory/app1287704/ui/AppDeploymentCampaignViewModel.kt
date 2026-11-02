package sa.factory.app1287704.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import sa.factory.app1287704.data.AppDeploymentCampaignRepository
import sa.factory.app1287704.model.AppDeploymentCampaign
import sa.factory.app1287704.model.TesterVerificationMilestone
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class AppDeploymentCampaignViewModel(
    private val repository: AppDeploymentCampaignRepository
) : ViewModel() {
    val catalog: StateFlow<List<TesterVerificationMilestone>> = repository.getAvailableCatalog()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val activeRequest: StateFlow<AppDeploymentCampaign?> = repository.getActiveRequest()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)

    fun submitNewOrder(request: AppDeploymentCampaign) {
        viewModelScope.launch {
            repository.submitRequest(request)
        }
    }
}
