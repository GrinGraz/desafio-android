package cl.getapps.githubjavarepos.core.android

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_item_list.*


abstract class BaseActivity : AppCompatActivity() {

    @LayoutRes
    protected abstract fun getLayoutId(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        makeSnackBar()
        setupToolbar()
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar)
        toolbar.title = title
    }

    abstract fun makeSnackBar()
}