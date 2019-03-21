package cl.getapps.githubjavarepos.core.android

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView


class ItemDivider(private val itemDividerHeight: Int): RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.bottom = itemDividerHeight
    }
}