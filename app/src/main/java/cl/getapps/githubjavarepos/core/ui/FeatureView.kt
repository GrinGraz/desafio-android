package cl.getapps.githubjavarepos.core.ui

import cl.getapps.githubjavarepos.core.data.StateData


interface FeatureView {
    fun render(stateData: StateData)

    interface Pageable
}