package cl.getapps.githubjavarepos.feature.repopullrequests.data

import cl.getapps.githubjavarepos.feature.repopullrequests.domain.PullRequest

data class PullRequests(
    val pullrequests: List<cl.getapps.githubjavarepos.feature.repopullrequests.data.PullRequest>
)

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
    val assignee: String,
    val assignees: List<String>,
    val requested_reviewers: List<String>,
    val requested_teams: List<String>,
    val labels: List<String>,
    val milestone: String,
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
    fun toPullRequest() = PullRequest(user.toUSer(), title, body, created_at)
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
    fun toUSer() = cl.getapps.githubjavarepos.feature.repopullrequests.domain.User(login, avatar_url)
}

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

data class Owner (

    val login : String,
    val id : Int,
    val node_id : String,
    val avatar_url : String,
    val gravatar_id : String,
    val url : String,
    val html_url : String,
    val followers_url : String,
    val following_url : String,
    val gists_url : String,
    val starred_url : String,
    val subscriptions_url : String,
    val organizations_url : String,
    val repos_url : String,
    val events_url : String,
    val received_events_url : String,
    val type : String,
    val site_admin : Boolean
)

data class License (

    val key : String,
    val name : String,
    val spdx_id : String,
    val url : String,
    val node_id : String
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

data class Review_comment (

    val href : String
)

data class Commits(
    val href: String
)

data class Statuses (

    val href : String
)
