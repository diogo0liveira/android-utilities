package com.dao.utilities.app

import com.dao.utilities.app.recycler.AdapterActivity
import com.dao.utilities.app.recycler.PagedAdapterActivity
import kotlinx.android.synthetic.main.activity_recycler.*

class RecyclerActivity : BaseActivity(R.layout.activity_recycler) {

    @ExperimentalStdlibApi
    override fun initializeView() {
        buttonAdapter.setOnClickListener {
            navigationTo(AdapterActivity::class)
        }

        buttonPagedAdapter.setOnClickListener {
            navigationTo(PagedAdapterActivity::class)
        }
    }
}