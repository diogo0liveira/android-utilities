package com.dao.utilities.app.recycler

import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import com.dao.recycler.OnAdapterDataChangedListener
import com.dao.utilities.app.BaseActivity
import com.dao.utilities.app.R
import com.dao.utilities.app.recycler.common.Generate
import com.dao.utilities.app.recycler.common.ItemFactory
import kotlinx.android.synthetic.main.activity_adapter.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@ExperimentalStdlibApi
@ExperimentalCoroutinesApi
class PagedAdapterActivity : BaseActivity(R.layout.activity_adapter),
    Generate by ItemFactory(), OnAdapterDataChangedListener {

    private val adapter by lazy {
        ItemPagedAdapter().apply {
            lifecycle.addObserver(this)
            setOnAdapterDataChangedListener(this@PagedAdapterActivity)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.option_adapter, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.load -> {
                loadItems()
                true
            }
            R.id.clean -> {
                lifecycleScope.launch {
                    adapter.submitData(PagingData.empty())
                }
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
        loadItems()
    }

    private fun loadItems() {
        lifecycleScope.launch {
            buildPagingData().collectLatest {
                adapter.submitData(it)
            }
        }
    }

    private fun buildPagingData(): Flow<PagingData<String>> {
        return Pager(
            config = PagingConfig(pageSize = 50),
            pagingSourceFactory = { ItemPagingSource() }
        ).flow
    }

    inner class ItemPagingSource : PagingSource<Int, String>() {
        override suspend fun load(params: LoadParams<Int>): LoadResult<Int, String> {
            return LoadResult.Page(buildItems(), params.key, null)
        }
    }
}