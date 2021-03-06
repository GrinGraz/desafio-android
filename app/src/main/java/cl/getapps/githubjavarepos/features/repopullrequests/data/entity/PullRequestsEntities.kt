package cl.getapps.githubjavarepos.features.repopullrequests.data.entity

import cl.getapps.githubjavarepos.features.repopullrequests.domain.model.PullRequestModel
import com.google.gson.annotations.SerializedName

typealias PullRequestsResponse = List<PullRequest>

fun List<PullRequest>.toPullRequestsModel() = this.mapTo(
    mutableListOf<PullRequestModel>(),
    fromDataPullRequest
)

private val fromDataPullRequest = { dataPullRequest: PullRequest ->
    dataPullRequest.toPullRequestModel(dataPullRequest)
}

data class PullRequest(
    val url: String,
    val id: Int,
    val node_id: String,
    val html_url: String,
    val diff_url: String,
    val patch_url: String,
    val issue_url: String,
    val number: Int,
    val state: String,
    val locked: Boolean,
    val title: String,
    val user: User,
    val body: String,
    val created_at: String,
    val updated_at: String,
    val closed_at: String,
    val merged_at: String,
    val merge_commit_sha: String,
    val assignee: Assignee,
    val assignees: List<Assignee>,
    val requested_reviewers: List<Any>,
    val requested_teams: List<Any>,
    val labels: List<Labels>,
    val milestone: Milestone,
    val commits_url: String,
    val review_comments_url: String,
    val review_comment_url: String,
    val comments_url: String,
    val statuses_url: String,
    val head: Head,
    val base: Base,
    val _links: Links,
    val author_association: String
) {
    fun toPullRequestModel(dataPullRequest: PullRequest) =
        PullRequestModel(
            dataPullRequest.user.toUSerModel(),
            dataPullRequest.title,
            dataPullRequest.body,
            dataPullRequest.created_at
        )
}

data class User(
    val login: String,
    val id: Int,
    val node_id: String,
    val avatar_url: String,
    val gravatar_id: String,
    val url: String,
    val html_url: String,
    val followers_url: String,
    val following_url: String,
    val gists_url: String,
    val starred_url: String,
    val subscriptions_url: String,
    val organizations_url: String,
    val repos_url: String,
    val events_url: String,
    val received_events_url: String,
    val type: String,
    val site_admin: Boolean
) {
    fun toUSerModel() = cl.getapps.githubjavarepos.features.repopullrequests.domain.model.User(login, avatar_url)
}

data class Assignee(
    @SerializedName("login") val login: String,
    @SerializedName("id") val id: Int,
    @SerializedName("node_id") val node_id: String,
    @SerializedName("avatar_url") val avatar_url: String,
    @SerializedName("gravatar_id") val gravatar_id: String,
    @SerializedName("url") val url: String,
    @SerializedName("html_url") val html_url: String,
    @SerializedName("followers_url") val followers_url: String,
    @SerializedName("following_url") val following_url: String,
    @SerializedName("gists_url") val gists_url: String,
    @SerializedName("starred_url") val starred_url: String,
    @SerializedName("subscriptions_url") val subscriptions_url: String,
    @SerializedName("organizations_url") val organizations_url: String,
    @SerializedName("repos_url") val repos_url: String,
    @SerializedName("events_url") val events_url: String,
    @SerializedName("received_events_url") val received_events_url: String,
    @SerializedName("type") val type: String,
    @SerializedName("site_admin") val site_admin: Boolean
)

data class Labels(
    val id: Int,
    val node_id: String,
    val url: String,
    val name: String,
    val color: String,
    val default: Boolean
)

data class Milestone(
    val url: String,
    val html_url: String,
    val labels_url: String,
    val id: Int,
    val node_id: String,
    val number: Int,
    val title: String,
    val description: String,
    val creator: Creator,
    val open_issues: Int,
    val closed_issues: Int,
    val state: String,
    val created_at: String,
    val updated_at: String,
    val due_on: String,
    val closed_at: String
)

