package com.dao.utilities.app.recycler

import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.dao.recycler.OnAdapterDataChangedListener
import com.dao.utilities.app.BaseActivity
import com.dao.utilities.app.R
import com.dao.utilities.app.recycler.common.Generate
import com.dao.utilities.app.recycler.common.ItemFactory
import kotlinx.android.synthetic.main.activity_adapter.*

@ExperimentalStdlibApi
class AdapterActivity : BaseActivity(R.layout.activity_adapter),
    Generate by ItemFactory(), OnAdapterDataChangedListener {

    private val adapter by lazy {
        ItemAdapter().apply {
            lifecycle.addObserver(this)
            setOnAdapterDataChangedListener(this@AdapterActivity)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.option_adapter, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.load -> {
                adapter.setDataList(buildItems())
                true
            }
            R.id.clean -> {
                adapter.setDataList(emptyList())
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }

    override fun onCollectionChanged(isEmpty: Boolean) {
        Toast.makeText(this, "List is empty: $isEmpty", Toast.LENGTH_SHORT).show()
    }

    override fun initializeView() {
        recyclerView.adapter = adapter
        adapter.setDataList(buildItems())
    }
}