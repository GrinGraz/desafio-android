package cl.getapps.githubjavarepos.features.repopullrequests.ui

import android.os.Bundle
import android.view.MenuItem
import androidx.lifecycle.Observer
import cl.getapps.githubjavarepos.core.android.RecyclerViewActivity
import cl.getapps.githubjavarepos.core.data.StateData
import cl.getapps.githubjavarepos.core.ui.FeatureView
import cl.getapps.githubjavarepos.features.repopullrequests.data.remote.PullRequestParams
import cl.getapps.githubjavarepos.features.repopullrequests.domain.model.PullRequestModel
import com.google.android.material.snackbar.BaseTransientBottomBar
import org.koin.android.scope.ext.android.bindScope
import org.koin.android.scope.ext.android.getOrCreateScope
import org.koin.android.viewmodel.ext.android.viewModel


class PullRequestsActivity : RecyclerViewActivity<PullRequestsRecyclerViewAdapter, PullRequestModel>(), FeatureView {

    override var recyclerViewAdapter = PullRequestsRecyclerViewAdapter()

    private val pullRequestsViewModel: PullRequestsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindScope(getOrCreateScope("PullRequests"))

        setupViewModel()

        loadItems()
    }

    private fun setupViewModel() {
        pullRequestsViewModel.getPullRequestsLiveData().observe(this, Observer(::render))
    }

    override fun render(stateData: StateData) = when (stateData) {
        is StateData.Loading -> showSnackBar("Loading items...")
        is StateData.Success<*> -> setItems(stateData.data as MutableList<PullRequestModel>)
        is StateData.Error -> showSnackBar("Error loading items :(", isError = true)
    }

    override fun loadItems() {
        pullRequestsViewModel.fetchPullRequests(
            PullRequestParams(
                intent.getStringExtra(ARGS.REPO_OWNER),
                intent.getStringExtra(ARGS.REPO_NAME)
            )
        )
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        android.R.id.home -> {
            onBackPressed()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    object ARGS {
        const val REPO_OWNER = "owner"
        const val REPO_NAME = "name"
    }
}