data class Creator(
    val login: String,
    val id: Int,
    val node_id: String,
    val avatar_url: String,
    val gravatar_id: String,
    val url: String,
    val html_url: String,
    val followers_url: String,
    val following_url: String,
    val gists_url: String,
    val starred_url: String,
    val subscriptions_url: String,
    val organizations_url: String,
    val repos_url: String,
    val events_url: String,
    val received_events_url: String,
    val type: String,
    val site_admin: Boolean
)

data class Head(
    val label: String,
    val ref: String,
    val sha: String,
    val user: User,
    val repo: Repo
)

data class Repo(
    val id: Int,
    val node_id: String,
    val name: String,
    val full_name: String,
    val private: Boolean,
    val owner: Owner,
    val html_url: String,
    val description: String,
    val fork: Boolean,
    val url: String,
    val forks_url: String,
    val keys_urls_url: String,
    val collaborators_url: String,
    val teams_url: String,
    val hooks_url: String,
    val issue_events_url: String,
    val events_url: String,
    val assignees_url: String,
    val branches_url: String,
    val tags_url: String,
    val blobs_url: String,
    val git_tags_url: String,
    val git_refs_url: String,
    val trees_url: String,
    val statuses_url: String,
    val languages_url: String,
    val stargazers_url: String,
    val contributors_url: String,
    val subscribers_url: String,
    val subscription_url: String,
    val commits_url: String,
    val git_commits_url: String,
    val comments_url: String,
    val issue_comment_url: String,
    val contents_url: String,
    val compare_url: String,
    val merges_url: String,
    val archive_url: String,
    val downloads_url: String,
    val issues_url: String,
    val pulls_url: String,
    val milestones_url: String,
    val notifications_url: String,
    val labels_url: String,
    val releases_url: String,
    val deployments_url: String,
    val created_at: String,
    val updated_at: String,
    val pushed_at: String,
    val git_url: String,
    val ssh_url: String,
    val clone_url: String,
    val svn_url: String,
    val homepage: String,
    val size: Int,
    val stargazers_count: Int,
    val watchers_count: Int,
    val language: String,
    val has_issues: Boolean,
    val has_projects: Boolean,
    val has_downloads: Boolean,
    val has_wiki: Boolean,
    val has_pages: Boolean,
    val forks_count: Int,
    val mirror_url: String,
    val archived: Boolean,
    val open_issues_count: Int,
    val license: License,
    val forks: Int,
    val open_issues: Int,
    val watchers: Int,
    val default_branch: String
)

data class Owner(
    val login: String,
    val id: Int,
    val node_id: String,
    val avatar_url: String,
    val gravatar_id: String,
    val url: String,
    val html_url: String,
    val followers_url: String,
    val following_url: String,
    val gists_url: String,
    val starred_url: String,
    val subscriptions_url: String,
    val organizations_url: String,
    val repos_url: String,
    val events_url: String,
    val received_events_url: String,
    val type: String,
    val site_admin: Boolean
)

data class License(
    val key: String,
    val name: String,
    val spdx_id: String,
    val url: String,
    val node_id: String
)

data class Base(
    val label: String,
    val ref: String,
    val sha: String,
    val user: User,
    val repo: Repo
)

data class Links(
    val self: Self,
    val html: Html,
    val issue: Issue,
    val comments: Comments,
    val review_comments: Review_comments,
    val review_comment: Review_comment,
    val commits: Commits,
    val statuses: Statuses
)

data class Self(
    val href: String
)

data class Html(
    val href: String
)

data class Issue(
    val href: String
)

data class Comments(
    val href: String
)

data class Review_comments(
    val href: String
)

data class Review_comment(
    val href: String
)

data class Commits(
    val href: String
)

data class Statuses(
    val href: String
)
