package cl.getapps.githubjavarepos.core.ui

import cl.getapps.githubjavarepos.core.ui.state.ViewState


interface FeatureView {
    fun render(viewState: ViewState)
}