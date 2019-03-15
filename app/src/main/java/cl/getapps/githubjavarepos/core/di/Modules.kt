package cl.getapps.githubjavarepos.core.di

import cl.getapps.githubjavarepos.BuildConfig
import cl.getapps.githubjavarepos.core.data.CacheSource
import cl.getapps.githubjavarepos.core.data.RemoteSource
import cl.getapps.githubjavarepos.core.interactor.executor.JobExecutor
import cl.getapps.githubjavarepos.core.interactor.executor.PostExecutionThread
import cl.getapps.githubjavarepos.core.interactor.executor.ThreadExecutor
import cl.getapps.githubjavarepos.core.remote.ServiceFactory
import cl.getapps.githubjavarepos.core.ui.UiThread
import cl.getapps.githubjavarepos.feature.repopullrequests.data.cache.PullRequestCache
import cl.getapps.githubjavarepos.feature.repopullrequests.data.remote.PullRequestsRemote
import cl.getapps.githubjavarepos.feature.repopullrequests.data.repository.PullRequestsRepository
import cl.getapps.githubjavarepos.feature.repopullrequests.domain.GetPullRequests
import cl.getapps.githubjavarepos.feature.repos.data.cache.ReposCacheDataSource
import cl.getapps.githubjavarepos.feature.repos.data.remote.ReposRemoteDataSource
import cl.getapps.githubjavarepos.feature.repos.data.repository.ReposRepository
import cl.getapps.githubjavarepos.feature.repos.data.source.DataSourceFactory
import cl.getapps.githubjavarepos.feature.repos.domain.GetRepos
import cl.getapps.githubjavarepos.feature.repos.ui.ReposRecyclerViewAdapter
import cl.getapps.githubjavarepos.feature.repos.ui.ReposViewModel
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val applicationModule = module {
    single { JobExecutor() as ThreadExecutor }
    single { UiThread() as PostExecutionThread }
}

val reposModule = module {
    factory { ServiceFactory.makeRepoService(BuildConfig.DEBUG) }

    factory<RemoteSource> { ReposRemoteDataSource(get()) }
    factory<CacheSource> { ReposCacheDataSource() }

    factory { DataSourceFactory(get(), get()) }

    single<cl.getapps.githubjavarepos.feature.repos.domain.repository.ReposRepository> { ReposRepository(get()) }

    factory { GetRepos(get(), get(), get()) }

    viewModel { ReposViewModel(get()) }

    factory { ReposRecyclerViewAdapter() }
}

val pullRequestsModule = module {
    factory { ServiceFactory.makePullRequestsService(BuildConfig.DEBUG) }

    factory<RemoteSource> { PullRequestsRemote(get()) }
    factory<CacheSource> { PullRequestCache() }

    factory { cl.getapps.githubjavarepos.feature.repopullrequests.data.source.DataSourceFactory(get(), get()) }

    single { PullRequestsRepository(get()) }

    factory { GetPullRequests(get(), get(), get()) }
}
