package cl.getapps.githubjavarepos.core.data


sealed class StateData {
    object Loading : StateData()
    data class Success<out T>(val data: T) : StateData()
    data class Error(val throwable: Throwable) : StateData()
}