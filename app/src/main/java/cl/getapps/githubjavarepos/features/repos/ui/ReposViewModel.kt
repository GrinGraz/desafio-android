package cl.getapps.githubjavarepos.features.repos.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import cl.getapps.githubjavarepos.core.data.StateData
import cl.getapps.githubjavarepos.core.ui.ListViewModel
import cl.getapps.githubjavarepos.core.ui.event.Event
import cl.getapps.githubjavarepos.features.repos.data.remote.ReposParams
import cl.getapps.githubjavarepos.features.repos.domain.usecase.GetRepos
import io.reactivex.disposables.Disposable

class ReposViewModel(private val getRepos: GetRepos) : ViewModel(), ListViewModel {

    override fun onEvent(event: Event) {
        TODO("not implemented")
    }

    var repos: MutableLiveData<StateData> = MutableLiveData()
    private var disposable: Disposable? = null

    fun fetchRepos(params: ReposParams) {
        disposable = getRepos.execute(params)
            .doOnSubscribe { repos.value = StateData.Loading }
            .doOnError { repos.value = StateData.Error(it) }
            .subscribe(
                { repos.value = StateData.Success(it.toReposModel()) },
                { repos.postValue(StateData.Error(it)) })
    }

    override fun onCleared() {
        disposable?.dispose()
        super.onCleared()
    }
}
