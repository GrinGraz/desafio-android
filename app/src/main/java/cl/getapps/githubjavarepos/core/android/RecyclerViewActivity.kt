package cl.getapps.githubjavarepos.core.android

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cl.getapps.githubjavarepos.R
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.item_list.*


abstract class RecyclerViewActivity<Adapter : RecyclerView.Adapter<RecyclerView.ViewHolder>, Model> : BaseActivity() {

    lateinit var layoutManager: LinearLayoutManager
    open lateinit var recyclerViewAdapter: Adapter

    protected var snackBar: Snackbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        makeSnackBar()

        setupRecyclerView()

        addPagination()
    }

    private fun setupRecyclerView() {
        layoutManager = LinearLayoutManager(this)
        item_list.layoutManager = layoutManager

        val itemDividerDecoration = resources.getDimension(R.dimen.item_separator_height).toInt()
        item_list.addItemDecoration(ItemDivider(itemDividerDecoration))
        item_list.adapter = recyclerViewAdapter
    }

    override fun getLayoutId(): Int = R.layout.activity_item_list

    override fun makeSnackBar() {
        snackBar = Snackbar.make(item_list, "", Snackbar.LENGTH_INDEFINITE)
    }

    private fun addPagination() {

        fun isLastItemVisible() =
            layoutManager.findLastCompletelyVisibleItemPosition() == recyclerViewAdapter.itemCount - 1

        item_list.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                if (isLastItemVisible()) loadItems()
            }
        })
    }

    abstract fun loadItems()

    abstract fun setItems(items: MutableList<Model>)
}