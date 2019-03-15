package cl.getapps.githubjavarepos.core.ui.state


sealed class ViewState {
    object Loading : ViewState()
    data class Showing(val string: String) : ViewState()
    data class Error(val string: String) : ViewState()
}