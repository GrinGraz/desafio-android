package cl.getapps.githubjavarepos.core.android

import androidx.recyclerview.widget.RecyclerView


abstract class BaseAdapter<T> : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var values = mutableListOf<T>()
}