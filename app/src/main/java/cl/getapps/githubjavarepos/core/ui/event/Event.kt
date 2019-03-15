package cl.getapps.githubjavarepos.core.ui.event


sealed class Event {
    data class ItemClicked(val string: String): Event()
}