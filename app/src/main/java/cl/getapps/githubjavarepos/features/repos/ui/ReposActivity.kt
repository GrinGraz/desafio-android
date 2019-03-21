package cl.getapps.githubjavarepos.features.repos.ui

import android.os.Bundle
import androidx.lifecycle.Observer
import cl.getapps.githubjavarepos.core.android.RecyclerViewActivity
import cl.getapps.githubjavarepos.core.data.StateData
import cl.getapps.githubjavarepos.core.ui.FeatureView
import cl.getapps.githubjavarepos.features.repos.data.remote.ReposParams
import cl.getapps.githubjavarepos.features.repos.domain.model.RepoModel
import com.google.android.material.snackbar.BaseTransientBottomBar
import org.koin.android.scope.ext.android.bindScope
import org.koin.android.scope.ext.android.getOrCreateScope
import org.koin.android.viewmodel.ext.android.viewModel

class ReposActivity : RecyclerViewActivity<ReposRecyclerViewAdapter, RepoModel>(), FeatureView {

    private var pageParam: Int = 1
    private var loadingFromServer: Boolean = false

    override var recyclerViewAdapter = ReposRecyclerViewAdapter()

    private val reposViewModel: ReposViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindScope(getOrCreateScope("Repos"))

        setupViewModel()

        loadItems()
    }

    private fun setupViewModel() = reposViewModel.getReposLiveData().observe(this, Observer(::render))

    override fun render(stateData: StateData) = when (stateData) {
        is StateData.Loading -> showSnackBar("Loading items...")
        is StateData.Success<*> -> setItems(stateData.data as MutableList<RepoModel>)
        is StateData.Error -> showSnackBar("Error loading items :(", isError = true)
    }

    private fun showSnackBar(message: String, isError: Boolean = false) {
        snackBar?.setText(message)?.setDuration(BaseTransientBottomBar.LENGTH_INDEFINITE)?.run {
            if (isError) setAction("Retry") {
                loadingFromServer = false
                loadItems()
            }.show() else show()
        }
    }

    override fun setItems(items: MutableList<RepoModel>) {
        recyclerViewAdapter.values.addAll(items)
        recyclerViewAdapter.notifyItemRangeInserted(recyclerViewAdapter.itemCount + items.size, items.size)
        loadingFromServer = false
        pageParam++
        snackBar?.dismiss()
    }

    override fun loadItems() {
        if (!loadingFromServer) {
            loadingFromServer = true
            snackBar?.dismiss()
            reposViewModel.fetchRepos(ReposParams(pageParam.toString()))
        }
    }
}
