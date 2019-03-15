package cl.getapps.githubjavarepos.core.ui

import cl.getapps.githubjavarepos.core.ui.event.Event


interface ListViewModel {
    fun onEvent(event: Event)
}