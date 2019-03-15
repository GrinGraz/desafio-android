package cl.getapps.githubjavarepos.features.repos.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cl.getapps.githubjavarepos.R
import cl.getapps.githubjavarepos.core.data.StateData
import cl.getapps.githubjavarepos.core.ui.FeatureView
import cl.getapps.githubjavarepos.core.ui.state.ViewState
import cl.getapps.githubjavarepos.features.repos.data.remote.ReposParams
import cl.getapps.githubjavarepos.features.repos.domain.model.RepoModel
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_item_list.*
import kotlinx.android.synthetic.main.item_list.*
import org.koin.android.ext.android.inject
import org.koin.android.scope.ext.android.bindScope
import org.koin.android.scope.ext.android.getOrCreateScope
import org.koin.android.viewmodel.ext.android.viewModel


class ReposActivity : AppCompatActivity(), FeatureView {
    override fun render(viewState: ViewState) {
        when (viewState) {
            is ViewState.Loading -> TODO("not implemented")
            is ViewState.Showing -> TODO("not implemented")
            is ViewState.Error -> TODO("not implemented")
        }
    }

    private var pageParam: Int = 0
    private var loadingFromServer: Boolean = false

    private val reposRecyclerViewAdapter: ReposRecyclerViewAdapter by inject()
    private var linearLayoutManager: LinearLayoutManager? = null

    private val reposViewModel: ReposViewModel by viewModel()

    private var snackBar: Snackbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_list)
        bindScope(getOrCreateScope("Repos"))

        setToolbar()

        makeSnackBar()

        setupRecyclerView()

        setupViewModel()

        loadRepos()
    }

    private fun setToolbar() {
        setSupportActionBar(toolbar)
        toolbar.title = title
    }

    private fun makeSnackBar() {
        snackBar = Snackbar.make(item_list, "Loading items ...", Snackbar.LENGTH_INDEFINITE)
    }

    private fun setupRecyclerView() {
        linearLayoutManager = LinearLayoutManager(this)
        item_list.layoutManager = linearLayoutManager
        item_list.adapter = reposRecyclerViewAdapter
        addPagination()
    }

    private fun setupViewModel() {
        reposViewModel.repos.observe(this, Observer { repos -> handleReposState(repos) })
    }

    private fun handleReposState(repos: StateData?) {
        when (repos) {
            is StateData.Loading ->/* if (!loadingFromServer) showSnackBar("Loading items...") else showSnackBar("Loading more items...")*/ ""
            is StateData.Success<*> -> setRecyclerViewData(repos.data as MutableList<RepoModel>)
            is StateData.Error -> showSnackBar("Error loading items :C", isError = true)
        }
    }

    private fun loadRepos() {
        if (!loadingFromServer) {
            loadingFromServer = true
            pageParam++
            reposViewModel.fetchRepos(ReposParams(pageParam.toString()))
        }
    }

    private fun showSnackBar(message: String, isError: Boolean = false) {
        snackBar?.setText(message)?.setDuration(BaseTransientBottomBar.LENGTH_INDEFINITE)?.run {
            if (isError) setAction("Retry") {
                loadingFromServer = false
                loadRepos()
            }.show() else show()
        }
    }

    private fun setRecyclerViewData(repos: MutableList<RepoModel>) {
        reposRecyclerViewAdapter.values.addAll(repos)
        reposRecyclerViewAdapter.notifyDataSetChanged()
        loadingFromServer = false
        snackBar?.dismiss()
    }

    private fun addPagination() {

        fun isLastItemVisible() =
            linearLayoutManager?.findLastCompletelyVisibleItemPosition()!! == reposRecyclerViewAdapter.values.size - 1

        item_list.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                if (isLastItemVisible()) loadRepos()
            }
        })
    }
}
