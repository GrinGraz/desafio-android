package cl.getapps.githubjavarepos.features.repos.ui

import android.os.Bundle
import androidx.lifecycle.Observer
import cl.getapps.githubjavarepos.core.android.RecyclerViewActivity
import cl.getapps.githubjavarepos.core.data.StateData
import cl.getapps.githubjavarepos.core.ui.FeatureView
import cl.getapps.githubjavarepos.features.repos.data.remote.ReposParams
import cl.getapps.githubjavarepos.features.repos.domain.model.RepoModel
import org.koin.android.scope.ext.android.bindScope
import org.koin.android.scope.ext.android.getOrCreateScope
import org.koin.android.viewmodel.ext.android.viewModel

class ReposActivity : RecyclerViewActivity<ReposRecyclerViewAdapter, RepoModel>(), FeatureView {

    override var recyclerViewAdapter = ReposRecyclerViewAdapter()

    private val reposViewModel: ReposViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindScope(getOrCreateScope("Repos"))

        setupViewModel()

        loadItems()
    }

    private fun setupViewModel() = reposViewModel.getReposLiveData().observe(this, Observer(::render))

    override fun loadItems() {
        super.loadItems()
        reposViewModel.fetchRepos(ReposParams(pageParam.toString()))
    }

    override fun render(stateData: StateData) = when (stateData) {
        is StateData.Loading -> showSnackBar("Loading items...")
        is StateData.Success<*> -> setItems(stateData.data as MutableList<RepoModel>)
        is StateData.Error -> showSnackBar("Error loading items :(", isError = true)
    }
}
