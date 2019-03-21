package cl.getapps.githubjavarepos.features.repos.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import cl.getapps.githubjavarepos.core.data.StateData
import cl.getapps.githubjavarepos.features.repos.data.remote.ReposParams
import cl.getapps.githubjavarepos.features.repos.domain.usecase.GetRepos
import io.reactivex.disposables.Disposable

class ReposViewModel(private val getRepos: GetRepos) : ViewModel() {

    private var repos: MutableLiveData<StateData> = MutableLiveData()
    private var disposable: Disposable? = null

    fun getReposLiveData() = repos

    fun fetchRepos(params: ReposParams) {
        disposable = getRepos.execute(params)
            .doOnSubscribe { repos.value = StateData.Loading }
            .subscribe(
                { repos.value = StateData.Success(it.toReposModel()) },
                { repos.postValue(StateData.Error(it)) })
    }

    override fun onCleared() {
        disposable?.dispose()
        super.onCleared()
    }
}
