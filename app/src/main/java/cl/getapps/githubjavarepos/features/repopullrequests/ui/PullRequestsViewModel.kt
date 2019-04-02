package cl.getapps.githubjavarepos.features.repopullrequests.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import cl.getapps.githubjavarepos.core.data.StateData
import cl.getapps.githubjavarepos.features.repopullrequests.data.entity.toPullRequestsModel
import cl.getapps.githubjavarepos.features.repopullrequests.data.remote.PullRequestParams
import cl.getapps.githubjavarepos.features.repopullrequests.domain.usecase.GetPullRequests
import io.reactivex.disposables.Disposable

class PullRequestsViewModel(private val getPullRequests: GetPullRequests) : ViewModel() {

    private var pullRequests: MutableLiveData<StateData> = MutableLiveData()
    private var disposable: Disposable? = null

    fun getPullRequestsLiveData() = pullRequests

    fun fetchPullRequests(params: PullRequestParams) {
        disposable = getPullRequests.execute(params)
            .doOnSubscribe { pullRequests.value = StateData.Loading }
            .subscribe(
                { pullRequests.value = StateData.Success(it.toPullRequestsModel()) },
                { pullRequests.value = StateData.Error(it) })
    }

    override fun onCleared() {
        disposable?.dispose()
        super.onCleared()
    }
}
